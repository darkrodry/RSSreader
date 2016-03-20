package com.darkrodry.rssreader.preferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.darkrodry.rssreader.R;

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
