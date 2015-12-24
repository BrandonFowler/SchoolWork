//Name: Brandon Fowler
//Quarter: Fall 2015
//Assignment: Lab 8
//Class: CSCD372

package com.fowler.brandon.bafowlerlab8;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import android.view.View.OnClickListener;
import com.dropbox.chooser.android.DbxChooser;

public class baFowlerLab8 extends AppCompatActivity implements ExpandableListView.OnChildClickListener{

    ArrayList<String> makes = new ArrayList<String>();
    ArrayList<ArrayList<String>> models = new ArrayList<ArrayList<String>>();
    MyListAdapter adapter;

    static final int DBX_CHOOSER_REQUEST = 0;
    private Button mChooserButton;
    private DbxChooser mChooser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_fowler_lab8);
        if(savedInstanceState != null){
            this.models = (ArrayList<ArrayList<String>>) savedInstanceState.getSerializable("models");
            this.makes = (ArrayList<String>) savedInstanceState.getSerializable("makes");
        }
        else {
            boolean fileParsed = parseFile("makesAndModels.txt");
            if (!fileParsed) {
                Toast.makeText(getApplicationContext(), "Failed to read the assets file!", Toast.LENGTH_SHORT).show();
            }
        }
        this.adapter = new MyListAdapter(this,this.makes, this.models);
        ExpandableListView listView = (ExpandableListView) this.findViewById(R.id.expandableListView);
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(this);

        mChooser = new DbxChooser("loqsno29sfc4en3");

        mChooserButton = (Button) findViewById(R.id.DBButton);
        mChooserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooser.forResultType(DbxChooser.ResultType.FILE_CONTENT)
                        .launch(baFowlerLab8.this, DBX_CHOOSER_REQUEST);
            }
        });
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
            Toast.makeText(getApplicationContext(), "Android Lab 8, Brandon A. Fowler", Toast.LENGTH_SHORT).show();
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

    public boolean parseFileIS(InputStream IS){
        this.makes = new ArrayList<String>();
        this.models = new ArrayList<ArrayList<String>>();
        try {
            String line;
            String[] temp;
            ArrayList<String> modelList;
            Scanner scanner = new Scanner(IS);
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
        catch(Exception e){
            return false;
        }
        this.adapter = new MyListAdapter(this,this.makes, this.models);
        ExpandableListView listView = (ExpandableListView) this.findViewById(R.id.expandableListView);
        listView.setAdapter(adapter);
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
        this.makes = this.adapter.getMakes();
        this.models = this.adapter.getModels();
        savedState.putSerializable("makes", this.makes);
        savedState.putSerializable("models", this.models);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DBX_CHOOSER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                DbxChooser.Result result = new DbxChooser.Result(data);
                Log.d("main", "Link to selected file: " + result.getLink());
                Uri res = result.getLink();
                ContentResolver resolver = this.getContentResolver();
                try {
                    InputStream IS = resolver.openInputStream(res);
                    boolean fileParsed = parseFileIS(IS);
                    if(!fileParsed){
                        Log.d("main", "Failed to read the file!");
                        Toast.makeText(getApplicationContext(), "Failed to read the file!",Toast.LENGTH_SHORT).show() ;
                    }
                }
                catch(FileNotFoundException e){
                    Log.d("main", "Something went wrong and the file could not be opened");
                    Toast.makeText(getApplicationContext(), "Something went wrong and the file could not be opened", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Handle the result
            } else {
                // Failed or was cancelled by the user.
                Log.d("main", "Something went wrong and the file could not be downloaded");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
