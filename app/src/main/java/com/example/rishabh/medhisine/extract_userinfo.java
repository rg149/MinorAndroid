package com.example.rishabh.medhisine;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Set;

public class extract_userinfo extends AsyncTask<String, Void, String>{

    public static String user_name;
    public String user_Email;
    public String user_pass;
    public String user_phone;
    public String user_id;

    public Context context;

    public extract_userinfo(Context context)
    {
        this.context=context;
    }

    public String doInBackground(String... arg0) {

        String phone=arg0[0];
        String data ="";
        String link;
        String result;
        BufferedReader bufferedReader;


        try{
            data += "?Phone=" + URLEncoder.encode(phone,"UTF-8");

            link = "http://rishabh2.000webhostapp.com/getjson.php" + data;

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
            Log.e("result",result);
            String jsonstr = result;

            try{
                JSONObject jsonobj = new JSONObject(jsonstr);
                user_name = jsonobj.getString("Name");
                String user_Email = jsonobj.getString("EMail");
                String user_pass = jsonobj.getString("Passw");
                String user_phone = jsonobj.getString("Phone");
                String user_id = jsonobj.getString("_id");


                Log.e("username", user_name);

            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
       }
    }

