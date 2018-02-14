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

    //String apiKey = "iktlQK7yrqU-SQJuf6cy2u37AkAUGEO9KEkzOkgFex"; //rishabhgupta556
    //String apiKey = "tmta8AyNBPU-qjXVFWioZYnvq5nk0oWL5QtBquKgYX"; //rishabh.rg149
    String apiKey = "197929A4VYUZviW7jD5a81ce6a";



    //http://api.msg91.com/api/sendhttp.php?sender=MSGIND&route=4
    // &mobiles=919131593762&authkey=197929A4VYUZviW7jD5a81ce6a&country=91&message=%22Your%20OTP%20is%20%22

    public static String otp;


    //https://api.textlocal.in/send/?apiKey=iktlQK7yrqU-SQJuf6cy2u37AkAUGEO9KEkzOkgFex
    // &numbers=919827539106&message=%22this%20is%20the%20free%20sms%22&sender=TXTLCL


    @Override
    protected String doInBackground(String... strings) {


        Random r = new Random(System.currentTimeMillis());
        int o = 10000 + r.nextInt(20000);
        otp = Integer.toString(o);
        Log.e("OTP", otp);
        String message = "Your One Time Password for Activating your Medicine App Account is "+otp+ " -Team EngiNerds";

        try {

            otp_data += "?sender=NGNRDS";
            otp_data += "&route=4";
            otp_data += "&mobiles=91" + URLEncoder.encode(sua.phones, "UTF-8");
            otp_data += "&authkey=" + URLEncoder.encode(apiKey, "UTF-8");
            otp_data += "&country=91";
            otp_data += "&message=" + URLEncoder.encode(message, "UTF-8");

            otp_link = "http://api.msg91.com/api/sendhttp.php" + otp_data;

            Log.e("otp",otp_link);

            URL url = new URL(otp_link);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            Log.e("result otp",result);
        }
        catch (Exception e) {

            new String("Exception: " + e.getMessage());
        }
        return result;
    }


}




