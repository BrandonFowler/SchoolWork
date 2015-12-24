package com.fowler.brandon.bafowlerlab3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class baFowlerLab3 extends AppCompatActivity implements ListView.OnItemClickListener {

    ListView listView;
    DrawerLayout drawerLayout;
    final CharSequence mainTitle = "baFowlerLab3";
    final CharSequence navDrawerTitle = "Select a Page";
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    int imageRecID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_fowler_lab3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.rgb(41, 41, 41));
        setSupportActionBar(toolbar);
        listView = (ListView) this.findViewById(R.id.listView);
        drawerLayout = (DrawerLayout) this.findViewById(R.id.drawerLayout);
        DrawerItem[] drawerItems = {
                new DrawerItem(R.drawable.logo, "Fred Schneider"),
                new DrawerItem(R.drawable.logo, "Kate Pierson"),
                new DrawerItem(R.drawable.logo, "Cindy Wilson"),
                new DrawerItem(R.drawable.logo, "Keith Strickland"),
                new DrawerItem(R.drawable.logo, "Ricky Wilson"),
                new DrawerItem(R.drawable.logo, "Matt Flynn")};
        DrawerItemArrayAdapter drawerItemArrayAdapter = new DrawerItemArrayAdapter(this, R.layout.nav_list_row, drawerItems);
        listView.setAdapter(drawerItemArrayAdapter);
        listView.setOnItemClickListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                setTitle(mainTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(navDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState() ;
        this.imageRecID = R.drawable.logo;
        if(savedInstanceState != null){
            this.imageRecID = savedInstanceState.getInt("ImageRecID");
            ((ImageView) findViewById(R.id.imageView)).setImageResource(this.imageRecID);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ba_fowler_lab3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "Android Lab 3, Brandon A. Fowler", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int[] picIDs = {R.drawable.fred, R.drawable.kate, R.drawable.cindy, R.drawable.keith, R.drawable.rickey, R.drawable.matt};
        ((ImageView) findViewById(R.id.imageView)).setImageResource(picIDs[position]);
        this.imageRecID = picIDs[position];
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(listView);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        savedState.putInt("ImageRecID", this.imageRecID);
    }
}
