package com.example.bucovidmonitor.app;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bucovidmonitor.R;
import com.example.bucovidmonitor.api.CovidApi;
import com.example.bucovidmonitor.api.CovidApiTemplate;

import org.w3c.dom.Text;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidData extends AppCompatActivity {
    private TextView curState;
    private TextView curActiveCases;
    private TextView todayCases;
    private TextView casePerMil;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coviddata);

        curState = findViewById(R.id.curState);
        curActiveCases = findViewById(R.id.curActiveCases);
        todayCases = findViewById(R.id.curNewCases);
        casePerMil = findViewById(R.id.casespermil);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidApi covidApi = retrofit.create(CovidApi.class);

        Call<List<CovidApiTemplate>> call = covidApi.getStateData();

        call.enqueue(new Callback<List<CovidApiTemplate>>() {
            @Override
            public void onResponse(Call<List<CovidApiTemplate>> call, Response<List<CovidApiTemplate>> response) {
                if(!response.isSuccessful()){
                    curState.setText("Code: " + response.code());
                    return;
                }

                List<CovidApiTemplate> states = response.body();

                for (CovidApiTemplate state : states){
                    if(state.getState().equals("Massachusetts")){
                        curState.append(state.getState());
                        curActiveCases.append(" " + String.valueOf(state.getActive()));
                        todayCases.append(" " + String.valueOf(state.getTodayCases()));
                        casePerMil.append(String.valueOf(state.getCasesPerOneMillion()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CovidApiTemplate>> call, Throwable t) {
                curState.setText(t.getMessage());
            }
        });

    }

}
