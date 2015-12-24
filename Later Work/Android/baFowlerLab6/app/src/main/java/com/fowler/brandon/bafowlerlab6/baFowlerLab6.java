//Name: Brandon Fowler
//Class: CSCD372
//Quarter: Fall 2015
//Assignment: Lab 6

package com.fowler.brandon.bafowlerlab6;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class baFowlerLab6 extends AppCompatActivity {

    private static final String SETTINGS = "SETTINGS" ;
    public static final String FIRST_USE = "FIRST_USE" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_fowler_lab6);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences prefs = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE) ;
        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.fragHolder, new MainFragment()).commit();
        }
        boolean firstUse = prefs.getBoolean(FIRST_USE, true) ;
        if (firstUse) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction().replace(R.id.fragHolder, new SettingsFragment());
            transaction.addToBackStack(null);
            transaction.commit();
            prefs.edit().putBoolean(FIRST_USE, false).commit() ;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ba_fowler_lab6, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction().replace(R.id.fragHolder, new SettingsFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "Android Lab 6, Brandon A. Fowler", Toast.LENGTH_SHORT).show() ;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }
        else{
            super.onBackPressed();
        }
    }
}
