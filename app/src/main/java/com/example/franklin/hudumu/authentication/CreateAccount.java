package com.example.franklin.hudumu.authentication;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franklin.hudumu.MainActivity;
import com.example.franklin.hudumu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.ButterKnife;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth.AuthStateListener mAuthListener;

    public static final String ABS =CreateAccount.class.getSimpleName();

    private FirebaseAuth mFirebaseAuth;
    private ProgressDialog mProgressDialog;
    private TextView mTextViewSignUp;
    private EditText mEditTextName, mEditTextEmail, mEditTextPassword, mEditTextCreatePassword;
    private Button mButtonSignUp;
    private String mNameUser;
    AlertDialog.Builder builder;

    public CreateAccount(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        builder = new AlertDialog.Builder(this);//Initialize AlertDialog

        mEditTextName = (EditText)findViewById(R.id.createNameText);
        mEditTextEmail = (EditText)findViewById(R.id.createEmailText);
        mEditTextPassword = (EditText)findViewById(R.id.createPasswordText);
        mEditTextCreatePassword = (EditText)findViewById(R.id.createConfirmPassword);
        mButtonSignUp = (Button)findViewById(R.id.signUpNewAccount);
        mTextViewSignUp = (TextView)findViewById(R.id.textAccountReadyLogin);

        mFirebaseAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgress();

        ButterKnife.bind(this);
        mButtonSignUp.setOnClickListener(this);
        ButterKnife.bind(this);
        mTextViewSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mTextViewSignUp){
            Intent intent = new Intent(CreateAccount.this, LoginHuduma.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Exit and start a new task
            startActivity(intent);
            finish();
        }

        if (view == mButtonSignUp){
            createNewUser();
        }
    }

    private boolean isEmailValid(String email){
        boolean emailValid = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());

        if (!emailValid){
            mEditTextEmail.setError("Email address not valid!");
            return false;
        }
        return emailValid;
    }

    private boolean isNameValid(String name){
        if (name.equals("")){
            mEditTextName.setError("Please enter your name!");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String condirmPassword){
        if (password.length() < 6){
            mEditTextPassword.setError("Password should be larger than 6 characters!");
            return false;
        }else if (!password.equals(condirmPassword)){
            mEditTextCreatePassword.setError("Passwords do not match!");
            return false;
        }
        return true;
    }

    private void createAuthProgress(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Loading ...");
        mProgressDialog.setMessage("Authentication with Google mail...");
        mProgressDialog.setCancelable(false);
    }

    public void createProfile(){

        builder.setMessage("You are a step away from Huduma. Let's create your profile.....")
                .setCancelable(false)
                .setTitle("Account Profile Creation")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(CreateAccount.this, ClientProfile.class);
                        startActivity(intent);
                        PendingIntent pendingIntent = TaskStackBuilder.create(CreateAccount.this).addNextIntentWithParentStack(intent)
                                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(CreateAccount.this);
                        mBuilder.setContentIntent(pendingIntent);

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

    private void createNewUser(){

        mNameUser = mEditTextName.getText().toString().trim();

        final String name = mEditTextName.getText().toString().trim();
        final String email = mEditTextEmail.getText().toString().trim();
        String password = mEditTextPassword.getText().toString().trim();
        String passwordConfirm = mEditTextCreatePassword.getText().toString().trim();

        boolean validEmail, validPassword, validName;
        validEmail = isEmailValid(email);
        validName = isNameValid(name);
        validPassword = isValidPassword(password, passwordConfirm);

        boolean validNameUser = isNameValid(mNameUser);

        if (!validName || !validEmail || !validPassword) return;

        mProgressDialog.show();

        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        mProgressDialog.dismiss();

                        if (task.isSuccessful()){
                            Log.d(ABS, "Authentication Successful!");
                            createProfile();
                        } else {
                            Toast.makeText(CreateAccount.this, "Access Denied : Authentication Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void createAuthStateListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Intent intent = new Intent(CreateAccount.this, ClientProfile.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
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
    }
}
