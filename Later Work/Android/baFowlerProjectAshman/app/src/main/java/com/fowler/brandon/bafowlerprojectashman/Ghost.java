//Name: Brandon Fowler
//Class: CSCD372
//Quarter: Fall 2015
//Assignment: Ashman Project

package com.fowler.brandon.bafowlerprojectashman;

public class Ghost extends Actor{

    public Ghost(int row, int col){
        super();
        this.rowPos = row;
        this.colPos = col;
        this.subRow = row;
        this.subCol = col;
        this.direction = rn.nextInt(4) + 1;
        this.step = new Float(0.25);
    }

    @Override
    public void move(){
        if(direction == 1){
            this.subRow = this.subRow - step;
            if(this.subRow % 1 == 0) {
                this.rowPos = Math.round(subRow);
            }
        }
        else if(direction == 2){
            this.subCol = this.subCol + step;
            if(this.subCol % 1 == 0) {
                this.colPos = Math.round(subCol);
            }
        }
        else if(direction == 3){
            this.subRow = this.subRow + step;
            if(this.subRow % 1 == 0) {
                this.rowPos = Math.round(subRow);
            }
        }
        else if(direction == 4) {
            this.subCol = this.subCol - step;
            if (this.subCol % 1 == 0) {
                this.colPos = Math.round(subCol);
            }
        }
    }

    public void newDirection(){this.direction = rn.nextInt(4) + 1;}
}
