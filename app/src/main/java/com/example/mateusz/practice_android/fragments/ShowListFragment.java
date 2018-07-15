package com.example.mateusz.practice_android.fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mateusz.practice_android.R;
import com.example.mateusz.practice_android.adapters.MyAdapter;
import com.example.mateusz.practice_android.interfaces.Categorized;
import com.example.mateusz.practice_android.models.Technology;

import java.util.ArrayList;

public class ShowListFragment extends Fragment implements Categorized, ListView.OnItemClickListener {

    private int categoryId;
    private ListView list;
    private MyAdapter myAdapter;
    private ArrayList<Technology> data;
    int technologiesNamesArrayId = 0;
    int technologiesLogosArrayId = 0;
    int technologiesDescriptionsArrayId = 0;
    private TechnologiesListListener listener;

    public static interface TechnologiesListListener {
        void itemClicked(Technology technology);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (TechnologiesListListener)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            categoryId = savedInstanceState.getInt("id");
            technologiesNamesArrayId = savedInstanceState.getInt("namesId");
            technologiesLogosArrayId = savedInstanceState.getInt("logosId");
            technologiesDescriptionsArrayId = savedInstanceState.getInt("descriptionsId");
        } else {
            switch (categoryId) {
                case 1:
                    technologiesNamesArrayId = R.array.js_spa_frameworks;
                    technologiesLogosArrayId = R.array.js_spa_frameworks_logos;
                    technologiesDescriptionsArrayId = R.array.java_web_frameworks_descriptions;
                    break;
                case 2:
                    technologiesNamesArrayId = R.array.java_web_frameworks;
                    technologiesLogosArrayId = R.array.java_web_frameworks_logos;
                    technologiesDescriptionsArrayId = R.array.java_web_frameworks_descriptions;
                    break;
            }
        }
        data = new ArrayList<>();
        String[] technologiesNames = getResources().getStringArray(technologiesNamesArrayId);
        String[] technologiesDescriptions = getResources().getStringArray(technologiesDescriptionsArrayId);
        TypedArray technologiesLogos = getResources().obtainTypedArray(technologiesLogosArrayId);
        for (int i = 0; i < technologiesNames.length; i++) {
            data.add(new Technology(technologiesLogos.getResourceId(i, -1), technologiesNames[i], technologiesDescriptions[i]));
        }
        technologiesLogos.recycle();
        myAdapter = new MyAdapter(inflater.getContext());
        return inflater.inflate(R.layout.fragment_show_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        list = (ListView) view.findViewById(R.id.technology_list);
        list.setAdapter(myAdapter);
        list.setOnItemClickListener(this);
        myAdapter.addAll(data);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("id", categoryId);
        outState.putInt("namesId", technologiesNamesArrayId);
        outState.putInt("logosId", technologiesLogosArrayId);
        outState.putInt("descriptionsId", technologiesDescriptionsArrayId);
    }

    @Override
    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(listener != null){
            listener.itemClicked(data.get(i));
        }
    }
}
