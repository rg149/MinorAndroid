package com.example.rishabh.medhisine;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class extract_prescription extends AsyncTask<String , Void, String>{

    public static ArrayList<prescription> prescriptions = new ArrayList<prescription>();
    public static String doctor;
    public static String date;
    public static String p_id;
    public static String pres_array;
    public static JSONArray json_arr;


    ProgressDialog progressDialog;

    public Context context;

    public extract_prescription(Context context)
    {
        this.context=context;
    }

    public void onPreExecute()
    {

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
    }

    public String doInBackground(String... arg0) {

        String phone=arg0[0];
        String data ="";
        String link;
        BufferedReader bufferedReader;

        try{

            data += "?Phone=" + URLEncoder.encode(phone,"UTF-8");

            link = "http://rishabh2.000webhostapp.com/getpres.php" + data;

            Log.e("pres link",link);


            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            pres_array = bufferedReader.readLine();

            return pres_array;
        }

        catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    public void onPostExecute (String pres_array)
    {
        //Log.e("prescription array",pres_array);

        try{

            json_arr = new JSONArray(pres_array);
            for(int i=0; i<json_arr.length(); i++)
            {
                JSONObject json_data = json_arr.getJSONObject(i);

                date = json_data.getString("Date");
                doctor =  json_data.getString("Doctor");
                p_id = json_data.getString("P_id");
                //Log.e("date", date);
                //Log.e("doctor",doctor);
                //Log.e("Pid",p_id);

                prescriptions.add(new prescription(date, doctor));
                progressDialog.cancel();
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}

