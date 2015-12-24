package com.fowler.brandon.bafowlerlab3;

public class DrawerItem {
    int iconID;
    String rowName;

    DrawerItem(int iconID, String rowName){
        this.iconID = iconID;
        this.rowName = rowName;
    }

    public int getIconID(){
        return this.iconID;
    }

    public String getRowName(){
        return this.rowName;
    }
}
