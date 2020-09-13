package com.example.bucovidmonitor.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bucovidmonitor.R;

import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class HomeScreen extends AppCompatActivity {

    // private Button surveyBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        final Button surveyBtn = findViewById(R.id.button2);
        surveyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), SurveyActivity.class));
            }
        });

    }
}
