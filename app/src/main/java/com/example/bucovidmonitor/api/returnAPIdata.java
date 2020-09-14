package com.example.bucovidmonitor.api;

public class returnAPIdata {

    private String state;
    private int cases;
    private int todayCases;
    private int active;
    private int casesPerOneMillion;

    public String getState() {
        return state;
    }

    public int getCases() {
        return cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getActive() {
        return active;
    }

    public int getCasesPerOneMillion() {
        return casesPerOneMillion;
    }
}
