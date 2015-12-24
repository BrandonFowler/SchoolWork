//Name: Brandon Fowler
//Class: CSCD372
//Quarter: Fall 2015
//Assignment: Ashman Project

package com.fowler.brandon.bafowlerprojectashman;

public class Ashman extends Actor{

    int waitingDirection;
    boolean directionSet;

    public Ashman(){
        super();
        this.rowPos = 6;
        this.colPos = 8;
        this.subRow = 6;
        this.subCol = 8;
        this.direction = 0;
        this.waitingDirection = 0;
        this.directionSet = false;
        this.step = new Float(0.25);
    }

    @Override
    public void move(){
        if(direction == 1){
            this.subRow = this.subRow - step;
            if(this.subRow % 1 == 0) {
                this.rowPos = Math.round(subRow);
                if(directionSet){
                    this.direction = waitingDirection;
                    directionSet = false;
                }
            }
        }
        else if(direction == 2){
            this.subCol = this.subCol + step;
            if(this.subCol % 1 == 0) {
                this.colPos = Math.round(subCol);
                if(directionSet){
                    this.direction = waitingDirection;
                    directionSet = false;
                }
            }
        }
        else if(direction == 3){
            this.subRow = this.subRow + step;
            if(this.subRow % 1 == 0) {
                this.rowPos = Math.round(subRow);
                if(directionSet){
                    this.direction = waitingDirection;
                    directionSet = false;
                }
            }
        }
        else if(direction == 4) {
            this.subCol = this.subCol - step;
            if (this.subCol % 1 == 0) {
                this.colPos = Math.round(subCol);
                if(directionSet){
                    this.direction = waitingDirection;
                    directionSet = false;
                }
            }
        }
        else{
            if(directionSet){
                this.direction = waitingDirection;
                directionSet = false;
            }
        }
    }

    @Override
    public void setDirection(int direction){
        this.waitingDirection = direction;
        this.directionSet = true;
    }

    public int getWaitingDirection(){
        return this.waitingDirection;
    }

    public boolean getDirectionSet(){
        return this.directionSet;
    }

    public void setWaitingDirection(int waitingDirection){
        this.waitingDirection = waitingDirection;
    }

    public void setDirectionSet(boolean directionSet){
        this.directionSet = directionSet;
    }
}
