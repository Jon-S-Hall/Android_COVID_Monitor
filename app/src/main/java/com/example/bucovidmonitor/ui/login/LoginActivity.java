package com.example.bucovidmonitor.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bucovidmonitor.R;
import com.example.bucovidmonitor.app.HomeScreen;
import com.example.bucovidmonitor.ui.login.LoginViewModel;
import com.example.bucovidmonitor.ui.login.LoginViewModelFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText mEmail, mPassword;
    TextView mRegisterBtn;
    Button loginBtn;
    ProgressBar loadingProgressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Authorization
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);
        mRegisterBtn = findViewById(R.id.registerBtn);
        loadingProgressBar = findViewById(R.id.loading);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Please enter Email!");
                    return;
                }

                if (TextUtils.getTrimmedLength(password) < 6) {
                    mPassword.setError("There is a problem with your password! Make sure you've entered it correctly and that is is more than 6 characters.");
                    return;
                }

                loadingProgressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Toast.makeText(LoginActivity.this, "Logged in! " + task.getException().getMessage(), Toast.LENGTH_SHORT);
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT);
                        }

                        // ...
                    }
                });
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }
}