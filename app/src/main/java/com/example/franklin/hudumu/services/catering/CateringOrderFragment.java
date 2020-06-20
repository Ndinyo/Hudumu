package com.example.franklin.hudumu.services.catering;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franklin.hudumu.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CateringOrderFragment extends Fragment {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore mFirebaseFirestore;
    private TextView mTextViewCateringCategory;
    private TextView mTextViewCateringType;
    private TextView mTextViewCateringPrice;
    private Button mButtonRequest;
    ProgressDialog mProgressDialog;
    UploadTask mUploadTask;
    AlertDialog.Builder builder;
    @ServerTimestamp
    Date time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.catering_order, container, false);

        mTextViewCateringCategory = (TextView)rootView.findViewById(R.id.cateringCategory);
        mTextViewCateringType = (TextView)rootView.findViewById(R.id.cateringType);
        mTextViewCateringPrice = (TextView)rootView.findViewById(R.id.cateringPrice);
        mButtonRequest = (Button)rootView.findViewById(R.id.button_request_catering);

        //Saving data to firebase
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        builder = new AlertDialog.Builder(getContext());//Initialize AlertDialog

        mButtonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestCatering();
            }
        });

        return rootView;

    }

    private void requestCatering(){

        builder.setMessage("Are you sure you want to request?")
                .setCancelable(false)
                .setTitle("Catering Errand Request")
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


                        Map<String, Object> profile = new HashMap<>();
                        profile.put("cateringCategory", mTextViewCateringCategory.getText().toString());
                        profile.put("cateringType", mTextViewCateringType.getText().toString());
                        profile.put("cateringPrice", mTextViewCateringPrice.getText().toString());
                        profile.put("userId", userId);
                        profile.put("email", email);
                        profile.put("timeRequested", FieldValue.serverTimestamp());

                        mFirebaseFirestore.collection("CateringErrands").document(userId)
                                .set(profile)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getContext(), "Catering errand request successful! Kindly await confirmation message!", Toast.LENGTH_LONG).show();
                                        resetFields();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getContext(), "Error! Check your Internet Connection! " + e.toString(), Toast.LENGTH_LONG).show();
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

    //Validate Profile
    private boolean validateData() {
        boolean valid = true;

        String category = mTextViewCateringCategory.getText().toString().trim();
        if (TextUtils.isEmpty(category)) {
            mTextViewCateringCategory.setError("Category required!");
            valid = false;
        } else {
            mTextViewCateringCategory.setError(null);
        }

        String type = mTextViewCateringType.getText().toString().trim();
        if (TextUtils.isEmpty(type)) {
            mTextViewCateringType.setError("Type required!");
            valid = false;
        } else {
            mTextViewCateringType.setError(null);
        }

        String price = mTextViewCateringPrice.getText().toString().trim();
        if (TextUtils.isEmpty(price)) {
            mTextViewCateringPrice.setError("Price required!");
            valid = false;
        } else {
            mTextViewCateringPrice.setError(null);
        }

        return valid;
    }

    private void resetFields(){
        mTextViewCateringPrice.setText("");
        mTextViewCateringType.setText("");
        mTextViewCateringCategory.setText("");
    }
}
