package com.example.rishabh.medhisine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.IntegerRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        TextView tv = (TextView)findViewById(R.id.topbar);
        TextView tv2 = (TextView)findViewById(R.id.name);
        ImageView imageView =(ImageView)findViewById(R.id.dayicon);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 5 && timeOfDay < 12){
            tv.setText("Good Morning,");
            imageView.setImageResource(R.drawable.baseline_wb_sunny_white_48);
            imageView.setColorFilter(R.color.suncolor);
        }
        else if(timeOfDay >= 12 && timeOfDay < 16){
            tv.setText("Good Afternoon,");
            imageView.setImageResource(R.drawable.baseline_wb_sunny_white_48);
            imageView.setColorFilter(R.color.suncolor);
        }
        else if(timeOfDay >= 16 && timeOfDay < 19){
            tv.setText("Good Evening,");
            imageView.setImageResource(R.drawable.sunset);
            imageView.setColorFilter(R.color.suncolor);
        }
        else if(timeOfDay >= 21 && timeOfDay < 24){
            tv.setText("Good Evening,");
            imageView.setImageResource(R.drawable.moon);
        }
        else{
            tv.setText("Good Evening,");
            imageView.setImageResource(R.drawable.moon);
        }

        try {
            JSONObject jsonObject = new JSONObject(extract_userinfo.user_result);
            tv2.setText(""+jsonObject.getString("Name"));
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void nearby_pharma(View view)
    {
        Uri gmaps_uri = Uri.parse("http://maps.google.co.in/maps?q=medical shops");
        Intent gmaps_intent = new Intent(Intent.ACTION_VIEW, gmaps_uri);
        startActivity(gmaps_intent);
    }

    public void search_medicine(View view)
    {
        new FetchMedicines(Dashboard.this).execute();
        FetchMedicines.med.clear();
    }

    public void my_prescriptions(View view)
    {
        new extract_prescription(this).execute(extract_userinfo.user_phone);
        extract_prescription.prescriptions.clear();
    }

    public void myaccount(View view)
    {
        Intent intent = new Intent(this, MyAccount.class);
        startActivity(intent);
    }
    public void signout(View view)
    {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Dashboard.this);
        TextView myMsg = new TextView(Dashboard.this);
        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Dashboard.this);
                sharedPreferences.edit().clear().apply();
                Intent intent = new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent);
            }
        });
        alertDialog.setTitle("WARNING!");
        alertDialog.setMessage("Are you sure you want to Logout?");
        alertDialog.show();
    }


    boolean doubleBackToExitPressedOnce = false;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
