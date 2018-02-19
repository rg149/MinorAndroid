package com.example.rishabh.medhisine;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class preschart extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preschart);

        TextView textView = (TextView)findViewById(R.id.presdate);
        try {
            textView.setText(MyPrescriptions.temp.getString("Date"));
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        TextView textView2 = (TextView)findViewById(R.id.presdoctor);
        try {
            textView2.setText(MyPrescriptions.temp.getString("Doctor"));
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        final Medicine_ListAdapter adapter = new Medicine_ListAdapter(this, pres_conn.medicine_list, R.color.barcolor);

        ListView listView2 = (ListView) findViewById(R.id.listmed1);
        listView2.setAdapter(adapter);
    }
}
































