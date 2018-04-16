package com.example.rishabh.medhisine;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class FetchMedicines extends AsyncTask<String, Void , String> {
    private Context context;
    public static JSONArray json_arr;


    public FetchMedicines(Context context){
        this.context = context;
    }


    public static ArrayList<String> med = new ArrayList<String>();

    @Override
    protected String doInBackground(String...arg0) {
        String link;
        String result;
        BufferedReader bufferedReader;

        try{

            link = "http://rishabh2.000webhostapp.com/med.php";

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
        try{

            json_arr = new JSONArray(result);
            for(int i=0; i<json_arr.length(); i++)
            {
                JSONObject json_data = json_arr.getJSONObject(i);

                med.add(json_data.getString("Name"));
            }
            Intent i = new Intent(context, Search_Activity.class);
            context.startActivity(i);
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
