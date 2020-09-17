package com.example.bucovidmonitor.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.content.Intent;
import android.widget.TextView;
import android.view.View;

import com.example.bucovidmonitor.R;

import java.util.ArrayList;
import java.util.Objects;

public class Badge extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badge_view);
        View view = findViewById(R.id.bgColor);

        TextView quarStatus = findViewById(R.id.quarStatus);


        SharedPreferences shp = getSharedPreferences("SharedPrefs", 0);
        String answers = shp.getString("answer", "");


            if (answers.matches("(Yes).*")) {
                quarStatus.setText("Quarantine");
                view.setBackgroundColor(Color.rgb(255,128,0));
                shp.edit().clear().commit();
            }
            else {
                quarStatus.setText("Cleared");;
                view.setBackgroundColor(Color.rgb(31,240,31));
                // shp.edit().clear().commit();
            }

    }
}



