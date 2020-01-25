package com.example.haasyadentalcare.ui.Mission;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.haasyadentalcare.R;
import com.example.haasyadentalcare.ui.Review.ReviewView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends ArrayAdapter {

    ArrayList<Item> data = new ArrayList<>();

    public MyAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {
        super(context, textViewResourceId, objects);
        data = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grideview_item, null);

        CircleImageView circleImageView=(CircleImageView)v.findViewById(R.id.image);
        TextView name = (TextView) v.findViewById(R.id.txt_title);
        TextView occu = (TextView) v.findViewById(R.id.txt_occu);

        name.setText(data.get(position).getName());
        occu.setText("-"+data.get(position).getDetails());
        circleImageView.setImageResource(data.get(position).getImage());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view=new Intent(v.getContext(), ReviewView.class);
                v.getContext().startActivity(view);
            }
        });
        return v;

    }
}
