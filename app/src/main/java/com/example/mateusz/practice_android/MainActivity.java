package com.example.mateusz.practice_android;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;

import com.example.mateusz.practice_android.fragments.HomeFragment;
import com.example.mateusz.practice_android.fragments.ShowListFragment;
import com.example.mateusz.practice_android.interfaces.Categorized;
import com.example.mateusz.practice_android.models.Technology;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements ShowListFragment.TechnologiesListListener {
    private ShareActionProvider shareActionProvider;
    private String[] categories;
    private ActionBarDrawerToggle drawerToggle;
    private int currentPosition = 0;
    private static final String CURRENT_POSITION_TAG = "currentPosition";
    private static final String VISIBLE_FRAGMENT_TAG = "visibleFragment";

    @BindView(R.id.drawer_list)
    ListView drawerList;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    public void itemClicked(Technology technology) {
        Intent intent = new Intent(this, ShowTechnologyActivity.class);
        intent.putExtra(ShowTechnologyActivity.TECHNOLOGY_TAG, technology);
        startActivity(intent);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            selectItem(position);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        categories = getResources().getStringArray(R.array.categories);
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, categories));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(CURRENT_POSITION_TAG);
        }
        presetActionBar();
        presetDrawerToggle();
        selectItem(currentPosition);
        getFragmentManager().addOnBackStackChangedListener(() -> {
            FragmentManager fragmentManager = getFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag(VISIBLE_FRAGMENT_TAG);
            if (fragment instanceof Categorized) {
                Categorized categorizedFragment = (Categorized) fragment;
                currentPosition = categorizedFragment.getCategoryId();
                setActionBarTitle(currentPosition);
                drawerList.setItemChecked(currentPosition, true);
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        menu.findItem(R.id.action_share).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_POSITION_TAG, currentPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        setShareActionProviderIntent(getString(R.string.action_share_message));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_make_note:
                Intent intent = new Intent(this, NoteActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void presetActionBar() {
        setActionBarTitle(currentPosition);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    private void presetDrawerToggle() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void selectItem(int position) {
        currentPosition = position;
        Categorized fragment = position != 0 ? new ShowListFragment() : new HomeFragment();
        fragment.setCategoryId(position);
        replaceFragment((Fragment) fragment);
        setActionBarTitle(position);
        drawerLayout.closeDrawer(drawerList);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment, VISIBLE_FRAGMENT_TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void setActionBarTitle(int position) {
        String title = position == 0 ? getResources().getString(R.string.app_name) : categories[position];
        getActionBar().setTitle(title);
    }

    private void setShareActionProviderIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }
}
