package com.example.mateusz.practice_android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.mateusz.practice_android.R;
import com.example.mateusz.practice_android.databinding.RowTechnologyBinding;
import com.example.mateusz.practice_android.models.Technology;

/**
 * Created by Mateusz on 7/7/2018.
 */

public class MyAdapter extends ArrayAdapter<Technology> {

    public MyAdapter(@NonNull Context context) {
        super(context, R.layout.row_technology);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            RowTechnologyBinding binding = RowTechnologyBinding.inflate(LayoutInflater.from(getContext()), parent, false);
            convertView = binding.getRoot();
            holder = new Holder(binding);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.bind(getItem(position));
        return convertView;
    }

    private class Holder {
        private RowTechnologyBinding binding;

        Holder(@NonNull RowTechnologyBinding binding) {
            this.binding = binding;
        }

        void bind(Technology technology) {
            binding.setTechnology(technology);
            binding.executePendingBindings();
        }
    }
}
