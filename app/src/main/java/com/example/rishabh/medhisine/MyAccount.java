package com.example.rishabh.medhisine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        TextView tv = (TextView)findViewById(R.id.myacc_name);
        TextView tv2 = (TextView)findViewById(R.id.myacc_phn);

        try {
            JSONObject jsonObject = new JSONObject(extract_userinfo.user_result);
            tv.setText(jsonObject.getString("Name"));
            tv2.setText(jsonObject.getString("Phone"));
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

}
