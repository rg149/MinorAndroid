package com.example.rishabh.medhisine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;

public class MyPrescriptions extends AppCompatActivity {
    public static int f = 0;
    public ListView listView;
    public static JSONObject temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_prescriptions);
        final ListAdapter adapter = new ListAdapter(this, extract_prescription.prescriptions, R.color.barcolor);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    temp = extract_prescription.json_arr.getJSONObject(i);
                    new pres_conn(MyPrescriptions.this).execute(temp.getString("P_id"));
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                    pres_conn.medicine_list.clear();
                }
        });
    }
}
