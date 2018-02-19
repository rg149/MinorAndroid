package com.example.rishabh.medhisine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Set;

import javax.sql.StatementEvent;

public class extract_userinfo extends AsyncTask<String, Void, String>{

    public static String jsonstruser;
    public static String user_Email;
    public static String user_pass;
    public static String user_phone;
    public static String user_id;
    public static JSONObject jsonobj;
    public static String user_result;

    public Context context;

    public extract_userinfo(Context context)
    {
        this.context=context;
    }

    @Override
    public String doInBackground(String... arg0) {

        String phone=arg0[0];
        String data ="";
        String link;
        String result;
        BufferedReader bufferedReader;

        try{
            data += "?Phone=" + URLEncoder.encode(phone,"UTF-8");

            link = "http://rishabh2.000webhostapp.com/getjson.php" + data;

            Log.e("linkuserifo",link);

            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            user_result = bufferedReader.readLine();
            //Log.e("result", user_result);
            return user_result;
        }

        catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    public void onPostExecute (String user_result)
    {
        jsonstruser = user_result;

        try{
            jsonobj = new JSONObject(jsonstruser);
            //user_name = jsonobj.getString("Name");
            user_Email = jsonobj.getString("EMail");
            user_pass = jsonobj.getString("Passw");
            user_phone = jsonobj.getString("Phone");
            user_id = jsonobj.getString("_id");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
            Intent i = new Intent(context, Dashboard.class);
            context.startActivity(i);
    }
}