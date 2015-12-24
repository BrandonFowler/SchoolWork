//Name: Brandon Fowler
//Class: CSCD372
//Quarter: Fall 2015
//Assignment: Lab 6

package com.fowler.brandon.bafowlerlab6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        onSharedPreferenceChanged(prefs,"name");
        onSharedPreferenceChanged(prefs,"yearsRemaining");
        onSharedPreferenceChanged(prefs,"homeWorld");
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.compareTo("name")==0){
            if(sharedPreferences.getString(key,"Unknown").compareTo("")==0){
                findPreference(key).setSummary("Unknown");
            }
            else{
                findPreference(key).setSummary(sharedPreferences.getString(key,"Unknown"));
            }
        }
        else if(key.compareTo("yearsRemaining")==0){
            if(sharedPreferences.getString(key,"Unknown").compareTo("")==0){
                findPreference(key).setSummary("Unknown");
            }
            else {
                findPreference(key).setSummary(sharedPreferences.getString(key, "Unknown"));
            }
        }
        else if(key.compareTo("homeWorld")==0){
            String[] homeWorlds = getResources().getStringArray(R.array.home_worlds);
            int index;
            try {
                index = Integer.parseInt(sharedPreferences.getString(key, "14"));
            }
            catch(Exception e){
                index = 0;
            }
            if(index < 14) {
                this.findPreference(key).setSummary(homeWorlds[index]);
            }
            else{
                this.findPreference(key).setSummary("Unknown");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume() ;
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this) ;
    }
    @Override
    public void onPause() {
        super.onPause() ;
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this) ;
    }
}
