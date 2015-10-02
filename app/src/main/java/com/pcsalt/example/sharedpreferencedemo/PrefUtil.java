package com.pcsalt.example.sharedpreferencedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by krrishnaaaa on Oct 02, 2015
 */
public final class PrefUtil {
    private static SharedPreferences getPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveString(Context context, String key, String value) {
        getPref(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        return getPref(context).getString(key, defValue);
    }

}