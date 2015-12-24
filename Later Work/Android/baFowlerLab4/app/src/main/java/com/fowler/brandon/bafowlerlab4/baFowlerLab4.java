package com.fowler.brandon.bafowlerlab4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class baFowlerLab4 extends AppCompatActivity implements View.OnClickListener {

    int displayVal = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_fowler_lab4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ba_fowler_lab4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "Android Lab 4, Brandon A. Fowler", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        SevenSegment cView = (SevenSegment) findViewById(R.id.disp1);
        if(displayVal == 10 && cView.getDisplayVal() != 10){
            displayVal = cView.displayVal + 1;
        }
        cView.setDisplayVal(displayVal%10);
        cView.invalidate();

        cView = (SevenSegment) findViewById(R.id.disp2);
        cView.setDisplayVal((displayVal+1)%10);
        cView.invalidate();

        cView = (SevenSegment) findViewById(R.id.disp3);
        cView.setDisplayVal((displayVal+2)%10);
        cView.invalidate();

        cView = (SevenSegment) findViewById(R.id.disp4);
        cView.setDisplayVal((displayVal+3)%10);
        cView.invalidate();

        displayVal++;
    }


}
