package com.example.rishabh.medhisine;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

public class LoginWait extends AppCompatActivity {

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_wait);

        progressDialog = new ProgressDialog(LoginWait.this);
        progressDialog.setMessage("Loading.....");
        progressDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();
                new extract_userinfo(LoginWait.this).execute(SplashScreen.uid);
            }
        }, 3000);
    }
}
