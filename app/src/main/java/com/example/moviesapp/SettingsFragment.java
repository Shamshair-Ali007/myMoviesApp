package com.example.moviesapp;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import java.util.prefs.Preferences;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
