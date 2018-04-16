package com.example.rishabh.medhisine;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

public class Fetch_substitutes extends AsyncTask<String , Void, String> {

    public static ArrayList<substitute> substitutes = new ArrayList<substitute>();

    public static String medname;
    public static String brandname;
    public static String price;
    public static String packsize;
    public static String subs_array;
    public static JSONArray json_arr;

    ProgressDialog progressDialog;

    public Context context;

    public Fetch_substitutes(Context context)
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

        String name=arg0[0];
        String data ="";
        String link;
        BufferedReader bufferedReader;

        try{

            data += "?name='" + URLEncoder.encode(name,"UTF-8")+"'";
            link = "http://rishabh2.000webhostapp.com/substitute.php" + data;
            Log.e("substitute link",link);

            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            subs_array = bufferedReader.readLine();
            return subs_array;
        }

        catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    public void onPostExecute (String subs_array)
    {
        Log.e("Substitute array", subs_array);

        try{
            json_arr = new JSONArray(subs_array);
            for(int i=0; i<json_arr.length(); i++) {
                JSONObject json_data = json_arr.getJSONObject(i);

                medname = json_data.getString("Name");
                brandname = json_data.getString("BrandName");
                price = json_data.getString("Price");
                packsize = json_data.getString("PackSize");

                substitutes.add(new substitute(medname, brandname, price, packsize));
                progressDialog.cancel();
            }
            Intent intent = new Intent(context, Substitute_Page.class);
            context.startActivity(intent);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
