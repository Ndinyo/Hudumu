package com.example.franklin.hudumu.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class LoginHuduma extends AppCompatActivity implements View.OnClickListener{
    //TextView mTextView;
    //@BindView(R.id.registerText)
    private  TextView mregisterTextView;
    private EditText mEditTextEmail, mPasswordText;
    private Button mButtonLogin;
    private ProgressDialog mProgressDialog;
    public static final String ABS = CreateAccount.class.getSimpleName();
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__huduma);

        //Login widgets/views binding
        mEditTextEmail = (EditText) findViewById(R.id.emailText);
        mPasswordText = (EditText) findViewById(R.id.passwordText);
        mButtonLogin = (Button) findViewById(R.id.loginButton);

        //Button Listener
        ButterKnife.bind(this);
        mButtonLogin.setOnClickListener(this);

        mFirebaseAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgress();

        //To ensure new users navigate to CreateActivity class - bind
        mregisterTextView = (TextView) findViewById(R.id.registerText);
        ButterKnife.bind(this);
        mregisterTextView.setOnClickListener(this);

       // mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view == mregisterTextView){
            Intent intent = new Intent(LoginHuduma.this, CreateAccount.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Exit and start a new task
            startActivity(intent);
            finish();//Destroy LoginHuduma
        } else if (view == mButtonLogin){
            signInClient();
        }
    }

    private void createAuthStateListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Intent intent = new Intent(LoginHuduma.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    private void createAuthProgress(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Loading ...");
        mProgressDialog.setMessage("Login in progress...");
        mProgressDialog.setCancelable(false);
    }

    private boolean validateLoginForm(){

        boolean valid = true;

        String email = mEditTextEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            mEditTextEmail.setError("Email required!");
            valid = false;
        } else {
            mEditTextEmail.setError(null);
        }

        String password = mPasswordText.getText().toString().trim();
        if (TextUtils.isEmpty(password)){
            mPasswordText.setError("Password required!");
            valid = false;
        } else {
            mPasswordText.setError(null);
        }
        return valid;
    }

    private void signInClient(){

        String email = mEditTextEmail.getText().toString().trim();
        String password = mPasswordText.getText().toString().trim();

        Log.d(ABS, "Signing In: " + email);
        if (!validateLoginForm()){
            return;
        }

        mProgressDialog.show();

        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgressDialog.hide();
                        if (task.isSuccessful()){
                            Toast.makeText(LoginHuduma.this, "Account verified successfully!", Toast.LENGTH_LONG).show();
                            Log.d(ABS, "Account verified successfully!");
                        } else {
                            Log.d(ABS, "Incorrect Email or Password!");
                            Toast.makeText(LoginHuduma.this, "Authentication error! Incorrect Email or Password!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
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
 