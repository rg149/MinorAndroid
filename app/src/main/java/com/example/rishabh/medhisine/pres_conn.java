package com.example.rishabh.medhisine;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class pres_conn extends AsyncTask<String, Void, String>{

    public static ArrayList<medlist> medicine_list = new ArrayList<medlist>();

    public static String medname;
    public static String dosage;
    public static String duration;

    private Context context;

    public pres_conn(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String...arg) {

        String p_id = arg[0];
        String data ="";
        String link;
        String result;
        BufferedReader bufferedReader;

        try{
            data += "?P_id=" + URLEncoder.encode(p_id,"UTF-8");
            link = "http://rishabh2.000webhostapp.com/medicines.php" + data;

            //Log.e("medlist link",link);
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
        //Log.e("prescription array",result);

        try{
            JSONArray json_arr = new JSONArray(result);
            for(int i=0; i<json_arr.length(); i++) {
                JSONObject json_data = json_arr.getJSONObject(i);

                medname = json_data.getString("name");
                dosage = json_data.getString("Dosage");
                duration = json_data.getString("Duration");

//                Log.e("medicine", medname);
//                Log.e("dose", dosage);
//                Log.e("duration", duration);

                medicine_list.add(new medlist(medname, dosage, duration));
            }
            Intent intent = new Intent(context, preschart.class);
            context.startActivity(intent);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}

