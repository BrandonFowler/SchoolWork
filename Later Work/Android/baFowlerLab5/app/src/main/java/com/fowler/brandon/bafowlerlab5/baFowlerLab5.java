//Name: Brandon Fowler
//Class: CSCD372(Android)
//Assignment: Lab5
//Quarter: Fall 2015

package com.fowler.brandon.bafowlerlab5;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class baFowlerLab5 extends AppCompatActivity implements MainFragment.UpdateListener {

    String updateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_fowler_lab5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(savedInstanceState == null){
            if(!(getResources().getBoolean(R.bool.dual_pane))) {
                getFragmentManager().beginTransaction().add(R.id.portHolder, new MainFragment()).commit();
            }
        }
        else{
            if((getResources().getBoolean(R.bool.dual_pane))) {
                this.updateText = savedInstanceState.getString("Update_Text");
                DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailHolder);
                detailFragment.setUpdateText(this.updateText);
                getFragmentManager().popBackStack(0, getFragmentManager().POP_BACK_STACK_INCLUSIVE);
            }
            else{
                getFragmentManager().beginTransaction().replace(R.id.portHolder, new MainFragment()).commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ba_fowler_lab5, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "Android Lab 5, Brandon A. Fowler", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String updateText) {
        this.updateText = updateText;
        DetailFragment detailFragment;
        if(getResources().getBoolean(R.bool.dual_pane)) {
            detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailHolder);
            detailFragment.setUpdateText(updateText);
        }
        else{
            detailFragment = (DetailFragment) getFragmentManager().findFragmentByTag("DETAIL_FRAGMENT");
            if(detailFragment != null){
                Log.d("Detail Fragments", "An existing DetailFragment was found");
                detailFragment.setUpdateText(updateText);
            }
            else{
                Log.d("Detail Fragments", "An existing DetailFragment NOT was found");
                detailFragment = new DetailFragment();
                Bundle args = new Bundle();
                args.putString("UPDATE_TEXT", updateText);
                detailFragment.setArguments(args);
                FragmentTransaction transaction = getFragmentManager().beginTransaction().replace(R.id.portHolder, detailFragment, "DETAIL_FRAGMENT");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
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

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        savedState.putString("Update_Text", this.updateText);
    }
}
