package com.example.mateusz.practice_android.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mateusz.practice_android.R;
import com.example.mateusz.practice_android.adapters.MyAdapter;
import com.example.mateusz.practice_android.interfaces.Categorized;
import com.example.mateusz.practice_android.models.Technology;

import java.util.ArrayList;

public class ShowListFragment extends Fragment implements Categorized {

    private int categoryId;
    private ListView list;
    private MyAdapter myAdapter;
    private ArrayList<Technology> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int technologiesNamesArrayId = 0;
        int technologiesLogosArrayId = 0;
        if (savedInstanceState != null) {
            categoryId = savedInstanceState.getInt("id");
        }
        switch (categoryId) {
            case 1:
                technologiesNamesArrayId = R.array.js_spa_frameworks;
                technologiesLogosArrayId = R.array.java_web_frameworks_logos;
                break;
            case 2:
                technologiesNamesArrayId = R.array.java_web_frameworks;
                technologiesLogosArrayId = R.array.java_web_frameworks_logos;
                break;
        }
        data = new ArrayList<>();
        String[] technologiesNames = getResources().getStringArray(technologiesNamesArrayId);
        TypedArray technologiesLogos = getResources().obtainTypedArray(technologiesLogosArrayId);
        for (int i = 0; i < technologiesNames.length; i++) {
            data.add(new Technology(technologiesLogos.getResourceId(i, -1), technologiesNames[i], ""));
        }
        myAdapter = new MyAdapter(inflater.getContext());
        return inflater.inflate(R.layout.fragment_show_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        list = (ListView) view.findViewById(R.id.technology_list);
        list.setAdapter(myAdapter);
        myAdapter.addAll(data);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("id", categoryId);
    }

    @Override
    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
