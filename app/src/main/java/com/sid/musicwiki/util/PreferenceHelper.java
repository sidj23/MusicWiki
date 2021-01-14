package com.sid.musicwiki.util;

import android.content.Context;
import android.content.SharedPreferences;

import static com.sid.musicwiki.util.AppConstants.PREF_NAME;

public class PreferenceHelper {

    private static final String PREF_KEY_TAGS_DATA = "PREF_KEY_TAGS_DATA";

    private final SharedPreferences mPrefs;

    public PreferenceHelper(Context context) {
        this.mPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
}
