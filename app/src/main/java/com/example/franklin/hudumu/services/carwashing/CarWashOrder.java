package com.example.franklin.hudumu.services.carwashing;

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
import com.example.franklin.hudumu.adapters.CarWashingAdpater;
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

public class CarWashOrder extends Fragment {

    CarWashingAdpater mCarWashingAdpater;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore mFirebaseFirestore;
    private TextView mTextViewCategory;
    private TextView mTextViewVehicleType;
    private TextView mTextViewPrice;
    private Button mButtonRequest;
    ProgressDialog mProgressDialog;
    UploadTask mUploadTask;
    AlertDialog.Builder builder;
    @ServerTimestamp
    Date time;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.car_wash_order, container, false);

        mTextViewCategory = (TextView)rootView.findViewById(R.id.category);
        mTextViewVehicleType = (TextView)rootView.findViewById(R.id.vehicleType);
        mTextViewPrice = (TextView)rootView.findViewById(R.id.carWashPrice);
        mButtonRequest = (Button)rootView.findViewById(R.id.button_request_car_wash);

        //Saving data to firebase
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        builder = new AlertDialog.Builder(getContext());//Initialize AlertDialog

        mButtonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestCarWash();
            }
        });

        return rootView;
    }

    private void requestCarWash(){

        builder.setMessage("Are you sure you want to request?")
                .setCancelable(false)
                .setTitle("Car wash Errand Request")
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
                        profile.put("vehicleWashCategory", mTextViewCategory.getText().toString());
                        profile.put("vehicleType", mTextViewVehicleType.getText().toString());
                        profile.put("vehicleWashPrice", mTextViewPrice.getText().toString());
                        profile.put("userId", userId);
                        profile.put("email", email);
                        profile.put("timeRequested", FieldValue.serverTimestamp());

                        mFirebaseFirestore.collection("VehicleCarWashErrands").document(userId)
                                .set(profile)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getContext(), "Car wash errand successful! Kindly await confirmation message!", Toast.LENGTH_LONG).show();
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

        String category = mTextViewCategory.getText().toString().trim();
        if (TextUtils.isEmpty(category)) {
            mTextViewCategory.setError("Car wash category required!");
            valid = false;
        } else {
            mTextViewCategory.setError(null);
        }

        String vehicle = mTextViewVehicleType.getText().toString().trim();
        if (TextUtils.isEmpty(vehicle)) {
            mTextViewVehicleType.setError("Vehicle type required!");
            valid = false;
        } else {
            mTextViewVehicleType.setError(null);
        }

        String price = mTextViewPrice.getText().toString().trim();
        if (TextUtils.isEmpty(price)) {
            mTextViewPrice.setError("Price required!");
            valid = false;
        } else {
            mTextViewPrice.setError(null);
        }

        return valid;
    }

    private void resetFields(){
        mTextViewPrice.setText("");
        mTextViewVehicleType.setText("");
        mTextViewCategory.setText("");
    }
}
