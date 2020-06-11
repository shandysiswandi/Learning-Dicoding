package com.premankoding.mcd5;

import android.content.Context;
import android.content.SharedPreferences;

public class ApplicationPreference {
    private String PREF_NAME = "userPreferences";
    private String KEY_DAILY = "daily";
    private String KEY_RELEASE = "release";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public ApplicationPreference(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setRelease(boolean status){
        editor.putBoolean(KEY_RELEASE, status);
        editor.apply();
    }

    public void setDaily(boolean status){
        editor.putBoolean(KEY_DAILY, status);
        editor.apply();
    }

    public boolean getRelease(){
        return preferences.getBoolean(KEY_RELEASE,false);
    }

    public boolean getDaily(){
        return preferences.getBoolean(KEY_DAILY, false);
    }

}
