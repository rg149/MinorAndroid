package com.example.rishabh.medhisine;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class PhoneConfirm {

    public Context context;
    public static String user_otp;

    ProgressDialog progressDialog;
    SignUpActivity sua = new SignUpActivity();

    public PhoneConfirm(Context context)
    {
        this.context = context;
    }


    public void showdialog() {


        AlertDialog.Builder b = new AlertDialog.Builder(context);

        //Setting the gravity of dialog box text
        TextView myMsg = new TextView(context);
        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);


        b.setNegativeButton("EDIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        b.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                OTPGenerate otpobj = new OTPGenerate(context);
                otpobj.execute();

                AlertDialog.Builder ab = new AlertDialog.Builder(context);


                ab.setTitle("      OTP Verification");
                ab.setMessage("\nPlease Enter the 5 digit OTP you received");


                //creating textfield for OTP dialog
                final EditText editText = new EditText(context);
                editText.setTextSize(25);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
                editText.setWidth(40);
                editText.setGravity(Gravity.CENTER);
                ab.setView(editText);


                ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        user_otp = editText.getText().toString();
                        Log.e("userotp", user_otp);
                        check_otp();
                    }
                });

                ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("Cancel", "Cancel Pressed");
                        dialogInterface.dismiss();

                    }
                });

                ab.show();
            }
        });

        b.setView(myMsg);
        b.setTitle("  NUMBER CONFIRMATION\n             " + sua.phones);

        myMsg.setText("\nIs your phone number above correct?" +
                " We will be Sending a One Time Password to this Mobile number!");

        myMsg.setPadding(20, 20, 20, 0);
        b.show();
    }


    public void check_otp()
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.show();

        AlertDialog.Builder d = new AlertDialog.Builder(context);
        d.setMessage("User Registered Successfully!");
        d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent i1 = new Intent(context, MainActivity.class);
                context.startActivity(i1);
            }
        });


        OTPGenerate otpob = new OTPGenerate(context);

        if(user_otp.equals(otpob.otp))
        {

            new RegisterUser(context).execute(sua.name, sua.email, sua.passw, sua.phones);
            d.show();
        }
        else
        {
//          Log.e("userotp", user_otp);
//          Log.e("generated otp",otpob.otp);
            Toast toast = Toast.makeText(context, "Wrong OTP! Please Check Again!", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
