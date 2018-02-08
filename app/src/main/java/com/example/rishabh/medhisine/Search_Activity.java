package com.example.rishabh.medhisine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Search_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);


        AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(R.id.auto_complete);

        ArrayList<String> med = new ArrayList<String>();

        med.add("Abarelix");
        med.add("aaafsdfsf");
        med.add("aasdfert");
        med.add("aadfgdfg");
        med.add("aasgfdfg");
        med.add("aadfgdfg");
        med.add("aafdgdfg");
        med.add("aaerteyt");
        med.add("aazxcvxcv");
        med.add("aawqweq");
        med.add("asdasdad");
        med.add("adtw4e");
        med.add("Aabaareaalix");
        med.add("aasfderga");
        med.add("aaeryrtyr");
        med.add("aartyhrgh");
        med.add("aaetyrhfg");
        med.add("aafgfbvcb");
        med.add("aartgfbdcfv");
        med.add("sasdasda");
        med.add("aasdfsdfsdf");
        med.add("aaqweqwe");
        med.add("aazxczxc");
        med.add("aaaqwe");
        med.add("aaqeqweqw");
        med.add("Abciximab");
        med.add("asd");
        med.add("gfhfgh");
        med.add("Abalix");
        med.add("rtyrty");
        med.add("dfgdfg");
        med.add("ghjhn");
        med.add("retertwsdf");
        med.add("fdghdfgh");
        med.add("sdfgdfg");
        med.add("fghdfghfh");
        med.add("ghjghj");
        med.add("mhmghm");
        med.add("234234f");
        med.add("gffghfj");
        med.add("sdfdv");
        med.add("asdwqw");
        med.add("aweawe");
        med.add("hthrth");
        med.add("asdawe");
        med.add("tyhjtyjtyj");
        med.add("sdfs4er");
        med.add("xcvcvbcb");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, med);
        actv.setAdapter(adapter);

    }




}
