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
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class AdminHome extends AppCompatActivity {

    Button LogOut;
    TextView totSurveys;
    TextView surveysTodayText;
    TextView positiveSymptoms;
    TextView positiveSymptomsTotal;
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db;
    long difference;
    int surveysToday;
    int totalSymptomatic;
    int todaySymptomatic;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_screen);
        LogOut = findViewById(R.id.logoutBtn);
        totSurveys = findViewById(R.id.totalSurveys);
        surveysTodayText = findViewById(R.id.surveysToday);
        positiveSymptoms = findViewById(R.id.positiveSurveys);
        positiveSymptomsTotal = findViewById(R.id.positiveSurveysTotal);
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
        surveysToday = 0;
        totalSymptomatic = 0;
        todaySymptomatic = 0;
        Date curDate = Calendar.getInstance().getTime();
        db.collection("SymptomSurvey")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int numSurveys = task.getResult().size();
                            totSurveys.append(Integer.toString(numSurveys));
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.exists()) {
                                    Map<String, Object> survey = document.getData();
                                    Timestamp taken = (Timestamp)survey.get("date");
                                    boolean symptomatic = (boolean)survey.get("symptomatic");
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
                                    if(symptomatic){
                                        totalSymptomatic++;
                                    }
                                    if(elapsedHours < 24){
                                        surveysToday++;
                                        if(symptomatic){
                                            todaySymptomatic++;
                                        }
                                    }

                                }
                            }
                            surveysTodayText.append(" " + surveysToday);
                            positiveSymptoms.append(" " + todaySymptomatic);
                            positiveSymptomsTotal.append(" " + totalSymptomatic);
                        }
                    }
                });



    }

}
