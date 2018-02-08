package com.example.rishabh.medhisine;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Sql_Connect extends AsyncTask<String, Void, String> {


    private Context context;

    ProgressDialog progressbar;

    public Sql_Connect(Context context) {

        this.context = context;
    }

    public void onPreExecute() {
        progressbar = new ProgressDialog(context);
        progressbar.setMessage("Registering User....");
        progressbar.show();
    }

    @Override
    protected String doInBackground(String... arg0) {

        String name = arg0[0];
        String ema = arg0[1];
        String passwd = arg0[2];
        String phone = arg0[3];

        String link;
        String data = "";
        String result = "";
        BufferedReader bufferedReader;

        try {
            try {
                data += "?Name=" + URLEncoder.encode(name, "UTF-8");
                data += "&EMail=" + URLEncoder.encode(ema, "UTF-8");
                data += "&Passw=" + URLEncoder.encode(passwd, "UTF-8");
                data += "&Phone=" + URLEncoder.encode(phone, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
                //link = "192.168.1.6/sql/localconn.php"+data;
                link="http://rishabh2.000webhostapp.com/sql.php"+data;

            try {
                URL url = new URL(link);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                result = bufferedReader.readLine();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());

        }
    }

    public void onPostExecute(String result) {

        progressbar.cancel();

        String jsonstr = result;
        if(jsonstr != null)
        {
            try{
                JSONObject jobj = new JSONObject(jsonstr);
                String query_result = jobj.getString("query_result");
                String exist = jobj.getString("existence");
                String conne = jobj.getString("conection");

                if(conne == "Connection failed")
                {
                    Toast.makeText(context,"Connection Error!",Toast.LENGTH_LONG);
                }

                if(query_result == "FAILURE")
                {
                    Toast.makeText(context,"Something Went Wrong!",Toast.LENGTH_LONG);
                }
                if(exist == "EXISTS")
                {
                    Toast.makeText(context,"User Already Exists!",Toast.LENGTH_LONG);
                }
                if(query_result == "SUCCESS" && exist == "NOTEXISTS")
                {
                    Toast.makeText(context,"User Registered Succesfully!",Toast.LENGTH_LONG);
                }
                else
                    Toast.makeText(context,"Something Went Wrong!",Toast.LENGTH_LONG);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Toast.makeText(context,"Something Went Wrong!",Toast.LENGTH_LONG);
            }
        }
        else{
            Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_LONG);
        }

    }
}
