package com.pcsalt.example.sharedpreferencedemo;

/**
 * Created by krrishnaaaa on Oct 02, 2015
 */
public final class PrefData {
    public String key;
    public String value;

    public PrefData() {
    }

    public PrefData(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof PrefData && key.equals(((PrefData) object).key);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
