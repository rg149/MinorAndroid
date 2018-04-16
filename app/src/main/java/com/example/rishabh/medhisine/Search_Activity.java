package com.example.rishabh.medhisine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Search_Activity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    public static JSONObject temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);

        EditText editText = (EditText)findViewById(R.id.auto_complete);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FetchMedicines.med);
        ListView listView = (ListView)findViewById(R.id.listmed);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    temp = FetchMedicines.json_arr.getJSONObject(i);
                    new Fetch_substitutes(Search_Activity.this).execute(temp.getString("Name"));
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                Fetch_substitutes.substitutes.clear();
            }
        });


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Search_Activity.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
