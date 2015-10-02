package com.pcsalt.example.sharedpreferencedemo;

import android.widget.TextView;

/**
 * Created by krrishnaaaa on Oct 02, 2015
 */
public final class Utility {
    public static String getText(TextView textView) {
        String text = "";
        if (textView != null) {
            text = textView.getText().toString();
        }
        return text;
    }
}