package com.example.franklin.hudumu.services.pizza;

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

public class PizzaOrderFragment extends Fragment {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore mFirebaseFirestore;
    private TextView mTextViewPizzaCategory;
    private TextView mTextViewPizzaType;
    private TextView mTextViewPizzaSize;
    private TextView mTextViewPizzaPrice;
    private Button mButtonRequest;
    ProgressDialog mProgressDialog;
    UploadTask mUploadTask;
    AlertDialog.Builder builder;
    @ServerTimestamp
    Date time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.pizza_order, container, false);

        mTextViewPizzaCategory = (TextView)rootView.findViewById(R.id.pizzaCategory);
        mTextViewPizzaType = (TextView)rootView.findViewById(R.id.pizzaType);
        mTextViewPizzaPrice = (TextView)rootView.findViewById(R.id.pizzaPrice);
        mTextViewPizzaSize = (TextView)rootView.findViewById(R.id.pizzaSize);
        mButtonRequest = (Button)rootView.findViewById(R.id.button_request_pizza);

        //Saving data to firebase
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        builder = new AlertDialog.Builder(getContext());//Initialize AlertDialog

        mButtonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPizza();
            }
        });

        return rootView;
    }

    private void requestPizza(){

        builder.setMessage("Are you sure you want to request?")
                .setCancelable(false)
                .setTitle("Pizza Errand Request")
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
                        profile.put("pizzaCategory", mTextViewPizzaCategory.getText().toString());
                        profile.put("pizzaType", mTextViewPizzaType.getText().toString());
                        profile.put("pizzaSize", mTextViewPizzaSize.getText().toString());
                        profile.put("pizzaPrice", mTextViewPizzaPrice.getText().toString());
                        profile.put("userId", userId);
                        profile.put("email", email);
                        profile.put("timeRequested", FieldValue.serverTimestamp());

                        mFirebaseFirestore.collection("PizzaErrands").document(userId)
                                .set(profile)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getContext(), "Pizza errand request successful! Kindly await confirmation message!", Toast.LENGTH_LONG).show();
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

        String category = mTextViewPizzaCategory.getText().toString().trim();
        if (TextUtils.isEmpty(category)) {
            mTextViewPizzaCategory.setError("Pizza category required!");
            valid = false;
        } else {
            mTextViewPizzaCategory.setError(null);
        }

        String vehicle = mTextViewPizzaType.getText().toString().trim();
        if (TextUtils.isEmpty(vehicle)) {
            mTextViewPizzaType.setError("Pizza type required!");
            valid = false;
        } else {
            mTextViewPizzaType.setError(null);
        }

        String price = mTextViewPizzaPrice.getText().toString().trim();
        if (TextUtils.isEmpty(price)) {
            mTextViewPizzaPrice.setError("Pizza Price required!");
            valid = false;
        } else {
            mTextViewPizzaPrice.setError(null);
        }

        String size = mTextViewPizzaSize.getText().toString().trim();
        if (TextUtils.isEmpty(size)) {
            mTextViewPizzaSize.setError("Pizza Size required!");
            valid = false;
        } else {
            mTextViewPizzaSize.setError(null);
        }

        return valid;
    }

    private void resetFields(){
        mTextViewPizzaPrice.setText("");
        mTextViewPizzaType.setText("");
        mTextViewPizzaSize.setText("");
        mTextViewPizzaCategory.setText("");
    }
}
