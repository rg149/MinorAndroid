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
        String result;
        BufferedReader bufferedReader;

        try {
                data += "?Name=" + URLEncoder.encode(name, "UTF-8");
                data += "&EMail=" + URLEncoder.encode(ema, "UTF-8");
                data += "&Passw=" + URLEncoder.encode(passwd, "UTF-8");
                data += "&Phone=" + URLEncoder.encode(phone, "UTF-8");

                //link = "192.168.1.6/sql/localconn.php"+data;
                link="http://rishabh2.000webhostapp.com/register.php"+data;
                //link = "http://enginerds.heliohost.org/localconn.php"+data;

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

    public void onPostExecute(String result) {

        progressbar.cancel();

        String jsonstr = result;

        if(jsonstr != null)
        {
            try{
                JSONObject jobj = new JSONObject(jsonstr);

                //String query_result = jobj.getString("query_result");
                String exist = jobj.getString("existence");
                //String conne = jobj.getString("conection");

                //Log.e("exists", exist);
                //Log.e("query", query_result);
                //Log.e("connection", conne);

//                if(conne == "Connection failed")
//                {
//                    Toast t= Toast.makeText(context,"Connection Error!",Toast.LENGTH_LONG);
//                    t.show();
//                }

//                if(query_result.equals("FAILURE"))
//                {
//                    Toast t= Toast.makeText(context,"Query Failure!",Toast.LENGTH_LONG);
//                    t.show();
//
//                }
                if(exist.equals("EXISTS"))
                {
                    Toast t = Toast.makeText(context,"User Already Exists!",Toast.LENGTH_LONG);
                    t.show();
                }
                if(exist.equals("NOTEXISTS"))
                {
                    Toast t = Toast.makeText(context,"User Registered Succesfully!",Toast.LENGTH_LONG);
                    t.show();
                }
                else {
                    Toast t2 = Toast.makeText(context, "Something Went Wrong! Try Agian..", Toast.LENGTH_LONG);
                    t2.show();
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Toast t3 = Toast.makeText(context,"Something Went Wrong JSON Exception!",Toast.LENGTH_LONG);
                t3.show();
            }
        }
    }
}
