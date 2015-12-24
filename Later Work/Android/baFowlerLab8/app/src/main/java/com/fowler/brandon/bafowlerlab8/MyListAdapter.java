//Name: Brandon Fowler
//Quarter: Fall 2015
//Assignment: Lab 8
//Class: CSCD372

package com.fowler.brandon.bafowlerlab8;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MyListAdapter extends BaseExpandableListAdapter implements View.OnClickListener{

    Activity activity;
    ArrayList<String> makes;
    ArrayList<ArrayList<String>> models;

    public MyListAdapter(Activity activity, ArrayList<String> makes, ArrayList<ArrayList<String>> models){
        this.activity = activity;
        this.makes = makes;
        this.models = models;
    }

    @Override
    public int getGroupCount() {
        return makes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return (models.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return makes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return (models.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)  activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.group_item_layout, null);
        }
        String make = makes.get(groupPosition);
        ((TextView) convertView.findViewById(R.id.groupTextView)).setText(make);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)  activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.child_item_layout, null);
        }
        String model = (models.get(groupPosition)).get(childPosition);
        ((TextView) convertView.findViewById(R.id.childTextView)).setText(model);
        ImageView tv = (ImageView) convertView.findViewById(R.id.imageView);
        tv.setTag(R.id.group_num, groupPosition);
        tv.setTag(R.id.posn_num, childPosition);
        tv.setOnClickListener(this);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
        int groupPos = (int)v.getTag(R.id.group_num);
        int childPos = (int)v.getTag(R.id.posn_num);
        (this.models.get(groupPos)).remove(childPos);
        notifyDataSetChanged();
    }

    public ArrayList<ArrayList<String>> getModels(){
        return this.models;
    }

    public ArrayList<String> getMakes(){
        return this.makes;
    }
}

