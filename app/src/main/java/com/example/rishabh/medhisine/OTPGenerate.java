package com.example.rishabh.medhisine;


import android.content.Context;
import android.content.Intent;
import android.icu.util.BuddhistCalendar;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

public class OTPGenerate extends AsyncTask<String, Void, String>{

    public Context context;

    public OTPGenerate(Context context)
    {
        this.context = context;
    }

    SignUpActivity sua = new SignUpActivity();

    String otp_data="";
    String result;
    BufferedReader bufferedReader;
    public static String otp_link;
    String apiKey = "iktlQK7yrqU-SQJuf6cy2u37AkAUGEO9KEkzOkgFex 1";
    public static String otp;


    //https://api.textlocal.in/send/?apiKey=iktlQK7yrqU-SQJuf6cy2u37AkAUGEO9KEkzOkgFex
    // &numbers=919827539106&message=%22this%20is%20the%20free%20sms%22&sender=TXTLCL


    @Override
    protected String doInBackground(String... strings) {


        Random r = new Random(System.currentTimeMillis());
        int o = 10000 + r.nextInt(20000);
        otp = Integer.toString(o);
        Log.e("OTP", otp);
        String message = "Your One Time Password for Medicine App is "+otp+ " Jai EngiNerds :P ";

        try {

            otp_data += "?apiKey=" + URLEncoder.encode(apiKey, "UTF-8");
            otp_data += "&numbers=" + URLEncoder.encode(sua.phones, "UTF-8");
            otp_data += "&message=" + URLEncoder.encode(message, "UTF-8");
            otp_data += "&sender=TXTLCL";

            otp_link = "https://api.textlocal.in/send/" + otp_data;

            Log.e("otp",otp_link);

            URL url = new URL(otp_link);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
        }
        catch (Exception e) {

            new String("Exception: " + e.getMessage());
        }
        return result;
    }


}




