package com.example.rishabh.medhisine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.*;
import android.util.Log;

import java.util.Calendar;

public class SplashScreen extends AppCompatActivity {

    ProgressDialog dialog;
    public static String uid;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
                uid = sharedPreferences.getString("userid","0");
                Log.e("uid",uid);

                if(uid.length()<10)
                {
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                }
                else {

                    Intent intent = new Intent(SplashScreen.this, LoginWait.class);
                    startActivity(intent);
                }

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
