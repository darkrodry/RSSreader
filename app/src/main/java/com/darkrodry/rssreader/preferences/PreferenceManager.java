package com.darkrodry.rssreader.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    public static String getFeedUrl(Context context) {
        SharedPreferences preferences =
                android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("feed_source", "http://feeds.weblogssl.com/xatakandroid");
    }
}
