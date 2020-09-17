package com.example.bucovidmonitor.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bucovidmonitor.R;
import com.example.bucovidmonitor.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class AdminHome extends AppCompatActivity {

    Button LogOut;
    TextView totSurveys;
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_screen);
        LogOut = findViewById(R.id.logoutBtn);
        totSurveys = findViewById(R.id.totalSurveys);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //update name to name of person logged in.

        ReadData();
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }

    public void ReadData(){
        db.collection("SymptomSurvey")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int numSurveys = task.getResult().size();
                            totSurveys.append(Integer.toString(numSurveys));
                            for (QueryDocumentSnapshot document : task.getResult()) {

                            }
                        }
                    }
                });

    }

}
