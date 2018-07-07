package com.example.mateusz.practice_android.fragments;


import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        int id = 0;
        if (savedInstanceState != null) {
            categoryId = savedInstanceState.getInt("id");
        }
        switch (categoryId) {
            case 1:
                id = R.array.js_spa_frameworks;
                break;
            case 2:
                id = R.array.java_web_frameworks;
                break;
        }
        data = new ArrayList<>();
        for (String technologyName : getResources().getStringArray(id)) {
            data.add(new Technology(0, technologyName, ""));
        }
        myAdapter = new MyAdapter(inflater.getContext());

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(id));
//        setListAdapter(adapter);

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
