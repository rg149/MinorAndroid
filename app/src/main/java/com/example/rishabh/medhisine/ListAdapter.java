package com.example.rishabh.medhisine;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


class ListAdapter extends ArrayAdapter<prescription> {

    private int mResourceColour;

    public ListAdapter(Context context, ArrayList<prescription> words, int ResourceColor) {
        super(context, 0, words);
        mResourceColour = ResourceColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.prescription_list, parent, false);
        }

        TextView setdate = (TextView) listItemView.findViewById(R.id.date);
        setdate.setText(extract_prescription.date);

        // Find the TextView in the list_item.xml layout with the ID default_text_view.

        TextView setdoctor = (TextView) listItemView.findViewById(R.id.doctor);
        setdoctor.setText(extract_prescription.doctor);

        TextView setpresid = (TextView) listItemView.findViewById(R.id.p_id);
        setpresid.setText(extract_prescription.p_id);



        View text_container = listItemView.findViewById(R.id.textcontainer);
        int color = ContextCompat.getColor(getContext(), mResourceColour);
        return listItemView;
    }
}