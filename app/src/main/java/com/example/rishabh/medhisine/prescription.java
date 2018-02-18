package com.example.rishabh.medhisine;


public class prescription {

    String date;
    String  doctor;

    public prescription(String mdate, String mdoctor)
    {
        date = mdate;
        doctor = mdoctor;
    }

    public String getDate()
    {
        return date;
    }
    public String getDoctor()
    {
        return doctor;
    }

}
