package com.example.rishabh.medhisine;


public class medlist {

    String medicine;
    String  dosage;
    String duration;

    public medlist(String mmedicine, String mdosage, String mduraion)
    {
        medicine = mmedicine;
        dosage = mdosage;
        duration = mduraion;
    }

    public String getMedicine()
    {
        return medicine;
    }
    public String getDosage()
    {
        return dosage;
    }
    public String getDuration()
    {
        return duration;
    }

}
