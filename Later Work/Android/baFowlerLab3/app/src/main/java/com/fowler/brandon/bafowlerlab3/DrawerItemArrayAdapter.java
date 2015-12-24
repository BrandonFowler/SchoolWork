package com.fowler.brandon.bafowlerlab3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerItemArrayAdapter extends ArrayAdapter<DrawerItem> {

    Context context;
    int layoutRecID;
    DrawerItem[] drawerItems;

    DrawerItemArrayAdapter(Context context, int layoutRecID, DrawerItem[] drawerItems){
        super(context, layoutRecID, drawerItems);
        this.context = context;
        this.layoutRecID = layoutRecID;
        this.drawerItems = drawerItems;
    }

    @Override
    public android.view.View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)  ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.nav_list_row, null);
        }
        DrawerItem drawerItem = drawerItems[position];
        ((ImageView) convertView.findViewById(R.id.imageView)).setImageResource(drawerItem.getIconID());
        ((TextView) convertView.findViewById(R.id.textView)).setText(drawerItem.getRowName());
        return convertView;
    }
}
