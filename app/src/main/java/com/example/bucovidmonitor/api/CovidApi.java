package com.example.bucovidmonitor.api;

import com.example.bucovidmonitor.app.CovidData;
import com.example.bucovidmonitor.api.returnAPIdata;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidApi {

    @GET("states")
    Call<List<CovidApiTemplate>> getStateData();
}
