package com.example.rishabh.medhisine;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class login_check extends AsyncTask<String, Void, String>{

    public static String phone;
    public static JSONObject temp2;

    private Context context;
    Activity activity;


    public ProgressDialog progressdialog;

    public login_check(Context context)
    {
        this.context=context;
    }

    public void onPreExecute()
    {
        progressdialog = new ProgressDialog(context);
        progressdialog.setMessage("Signing in..");
        progressdialog.show();
    }


    public String doInBackground(String... arg0) {

        phone=arg0[0];
        String pass = arg0[1];
        String link;
        String result;
        String data="";

        BufferedReader bufferedReader;


        try{
                data += "?Phone=" + URLEncoder.encode(phone,"UTF-8");
                data += "&Passw='" +URLEncoder.encode(pass,"UTF-8")+"'";

                link = "http://rishabh2.000webhostapp.com/login.php" + data;
                //Log.e("Login Check link", data);

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
        public void onPostExecute(String result)
        {
            progressdialog.cancel();
            String jsonstr = result;

            try{
                JSONObject jsonobj = new JSONObject(jsonstr);
                String valid = jsonobj.getString("status");

                if(valid.equals("Invalid"))
                {
                    Toast t = Toast.makeText(context, "Invalid Username or Password",Toast.LENGTH_LONG);
                    t.show();
                }
                if(valid.equals("Valid"))
                {
                    new extract_userinfo(context).execute(phone);
                }
            }

            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

}
