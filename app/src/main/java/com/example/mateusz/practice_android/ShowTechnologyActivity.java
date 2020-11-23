package com.example.mateusz.practice_android;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

import com.example.mateusz.practice_android.databinding.ActivityShowTechnologyBinding;
import com.example.mateusz.practice_android.models.Technology;

public class ShowTechnologyActivity extends Activity {
    public static final String TECHNOLOGY_TAG = "technology";
    private Technology technology;
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityShowTechnologyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_show_technology);
        if (savedInstanceState != null) {
            technology = (Technology) savedInstanceState.get(TECHNOLOGY_TAG);
        } else {
            technology = (Technology) getIntent().getParcelableExtra(TECHNOLOGY_TAG);
        }
        binding.setTechnology(technology);
        getActionBar().setTitle(technology.getName());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(TECHNOLOGY_TAG, technology);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_technology, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share_description);
        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        setShareActionProviderIntent(technology.getDescription());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setShareActionProviderIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }
}
