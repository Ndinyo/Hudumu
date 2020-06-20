package com.example.franklin.hudumu.utils;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franklin.hudumu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class History extends AppCompatActivity {

    private TextView mTextViewDisplay, mTextViewPizza, mTextViewCatering;
    private FirebaseFirestore mFirebaseFirestore;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mTextViewDisplay = (TextView)findViewById(R.id.textDisplay);
        mTextViewPizza = (TextView)findViewById(R.id.textDisplayPizza);
        mTextViewCatering = (TextView)findViewById(R.id.textDisplayCatering);


        mFirebaseFirestore = FirebaseFirestore.getInstance();

        fetchHistory();

    }

    private void fetchHistory() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            return;
        }

        String userId = user.getUid();
        String email = user.getEmail();

        DocumentReference reference = mFirebaseFirestore.collection("VehicleCarWashErrands").document(userId);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    fields.append("Date Requested: ").append(doc.get("timeRequested"));
                    fields.append("\nEmail: ").append(doc.get("email"));
                    fields.append("\nVehicle Type: ").append(doc.get("vehicleType"));
                    fields.append("\nVehicle Category: ").append(doc.get("vehicleCategory"));
                    fields.append("\nCharges: ").append(doc.get("vehicleWashPrice"));
                    mTextViewDisplay.setText(fields.toString());
                }
                Toast.makeText(History.this, "Sample History Loaded Successfully!", Toast.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(History.this, "Error: Check your internet connection!" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        DocumentReference reference2 = mFirebaseFirestore.collection("PizzaErrands").document(userId);
        reference2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    fields.append("Date Requested: ").append(doc.get("timeRequested"));
                    fields.append("\nEmail: ").append(doc.get("email"));
                    fields.append("\nPizza Type: ").append(doc.get("pizzaType"));
                    fields.append("\nPizza Size: ").append(doc.get("pizzaSize"));
                    fields.append("\nPizza Category: ").append(doc.get("pizzaCategory"));
                    fields.append("\nCharges: ").append(doc.get("pizzaPrice"));
                    mTextViewPizza.setText(fields.toString());
                }
                Toast.makeText(History.this, "Sample History Loaded Successfully!", Toast.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(History.this, "Error: Check your internet connection!" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        DocumentReference reference3 = mFirebaseFirestore.collection("CateringErrands").document(userId);
        reference3.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    fields.append("Date Requested: ").append(doc.get("timeRequested"));
                    fields.append("\nEmail: ").append(doc.get("email"));
                    fields.append("\nCatering Type: ").append(doc.get("cateringType"));
                    fields.append("\nCatering Category: ").append(doc.get("cateringCategory"));
                    fields.append("\nCharges: ").append(doc.get("cateringPrice"));
                    mTextViewCatering.setText(fields.toString());
                }
                Toast.makeText(History.this, "Sample History Loaded Successfully!", Toast.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(History.this, "Error: Check your internet connection!" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
