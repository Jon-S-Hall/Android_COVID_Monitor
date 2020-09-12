package com.example.bucovidmonitor.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bucovidmonitor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {
    EditText mName, mEmail, mPassword, mConfirmPassword;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName = findViewById(R.id.nameEntry);
        mEmail = findViewById(R.id.emailAddressEntry);
        mPassword = findViewById(R.id.passwordEntry);
        mConfirmPassword = findViewById(R.id.confirmPasswordEntry);
        mRegisterBtn = findViewById(R.id.registerButton);
        mLoginBtn = findViewById(R.id.alreadyRegistered);

        fAuth = FirebaseAuth.getInstance(); //gets current instance of firebase database.

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirmPassword = mConfirmPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("You need an email to register!");
                    return;
                }

                if(!TextUtils.equals(password, confirmPassword) || (TextUtils.getTrimmedLength(password) < 6)){
                    mPassword.setError("There is a problem with your password! Make sure you've entered it correctly and that is is more than 6 characters.");
                    return;
                }


                //register the user in firebase

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }
}
