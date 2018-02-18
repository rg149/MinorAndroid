package com.example.rishabh.medhisine;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterUser extends AsyncTask<String, Void, String> {

    private Context context;
    ProgressDialog progressbar;


    public RegisterUser(Context context) {
        this.context = context;
    }


    public void onPreExecute() {
        progressbar = new ProgressDialog(context);
        progressbar.setMessage("Please wait....");
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
        String result;
        BufferedReader bufferedReader;

        try {
            data += "?Name=" + URLEncoder.encode(name, "UTF-8");
            data += "&EMail=" + URLEncoder.encode(ema, "UTF-8");
            data += "&Passw=" + URLEncoder.encode(passwd, "UTF-8");
            data += "&Phone=" + URLEncoder.encode(phone, "UTF-8");

            //link = "192.168.1.6/sql/localconn.php"+data;

            link="http://rishabh2.000webhostapp.com/RegisterUser.php"+data;
            //link = "http://enginerds.heliohost.org/localconn.php"+data;

            //Log.e("Register User link", data);

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

}
