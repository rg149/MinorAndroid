package com.example.rishabh.medhisine;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


class Substitute_Adapter extends ArrayAdapter<substitute>
    {
        private int mResourceColour;

        public Substitute_Adapter(Context context, ArrayList<substitute> words, int ResourceColor) {
        super(context, 0, words);
        mResourceColour = ResourceColor;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.substitute_item, parent, false);
        }

        substitute current = getItem(position);

        TextView setmedname = (TextView) listItemView.findViewById(R.id.mediname2);
        setmedname.setText(current.getMedname());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.

        TextView setbrandname = (TextView) listItemView.findViewById(R.id.brandname2);
        setbrandname.setText(current.getBrandname());

        TextView setprice = (TextView) listItemView.findViewById(R.id.price2);
        setprice.setText(" MRP  \u20B9 "+current.getPrice()+" for "
                +current.getPacksize()+" Tablets Per Strip");

//        TextView setpacksize = (TextView) listItemView.findViewById(R.id.packsize2);
//        setpacksize.setText(current.getPacksize());

        View text_container = listItemView.findViewById(R.id.container_substitute);
        int color = ContextCompat.getColor(getContext(), mResourceColour);
        return listItemView;
    }
    }

