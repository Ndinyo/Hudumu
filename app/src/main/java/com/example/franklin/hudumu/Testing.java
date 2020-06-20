package com.example.franklin.hudumu;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;


public class Testing extends AppCompatActivity {

    private TextView mTextViewName;
    private TextView mTextViewContact;
    private TextView mTextViewEstate;
    private TextView mTextViewHouseNo;
    private ImageView mImageView;
    private Button mButtonSubmit, mButtonUpload;
    private FirebaseFirestore mFirebaseFirestore;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        builder = new AlertDialog.Builder(this);//Initialize AlertDialog

        mTextViewName = (TextView) findViewById(R.id.editText18);
        mTextViewContact = (TextView) findViewById(R.id.editText24);
        mTextViewEstate = (TextView) findViewById(R.id.editText20);
        mTextViewHouseNo = (TextView) findViewById(R.id.editText21);
        mButtonSubmit = (Button) findViewById(R.id.button3);

        //Firestore instance
        mFirebaseFirestore = FirebaseFirestore.getInstance();
        retrieveProfile();

        //Update Data
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        realTimeUpdate();
    }

    //Validate Profile
    private boolean validateData() {
        boolean valid = true;

        String name = mTextViewName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            mTextViewName.setError("Name required!");
            valid = false;
        } else {
            mTextViewName.setError(null);
        }

        String contact = mTextViewContact.getText().toString().trim();
        if (TextUtils.isEmpty(contact)) {
            mTextViewContact.setError("Contact required!");
            valid = false;
        } else if (contact.length() < 10){
            mTextViewContact.setError("Contact should be greater than or equal to 10 digits!");
            valid = false;
        } else {
            mTextViewContact.setError(null);
        }

        String estate = mTextViewEstate.getText().toString().trim();
        if (TextUtils.isEmpty(estate)) {
            mTextViewEstate.setError("Estate required!");
            valid = false;
        } else {
            mTextViewEstate.setError(null);
        }

        String houseNo = mTextViewHouseNo.getText().toString().trim();
        if (TextUtils.isEmpty(houseNo)) {
            mTextViewHouseNo.setError("House No. required!");
            valid = false;
        } else {
            mTextViewHouseNo.setError(null);
        }

        return valid;
    }

    //Retrieve user data from Firestore DB
    private void retrieveProfile() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            return;
        }

        String userId = user.getUid();
        String email = user.getEmail();
        DocumentReference reference = mFirebaseFirestore.collection("users").document(userId);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    mTextViewName.setText((CharSequence) documentSnapshot.get("name"));
                    mTextViewContact.setText((CharSequence) documentSnapshot.get("contact"));
                    mTextViewEstate.setText((CharSequence) documentSnapshot.get("estate"));
                    mTextViewHouseNo.setText((CharSequence) documentSnapshot.get("houseno"));
                }
                Toast.makeText(Testing.this, "Profile data loaded successfully!", Toast.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Testing.this, "Error fetching profile data. Check your internet connection!" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    //Update data in Cloud Firestore DB
    private void updateProfile() {

        builder.setMessage("Are you sure you want to update?")
                .setCancelable(false)
                .setTitle("Profile Update")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        if (user == null) {
                            return;
                        }

                        //Validate fields
                        if (!validateData()){
                            return;
                        }

                        String userId = user.getUid();
                        String email = user.getEmail();
                        DocumentReference reference = mFirebaseFirestore.collection("users").document(userId);
                        reference.update("contact", mTextViewContact.getText().toString());
                        reference.update("estate", mTextViewEstate.getText().toString());
                        reference.update("houseno", mTextViewHouseNo.getText().toString());
                        reference.update("name", mTextViewName.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Testing.this, "User: " + mTextViewName.getText().toString() + " Profile updated successfully!", Toast.LENGTH_LONG).show();
                                        resetFieldData();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Testing.this, "Error while updating your profile. KIndly check your internet connection!" + e.toString(), Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void resetFieldData() {
        mTextViewHouseNo.setText("");
        mTextViewEstate.setText("");
        mTextViewContact.setText("");
        mTextViewName.setText("");
    }

    //Listen for updates in real time and notify users via a toast
    private void realTimeUpdate(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            return;
        }

        String userId = user.getUid();
        String email = user.getEmail();
        DocumentReference reference = mFirebaseFirestore.collection("users").document(userId);
        reference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null){
                    Toast.makeText(Testing.this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();
                    return;
                }else if (documentSnapshot != null && documentSnapshot.exists()) {
                    Toast.makeText(Testing.this, "Current data:" + documentSnapshot.getData(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
