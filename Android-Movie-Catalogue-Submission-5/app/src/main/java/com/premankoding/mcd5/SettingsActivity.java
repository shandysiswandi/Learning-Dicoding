package com.premankoding.mcd5;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    private ApplicationPreference applicationPreference;
    private boolean isUpcoming, isDaily;
    private SwitchPreference d,u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        boolean day, rel;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

        @Override
        public void onResume() {
            super.onResume();

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getActivity()));
            day = sharedPreferences.getBoolean("daily",false);
            rel = sharedPreferences.getBoolean("release",false);

            if(day){
                Log.i("DAY", "onResume: "+day);
            }else{
                Log.i("DAY", "onResume: "+day);
            }

            if(rel){
                Log.i("DAY", "onResume: "+rel);
            }else{
                Log.i("DAY", "onResume: "+rel);
            }
        }
    }
}