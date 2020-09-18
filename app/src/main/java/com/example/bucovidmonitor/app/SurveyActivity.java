package com.example.bucovidmonitor.app;


import androidx.appcompat.app.AlertDialog;

import com.example.bucovidmonitor.data.model.SurveyDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.bucovidmonitor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.TextView;



import static com.example.bucovidmonitor.data.model.SurveyDatabase.WriteData;



public class SurveyActivity extends AppCompatActivity {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    int stringIDList[] = {R.string.q2, R.string.q3, R.string.q4, R.string.q5, R.string.q6, R.string.q7, R.string.q8};
    boolean responseVars[] = new boolean[8];
    int stringListCount;
    boolean positiveSymptom = false;
    private RadioGroup mYesNo;
            RadioButton yesBtn;
            Button nextBtn;




    @Override
     public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_1);


        mYesNo = (RadioGroup)findViewById(R.id.YesNo);
        yesBtn = findViewById(R.id.radioButton1);
        mYesNo.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton selected = (RadioButton)group.findViewById(checkedId);
                    }

                }
        );


        mYesNo = findViewById(R.id.YesNo);
        nextBtn = findViewById(R.id.next);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               TextView nextQ = (TextView)findViewById(R.id.question);

                if(mYesNo.getCheckedRadioButtonId() == -1) {
                    AlertDialog.Builder picksomething =  new AlertDialog.Builder(SurveyActivity.this);
                    picksomething.setMessage("Please select either Yes/No");
                    picksomething.show();
                }
                else {
                    int selected = mYesNo.getCheckedRadioButtonId();
                    if (selected == R.id.radioButton1) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SurveyActivity.this);
                        builder.setMessage(R.string.alertContent);
                        builder.setTitle("Alert");

                        builder.show();
                    }


                    RadioButton yourans = findViewById(mYesNo.getCheckedRadioButtonId());
                    SharedPreferences sharedPreferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    RadioButton mYes = findViewById(R.id.radioButton1);
                    if(mYes.isChecked()) {
                                myEdit.putString("answer", yourans.getText().toString());
                                myEdit.commit();
                            }



                    int id = v.getId();
                    if (id == R.id.next && stringListCount < stringIDList.length - 1) {
                        stringListCount++;
                    }

                    nextQ.setText(stringIDList[stringListCount]);
                    if (stringListCount == 6) {
                        nextBtn.setText("Finish");
                        nextBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                            }
                        });
                    }
                }

               int id = v.getId();
                if(yesBtn.isChecked()){
                    positiveSymptom = true;
                }
               if(id == R.id.next && stringListCount < stringIDList.length - 1) {
                   responseVars[stringListCount] = yesBtn.isChecked(); //set the corresponding bool to false if not checked.
                   stringListCount++;
               }
               nextQ.setText(stringIDList[stringListCount]);
               if(id == R.id.next && stringListCount == 6) {
                   responseVars[stringListCount] = yesBtn.isChecked(); //set the corresponding bool to false if not checked.
                   nextBtn.setText("Finish");

                   WriteData(user.getUid(), user.getEmail(), responseVars, positiveSymptom);

                   nextBtn.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                       }
                   });
               }
            }

        });







         /* mYesNo.setOnCheckedChangeListener(
                (group, checkedId) -> {
                    // RadioButton selected = (RadioButton)group.findViewById(checkedId);
                    class AlertingDialog extends DialogFragment {
                        public void buildDialog() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SurveyActivity.this);
                            builder.setMessage(R.string.alertContent);
                            builder.setTitle("Alert");

                            builder.show();
                        }
                    }
                }
        ); */
       // int selectedId = mYesNo.getCheckedRadioButtonId();
       /* RadioButton MYes;
        MYes = findViewById(R.id.radioButton2);
        if(MYes.isChecked()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.alertContent);
            builder.setTitle("Alert");
            builder.show();
        } */

    }
    }
