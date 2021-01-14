package com.example.labpomsnew;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

public class SharedPreferenceFragment extends PreferenceFragment {

    public SharedPreferenceFragment(){}

    public static SharedPreferenceFragment newInstance()
    {
        SharedPreferenceFragment fragment = new SharedPreferenceFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}
