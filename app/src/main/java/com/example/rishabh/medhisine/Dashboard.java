package com.example.rishabh.medhisine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    //extract_userinfo r = new extract_userinfo(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView tv = (TextView)findViewById(R.id.topbar);
        tv.setText("Welcome, "+extract_userinfo.user_name);
    }

    public void nearby_pharma(View view)
    {
        Uri gmaps_uri = Uri.parse("http://maps.google.co.in/maps?q=medical shops");
        Intent gmaps_intent = new Intent(Intent.ACTION_VIEW, gmaps_uri);
        startActivity(gmaps_intent);
    }
    public void search_medicine(View view)
    {
        Intent i = new Intent(this, Search_Activity.class);
        startActivity(i);
    }
    public void my_prescriptions(View view)
    {
        new extract_prescription(this).execute(extract_userinfo.user_id);

        Intent i = new Intent(this, MyPrescriptions.class);
        startActivity(i);
    }
}
