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


class Medicine_ListAdapter extends ArrayAdapter<medlist> {

    private int mResourceColour;

    public Medicine_ListAdapter(Context context, ArrayList<medlist> words, int ResourceColor) {
        super(context, 0, words);
        mResourceColour = ResourceColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.med_listitem, parent, false);
        }

        medlist current = getItem(position);

        TextView setmedicine = (TextView) listItemView.findViewById(R.id.mediname);
        setmedicine.setText(current.getMedicine());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.

        TextView setdosage = (TextView) listItemView.findViewById(R.id.dosage);
        setdosage.setText(current.getDosage());

        TextView setduration = (TextView) listItemView.findViewById(R.id.duration);
        setduration.setText(current.getDuration());


        View text_container = listItemView.findViewById(R.id.textcontainer);
        int color = ContextCompat.getColor(getContext(), mResourceColour);
        return listItemView;
    }
}