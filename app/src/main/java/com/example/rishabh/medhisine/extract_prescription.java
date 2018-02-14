package com.example.rishabh.medhisine;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class extract_prescription extends AsyncTask<String , Void, String>{


    public static String doctor;
    public static String date;
    public static String p_id;

    public Context context;

    public extract_prescription(Context context)
    {
        this.context=context;
    }

    public String doInBackground(String... arg0) {

        String id=arg0[0];
        String data ="";
        String link;
        String result;
        BufferedReader bufferedReader;


        try{
            data += "?_id=" + URLEncoder.encode(id,"UTF-8");

            link = "http://rishabh2.000webhostapp.com/getpres.php" + data;

            Log.e("pres link",link);


            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();

            return result;
        }

        catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    public void onPostExecute (String result)
    {
        Log.e("resultpres",result);
        String jsonstr = result;

        try{
            JSONObject jsonobj = new JSONObject(jsonstr);

            doctor = jsonobj.getString("Doctor");
            date = jsonobj.getString("Date");
            p_id = jsonobj.getString("P_id");


            Log.e("doctor ", doctor);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}

