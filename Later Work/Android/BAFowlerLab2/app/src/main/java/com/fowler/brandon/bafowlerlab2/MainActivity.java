//Name: Brandon Fowler
//Quarter: Fall 2015
//Assignment: Lab 2

package com.fowler.brandon.bafowlerlab2;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener{

    ArrayList<String> makes = new ArrayList<String>();
    ArrayList<ArrayList<String>> models = new ArrayList<ArrayList<String>>();
    MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            this.models = (ArrayList<ArrayList<String>>) savedInstanceState.getSerializable("models");
        }
        setContentView(R.layout.activity_main);
        boolean fileParsed = parseFile("makesAndModels.txt");
        if(!fileParsed){
            Toast.makeText(getApplicationContext(), "Failed to read the assets file!",Toast.LENGTH_SHORT).show() ;
        }
        this.adapter = new MyListAdapter(this,this.makes, this.models);
        ExpandableListView listView = (ExpandableListView) this.findViewById(R.id.expandableListView);
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "Android Lab 2, Brandon A. Fowler", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean parseFile(String fileName){
        AssetManager assets = getResources().getAssets();
        try {
            String line;
            String[] temp;
            ArrayList<String> modelList;
            InputStream input = assets.open(fileName);
            Scanner scanner = new Scanner(input);
            while(scanner.hasNextLine()){
                modelList = new ArrayList<String>();
                line = scanner.nextLine();
                temp = line.split(",");
                this.makes.add(temp[0]);
                for(int i = 1; i < temp.length; i++){
                    modelList.add(temp[i]);
                }
                this.models.add(modelList);
            }
        }
        catch(java.io.IOException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(getApplicationContext(), (models.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        this.models = this.adapter.getModels();
        savedState.putSerializable("makes", this.makes);
        savedState.putSerializable("models", this.models);
    }
}
