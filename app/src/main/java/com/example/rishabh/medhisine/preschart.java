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

        //new pres_conn().execute();
        TextView textView = (TextView)findViewById(R.id.presdate);
        textView.setText(extract_prescription.date);

        TextView textView2 = (TextView)findViewById(R.id.presdoctor);
        textView2.setText(extract_prescription.doctor);


        final Medicine_ListAdapter adapter = new Medicine_ListAdapter(this, pres_conn.medicine_list, R.color.barcolor);

        ListView listView2 = (ListView) findViewById(R.id.listmed1);
        listView2.setAdapter(adapter);
    }
}
































