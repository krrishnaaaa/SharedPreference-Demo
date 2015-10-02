package com.pcsalt.example.sharedpreferencedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static void removeString(Context context, String key) {
        getPref(context).edit().remove(key).apply();
    }

    public static List<PrefData> getAllValues(Context context) {
        Map<String, ?> values = getPref(context).getAll();
        List<PrefData> prefDataList = new ArrayList<>();
        for (Map.Entry<String, ?> entry : values.entrySet()) {
            PrefData prefData = new PrefData();
            prefData.key = entry.getKey();
            prefData.value = entry.getValue().toString();
            prefDataList.add(prefData);
        }
        return prefDataList;
    }
}