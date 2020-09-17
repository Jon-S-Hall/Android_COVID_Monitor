package com.example.bucovidmonitor.app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bucovidmonitor.R;
import com.example.bucovidmonitor.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HomeScreen extends AppCompatActivity {

    // private Button surveyBtn;
    TextView Banner;
    Button LogOut;
    FirebaseFirestore db;
    FirebaseUser user;
    TextView surveyText;
    long difference;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.home_screen);
        Banner = findViewById(R.id.welcomeBanner);
        LogOut = findViewById(R.id.logoutBtn);
        surveyText = findViewById(R.id.nextSurveyDueText);

        user = FirebaseAuth.getInstance().getCurrentUser();
        //update name to name of person logged in.
        Banner.append(" " + user.getEmail() + "!");

        final Button surveyBtn = findViewById(R.id.button2);
        final Button APIBtn = findViewById(R.id.APIBtn);


        surveyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), SurveyActivity.class));
            }
        });

        APIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), CovidData.class));
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        NextSurveyDue();
    }

    public void NextSurveyDue(){
        Date curDate = Calendar.getInstance().getTime();
        long mostRecentSurvey = 25;

        db.collection("SymptomSurvey")
                .whereEqualTo("uid", user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int numSurveys = task.getResult().size();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.exists()) {
                                    Map<String, Object> survey = document.getData();
                                    Timestamp taken = (Timestamp)survey.get("date");
                                    Date datetaken = taken.toDate();
                                    try {
                                        difference = curDate.getTime() - datetaken.getTime();
                                    } catch (Exception e) {
                                        System.out.println("Error " + e.getMessage());
                                        return;
                                    }
                                    long secondsInMilli = 1000;
                                    long minutesInMilli = secondsInMilli * 60;
                                    long hoursInMilli = minutesInMilli * 60;
                                    long daysInMilli = hoursInMilli * 24;
                                    difference = difference % daysInMilli;

                                    long elapsedHours = difference / hoursInMilli;

                                    System.out.println(elapsedHours);
                                    if (elapsedHours < mostRecentSurvey) {
                                        surveyText.append(" Tomorrow! You completed survey " + elapsedHours + " hour(s) ago.");
                                        break;
                                    }
                                    if (elapsedHours > mostRecentSurvey) {
                                        surveyText.append(" is due! Last take " + elapsedHours + " hour(s) ago.");
                                    }
                                }
                            }
                        }
                    }
                });

    }
}
