package com.example.mateusz.practice_android.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mateusz.practice_android.R;
import com.example.mateusz.practice_android.models.Technology;

public class ShowTechnologyFragment extends Fragment {

    private Technology technology;
    private ImageView technologyLogo;
    private TextView technologyDescription;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            technology = (Technology) savedInstanceState.get("technology");
        }
        return inflater.inflate(R.layout.fragment_show_technology, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        technologyLogo = view.findViewById(R.id.show_technology_icon);
        technologyDescription = view.findViewById(R.id.show_technology_description);
        technologyLogo.setImageResource(technology.getIconId());
        technologyDescription.setText(technology.getDescription());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("technology", technology);
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }
}
