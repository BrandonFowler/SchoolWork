//Name: Brandon Fowler
//Class: CSCD372
//Quarter: Fall 2015
//Assignment: Ashman Project

package com.fowler.brandon.bafowlerprojectashman;

import java.util.Random;

public abstract class Actor {

    int rowPos;
    int colPos;
    int direction;// 1 = North, 2 = East, 3 = South, 4 = West
    float subRow;
    float subCol;
    Random rn = new Random();
    float step;

    public int getRow(){return rowPos;}

    public int getCol(){
        return colPos;
    }

    public int getDirection(){return direction;}

    public void setDirection(int direction){this.direction = direction;}

    public void setRow(int row){
        this.rowPos = row;
    }

    public void setCol(int col){
        this.colPos = col;
    }

    public void setSubRow(float row){
        this.subRow = row;
    }

    public void setSubCol(float col){
        this.subCol = col;
    }

    public float getSubRow(){
        return subRow;
    }

    public float getSubCol(){
        return subCol;
    }

    public void move(){
        this.rowPos = this.rowPos;
        this.colPos = this.colPos;
    }


}
