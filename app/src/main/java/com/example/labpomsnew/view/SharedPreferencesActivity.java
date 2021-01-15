package com.example.labpomsnew.view;

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class SharedPreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SharedPreferenceFragment()).commit();
    }
}