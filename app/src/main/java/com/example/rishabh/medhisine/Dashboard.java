package com.example.rishabh.medhisine;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
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
}
