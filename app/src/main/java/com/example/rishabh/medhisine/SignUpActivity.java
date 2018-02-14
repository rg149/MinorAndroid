package com.example.rishabh.medhisine;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SignUpActivity extends AppCompatActivity {

    public static String phones;
    public static String name;
    public static String email;
    public static String passw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void Register_Click(View view) {


        EditText emai = (EditText) findViewById(R.id.editmail);
        EditText pass = (EditText) findViewById(R.id.editpass_signup);
        EditText confirmpass = (EditText) findViewById(R.id.confirm_pass);
        EditText nam = (EditText) findViewById(R.id.editname);
        EditText phon = (EditText) findViewById(R.id.editphone_signup);


        email = emai.getText().toString();
        passw = pass.getText().toString();
        String conpass = confirmpass.getText().toString();
        name = nam.getText().toString();
        phones = phon.getText().toString();

        char[] emailc = email.toCharArray();
        check_values(email, name, conpass,  passw, phones, emailc);


    }

    public void check_values(String email, String names, String conpass, String passw, String phones, char[] emailc)
    {
        int s = 0;
        int v = 0;

        for (int i = 0; i < email.length(); i++) {
            if (emailc[i] == '@') {
                s++;
                break;
            }
        }

        if (names.length() == 0) {
            namemsg();
        } else {
            v++;
            deletenamesmsg();
        }

        if (s != 1) {
            mailmsg();
        } else {
            deletemsgmail();
            v++;
        }

        if (passw.length() == 0) {
            passnull();
        } else {
            deletemsgpass();
            v++;
        }

        if (passw.equals(conpass)) {
            deletemsgpass();
            v++;
        } else passmsg();


        if (phones.length() != 10) {
            phonemesg();
        } else {
            v++;
            deletephonemsg();
        }

        if (v == 5 || v == 6) {
            new CheckUser_Existence(this).execute(phones);

        }
    }

    public void mailmsg()
    {
        TextView mailm = (TextView)findViewById(R.id.mailmsg);
        mailm.setText("Enter Valid Email!");
        mailm.setTextColor(Color.RED);
        mailm.setVisibility(View.VISIBLE);
    }

    public void passmsg()
    {
        TextView passm = (TextView)findViewById(R.id.passmsg);
        passm.setText("Passwords don't Match! Try Again");
        passm.setTextColor(Color.RED);
        passm.setVisibility(View.VISIBLE);
    }

    public void deletemsgmail()
    {
        TextView mailm = (TextView)findViewById(R.id.mailmsg);
        mailm.setVisibility(View.INVISIBLE);
    }

    public void deletemsgpass()
    {
        TextView passma = (TextView)findViewById(R.id.passmsg);
        passma.setVisibility(View.INVISIBLE);
    }

    public void phonemesg()
    {
        TextView phonemes = (TextView)findViewById(R.id.phonemsg);
        phonemes.setText("Please Enter a Valid Phone No.!");
        phonemes.setTextColor(Color.RED);
        phonemes.setVisibility(View.VISIBLE);

    }

    public void deletephonemsg()
    {
        TextView phonemess = (TextView)findViewById(R.id.phonemsg);
        phonemess.setVisibility(View.INVISIBLE);
    }

    public void namemsg()
    {
        TextView namev = (TextView)findViewById(R.id.namemsg);
        namev.setText("Name Field Cannot be left blank!");
        namev.setTextColor(Color.RED);
        namev.setVisibility(View.VISIBLE);
    }

    public void deletenamesmsg()
    {
        TextView namevi = (TextView)findViewById(R.id.namemsg);
        namevi.setVisibility(View.INVISIBLE);
    }

    public void passnull()
    {
        TextView passaa = (TextView)findViewById(R.id.passmsg);
        passaa.setText("Enter Valid Password!");
        passaa.setVisibility(View.VISIBLE);
        passaa.setTextColor(Color.RED);
    }

}
