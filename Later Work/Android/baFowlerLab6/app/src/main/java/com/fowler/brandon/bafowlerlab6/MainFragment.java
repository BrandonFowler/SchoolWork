//Name: Brandon Fowler
//Class: CSCD372
//Quarter: Fall 2015
//Assignment: Lab 6

package com.fowler.brandon.bafowlerlab6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainFragment extends Fragment {

    int[] picIds = null;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loadIntIds();
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String name = "Hello "+ prefs.getString("name","Unknown");
        boolean isStudent = prefs.getBoolean("isStudent",true);
        String welcomeMessage;
        int picRes;
        if(isStudent){
            int years;
            try {
                years = Integer.parseInt(prefs.getString("yearsRemaining", "0"));
            }
            catch(Exception e){
                years = 0;
            }
            welcomeMessage = "We wish you success during your "+years+" years at the academy.";
            int picIndex;
            try {
                picIndex = Integer.parseInt(prefs.getString("homeWorld", "14"));
            }
            catch(Exception e){
                picIndex = 14;
            }
            picRes = this.picIds[picIndex];
        }
        else{
            welcomeMessage = "Welcome back";
            picRes = R.drawable.academy;
        }
        TextView nameView = (TextView) v.findViewById(R.id.NameTextView);
        TextView welcomeView = (TextView) v.findViewById(R.id.WelcomeText);
        ImageView image = (ImageView) v.findViewById(R.id.imageSuggestion);
        nameView.setText(name);
        welcomeView.setText(welcomeMessage);
        image.setImageResource(picRes);
        return v;
    }

    public void loadIntIds(){
        if(picIds == null) {
            this.picIds = new int[15];
            this.picIds[0] = R.drawable.andoria;
            this.picIds[1] = R.drawable.bajor;
            this.picIds[2] = R.drawable.betazed;
            this.picIds[3] = R.drawable.cardassia;
            this.picIds[4] = R.drawable.denobula;
            this.picIds[5] = R.drawable.earth;
            this.picIds[6] = R.drawable.ferenginar;
            this.picIds[7] = R.drawable.fluidic;
            this.picIds[8] = R.drawable.kronos;
            this.picIds[9] = R.drawable.remus;
            this.picIds[10] = R.drawable.romulus;
            this.picIds[11] = R.drawable.suliban;
            this.picIds[12] = R.drawable.talax;
            this.picIds[13] = R.drawable.talos;
            this.picIds[14] = R.drawable.def;
        }
    }

}
