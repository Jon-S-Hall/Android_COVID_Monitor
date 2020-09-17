package com.example.bucovidmonitor.data.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SurveyDatabase {

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static void WriteData(String uid, String email, boolean[] surveyResult, boolean positiveSymptom){
        String TAG = "DatabaseActivity";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        Map<String,Object> user = new HashMap<>();
        user.put("uid", uid);
        user.put("email", email);
        user.put("date", Calendar.getInstance().getTime());
        user.put("q1", surveyResult[0]);
        user.put("q2", surveyResult[1]);
        user.put("q3", surveyResult[2]);
        user.put("q4", surveyResult[3]);
        user.put("q5", surveyResult[4]);
        user.put("q6", surveyResult[5]);
        user.put("q7", surveyResult[6]);
        user.put("q8", surveyResult[7]);
        user.put("symptomatic", positiveSymptom);

        // Add a new document with a generated ID
        db.collection("SymptomSurvey")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {


                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
         });

        // Create a new user with a first, middle, and last name

    }

    public void ReadData(){
        db.collection("SymptomSurvey")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int numSurveys = task.getResult().size();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                            }
                        }
                    }
                });

    }

}
