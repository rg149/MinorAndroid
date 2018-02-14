package com.example.rishabh.medhisine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MyPrescriptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_prescriptions);

        ArrayList<prescription> prescriptions = new ArrayList<prescription>();
        extract_prescription ep = new extract_prescription(this);


        //prescriptions.add(new prescription(ep.date, ep.doctor));
        prescriptions.add(new prescription(extract_prescription.date, extract_prescription.doctor));
        //prescriptions.add(new prescription("17-02-2018", "Dr. Pandey"));
        //prescriptions.add(new prescription("20-02-2018", "Dr. Dawar"));

        ListAdapter adapter = new ListAdapter(this,prescriptions, R.color.barcolor);
        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);




    }
}
