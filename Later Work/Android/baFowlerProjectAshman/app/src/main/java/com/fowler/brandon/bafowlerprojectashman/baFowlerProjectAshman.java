//Name: Brandon Fowler
//Class: CSCD372
//Quarter: Fall 2015
//Assignment: Ashman Project

package com.fowler.brandon.bafowlerprojectashman;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class baFowlerProjectAshman extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_fowler_project_ashman);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        status = (TextView)findViewById(R.id.Status);
        ((MazeDisplay)findViewById(R.id.MazeView)).setStatus(status);
        ((MazeDisplay)findViewById(R.id.MazeView)).setOnClickListener(this);
        ((CustomButton)findViewById(R.id.up)).setOnClickListener(this);
        ((CustomButton)findViewById(R.id.right)).setOnClickListener(this);
        ((CustomButton)findViewById(R.id.down)).setOnClickListener(this);
        ((CustomButton)findViewById(R.id.left)).setOnClickListener(this);
        ((TextView)findViewById(R.id.Instructions)).setOnLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ba_fowler_project_ashman, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "Brandon A. Fowler, Fall 2015, CSCD372, Ashman Project", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.MazeView){
            if(((MazeDisplay)findViewById(R.id.MazeView)).getEnd()){
                ((MazeDisplay)findViewById(R.id.MazeView)).reset();
            }
            else {
                ((MazeDisplay) findViewById(R.id.MazeView)).pauseOrGo();
            }
        }
        else if(v.getId() == R.id.up){
            ((MazeDisplay)findViewById(R.id.MazeView)).getMaze().setDirection(1);
        }
        else if(v.getId() == R.id.right){
            ((MazeDisplay)findViewById(R.id.MazeView)).getMaze().setDirection(2);
        }
        else if(v.getId() == R.id.down){
            ((MazeDisplay)findViewById(R.id.MazeView)).getMaze().setDirection(3);
        }
        else if(v.getId() == R.id.left){
            ((MazeDisplay)findViewById(R.id.MazeView)).getMaze().setDirection(4);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        ((MazeDisplay)findViewById(R.id.MazeView)).cheat();
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        ((MazeDisplay) findViewById(R.id.MazeView)).pause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((MazeDisplay) findViewById(R.id.MazeView)).pause();
    }
}
