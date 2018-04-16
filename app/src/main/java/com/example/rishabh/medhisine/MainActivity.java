package com.example.rishabh.medhisine;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }
    public void signup_page(View view)
    {
        TextView register = (TextView) findViewById(R.id.signup_button);
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    public void sign_in(View view) {
        EditText lp = (EditText) findViewById(R.id.editphone);
        EditText lpass = (EditText) findViewById(R.id.editpassword);

        String login_phone = lp.getText().toString();
        String login_pass = lpass.getText().toString();

        new login_check(this).execute(login_phone, login_pass);
    }

}

