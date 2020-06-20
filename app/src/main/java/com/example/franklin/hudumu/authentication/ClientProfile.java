package com.example.franklin.hudumu.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franklin.hudumu.MainActivity;
import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.models.ExpandableListViewData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClientProfile extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 1000;
    private TextView mTextViewName;
    private TextView mTextViewContact;
    private TextView mTextViewEstate;
    private TextView mTextViewHouseNo;
    private ImageView mImageView;
    private Button mButtonSubmit, mButtonUpload;
    private FirebaseFirestore mFirebaseFirestore;
    private CircularImageView mCircularImageView;
    private StorageReference mStorageReference;
    ProgressDialog mProgressDialog;
    UploadTask mUploadTask;
    private Timestamp timestamp;
    @ServerTimestamp
    Date time;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        mTextViewName = (TextView) findViewById(R.id.editText18);
        mTextViewContact = (TextView) findViewById(R.id.editText24);
        mTextViewEstate = (TextView) findViewById(R.id.editText20);
        mTextViewHouseNo = (TextView) findViewById(R.id.editText21);
        mButtonSubmit = (Button) findViewById(R.id.button3);
        mButtonUpload = (Button) findViewById(R.id.button2);
        mCircularImageView = (CircularImageView) findViewById(R.id.imageProfile);


        //mFirebaseAuth = FirebaseAuth.getInstance();
        //createAuthStateListener();
        mButtonSubmit.setOnClickListener(this);
        mButtonUpload.setOnClickListener(this);

        //Saving data to firebase
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        //Create a ref to Firebase Storage
        mStorageReference = FirebaseStorage.getInstance().getReference();
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(ClientProfile.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        if (view == mButtonUpload) {
            selectImageFromStorage();
        } else if (view == mButtonSubmit) {
            uploadUserProfile();
            storePhotoToFirebaseStorage();
        }
    }

    private void uploadUserProfile() {
        //Save image
        // uploadImageToDb();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            return;
        }

        String userId = user.getUid();
        String email = user.getEmail();

        Map<String, Object> profile = new HashMap<>();
        profile.put("contact", mTextViewContact.getText().toString());
        profile.put("estate", mTextViewEstate.getText().toString());
        profile.put("houseno", mTextViewHouseNo.getText().toString());
        profile.put("name", mTextViewName.getText().toString());
        profile.put("userId", userId);
        profile.put("email", email);
        profile.put("time", FieldValue.serverTimestamp());


        //mFirebaseFirestore.collection("").add(userId)
        /*mFirebaseFirestore.collection("users").document("profile")
                .set(profile)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ClientProfile.this, "Profile created successfully!", Toast.LENGTH_SHORT).show();
                        resetFieldData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ClientProfile.this, "Error! Check your Internet Connection! " + e.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                        //mTextViewName.findFocus();
                    }
                });*/

        //Validate fields
        if (!validateData()){
            return;
        }

        mFirebaseFirestore.collection("users").document(userId)
                .set(profile)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ClientProfile.this, "Profile created successfully!", Toast.LENGTH_LONG).show();
                        resetFieldData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ClientProfile.this, "Error! Check your Internet Connection! " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void storePhotoToFirebaseStorage() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            return;
        }

        String userId = user.getUid();
        String email = user.getEmail();

        if (filePath != null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setTitle("Uploading Photo....");
            mProgressDialog.show();

            //StorageReference ref = mStorageReference.child("images/photo.jpg");
            StorageReference mref =  mStorageReference.child(userId);
            mref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mProgressDialog.dismiss();

                            Toast.makeText(ClientProfile.this, "Profile photo uploaded!", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mProgressDialog.dismiss();
                            Toast.makeText(ClientProfile.this, "There was a problem uploading your photo. Kindly check your internet connection and re-try!" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //Display percentage
                            mProgressDialog.setMessage("Uploading! " + ((int) progress) + " %...");
                        }
                    });
        }
    }


    //Select image from storage
    private void selectImageFromStorage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                mCircularImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void resetFieldData() {
        mTextViewHouseNo.setText("");
        mTextViewEstate.setText("");
        mTextViewContact.setText("");
        mTextViewName.setText("");
    }

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

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mFirebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }*/
}
