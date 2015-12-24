//Name: Brandon Fowler
//Class: CSCD372
//Assignment: Lab7
//Quarter: Fall 2015

package com.fowler.brandon.bafowlerlab7;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class baFowlerLab7 extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    ImageView[] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_fowler_lab7);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView x = (ImageView) findViewById(R.id.x);
        ImageView o = (ImageView) findViewById(R.id.o);
        x.setOnTouchListener(this);
        o.setOnTouchListener(this);
        x.setTag(R.drawable.x);
        o.setTag(R.drawable.o);
        cells = new ImageView[9];
        cells[0] = (ImageView) findViewById(R.id.cell1);
        cells[1] = (ImageView) findViewById(R.id.cell2);
        cells[2] = (ImageView) findViewById(R.id.cell3);
        cells[3] = (ImageView) findViewById(R.id.cell4);
        cells[4] = (ImageView) findViewById(R.id.cell5);
        cells[5] = (ImageView) findViewById(R.id.cell6);
        cells[6] = (ImageView) findViewById(R.id.cell7);
        cells[7] = (ImageView) findViewById(R.id.cell8);
        cells[8] = (ImageView) findViewById(R.id.cell9);

        for(int i = 0; i < cells.length; i++){
            cells[i].setOnDragListener(this);
        }

        if(savedInstanceState != null){
            for(int i = 0; i < cells.length; i++) {
                cells[i].setImageResource(savedInstanceState.getInt("Cell"+i));
                cells[i].setTag(savedInstanceState.getInt("Cell" + i));
            }
            x.setImageResource(R.drawable.x);
            o.setImageResource(R.drawable.o);
        }
        else {
            for(int i = 0; i < cells.length; i++){
                cells[i].setTag(R.drawable.blank);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ba_fowler_lab7, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "Android Lab 7, Brandon A. Fowler", Toast.LENGTH_SHORT).show() ;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            View.DragShadowBuilder drag = new View.DragShadowBuilder(v);
            v.startDrag(null, drag, v, 0);
            return true;
        }
        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        ImageView cell = (ImageView) v;
        if(((int)cell.getTag()) != R.drawable.blank){
            return false;
        }
        if(event.getAction() == DragEvent.ACTION_DROP){
            View droppingView = (View) event.getLocalState();
            int idRec = (int) droppingView.getTag();
            cell.setImageResource(idRec);
            cell.setTag(idRec);
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        for(int i = 0; i < cells.length; i++) {
            savedState.putInt("Cell"+i, (int)cells[i].getTag());
        }
    }
}
