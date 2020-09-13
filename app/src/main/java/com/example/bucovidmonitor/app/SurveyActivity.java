package com.example.bucovidmonitor.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.bucovidmonitor.R;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.TextView;

public class SurveyActivity extends AppCompatActivity {

    int stringIDList[] = {R.string.q2, R.string.q3, R.string.q4, R.string.q5, R.string.q6, R.string.q7, R.string.q8};
    int stringListCount;

    private RadioGroup mYesNo;
            Button nextBtn;
            
    @Override
     public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_1);

        mYesNo = (RadioGroup)findViewById(R.id.YesNo);
        mYesNo.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton selected = (RadioButton)group.findViewById(checkedId);
                    }

                }
        );

        nextBtn = findViewById(R.id.next);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               TextView nextQ = (TextView)findViewById(R.id.question);
                // nextQ.setText("Coughing, not related to a chronic condition");
               int id = v.getId();
               if(id == R.id.next && stringListCount < stringIDList.length - 1)
                   stringListCount++;

               nextQ.setText(stringIDList[stringListCount]);
               if(stringListCount == 6) {
                   nextBtn.setText("Finish");
                   nextBtn.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                       }
                   });
               }
            }

        });
        }
    }
