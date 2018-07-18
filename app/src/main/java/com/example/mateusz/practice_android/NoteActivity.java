package com.example.mateusz.practice_android;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class NoteActivity extends Activity {

    private static final String NOTE_CONTENT_TAG = "noteConeten";
    private String noteContent;
    private EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        noteEditText = (EditText) findViewById(R.id.notes);
        if (savedInstanceState != null) {
            noteContent = savedInstanceState.getString(NOTE_CONTENT_TAG);
            noteEditText.setText(noteContent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        noteContent = noteEditText.getText().toString();
        outState.putString(NOTE_CONTENT_TAG, noteContent);
    }
}
