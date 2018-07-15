package com.example.mateusz.practice_android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mateusz.practice_android.R;
import com.example.mateusz.practice_android.models.Technology;


/**
 * Created by Mateusz on 7/7/2018.
 */

public class MyAdapter extends ArrayAdapter<Technology> {

    LayoutInflater inflater;

    public MyAdapter(@NonNull Context context) {
        super(context, R.layout.row_technology);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Technology technology = getItem(position);
        Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_technology, parent, false);
            holder = new Holder();
            holder.technologyIcon = (ImageView) convertView.findViewById(R.id.technology_icon);
            holder.technologyName = (TextView) convertView.findViewById(R.id.technology_name);
            convertView.setTag(holder);
        } else {
            holder = (Holder)convertView.getTag();
        }
        holder.technologyIcon.setImageResource(technology.getIconId());
        holder.technologyName.setText(technology.getName());
        return convertView;
    }

    private class Holder {
        ImageView technologyIcon;
        TextView technologyName;
    }

}
