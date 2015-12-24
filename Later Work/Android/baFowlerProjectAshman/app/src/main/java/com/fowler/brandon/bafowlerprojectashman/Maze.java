//Name: Brandon Fowler
//Class: CSCD372
//Quarter: Fall 2015
//Assignment: Ashman Project

package com.fowler.brandon.bafowlerprojectashman;

import android.os.Parcel;
import android.os.Parcelable;

public class Maze{

    int level;
    int[][] grid;//0 = empty cell, 1 = wall, 2 = cake cell
    int cakes = 0;
    Ashman ash;
    Ghost G1;
    Ghost G2;
    Ghost G3;
    Ghost G4;
    Ghost G5;

    public Maze(int level){
        this.level = level;
        this.ash = new Ashman();
        this.G1 = new Ghost(0,0);
        this.G2 = new Ghost(2,0);
        this.G3 = new Ghost(9,0);
        if(level == 2){
            this.G4 = new Ghost(12, 1);
            this.G5 = new Ghost(13, 3);
        }
        initializeMaze();
    }

    private void initializeMaze() {
        grid = new int[14][14];

        //Set Walls
        grid[0][8] = 1; grid[1][1] = 1; grid[1][3] = 1; grid[1][4] = 1; grid[1][10] = 1; grid[1][11] = 1;
        grid[1][12] = 1; grid[1][13] = 1; grid[2][1] = 1; grid[2][3] = 1; grid[2][6] = 1; grid[2][7] = 1;
        grid[2][12] = 1; grid[3][1] = 1; grid[3][6] = 1; grid[3][7] = 1; grid[4][1] = 1; grid[4][2] = 1;
        grid[4][4] = 1; grid[4][5] = 1; grid[4][6] = 1; grid[4][7] = 1; grid[4][8] = 1; grid[4][9] = 1;
        grid[4][11] = 1; grid[4][12] = 1; grid[4][13] = 1; grid[5][4] = 1; grid[5][5] = 1; grid[5][6] = 1;
        grid[5][7] = 1; grid[5][8] = 1; grid[5][9] = 1; grid[6][0] = 1; grid[6][1] = 1; grid[6][6] = 1;
        grid[6][7] = 1; grid[6][11] = 1; grid[6][13] = 1; grid[7][3] = 1; grid[7][4] = 1; grid[7][6] = 1;
        grid[7][7] = 1; grid[7][9] = 1; grid[8][1] = 1; grid[8][2] = 1; grid[8][3] = 1; grid[8][4] = 1;
        grid[8][6] = 1; grid[8][7] = 1; grid[8][9] = 1; grid[8][11] = 1; grid[8][13] = 1; grid[9][1] = 1;
        grid[9][3] = 1; grid[9][6] = 1; grid[9][7] = 1; grid[9][9] = 1; grid[9][13] = 1; grid[10][6] = 1;
        grid[10][7] = 1; grid[10][9] = 1; grid[10][11] = 1; grid[10][13] = 1; grid[11][0] = 1; grid[11][1] = 1;
        grid[11][2] = 1; grid[11][3] = 1; grid[11][6] = 1; grid[11][7] = 1; grid[12][10] = 1; grid[12][12] = 1;
        grid[13][0] = 1; grid[13][2] = 1; grid[13][5] = 1; grid[13][6] = 1; grid[13][7] = 1; grid[13][8] = 1;

        //Set Cakes
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] != 1){
                    grid[i][j] = 2;
                    cakes++;
                }
            }
        }

        //Set Player Start To Empty
        grid[6][8] = 0;
        cakes--;
    }

    public int[][] getGrid(){
        return grid;
    }

    public Ashman getAsh(){
        return ash;
    }

    public Ghost getG1(){
        return G1;
    }

    public Ghost getG2(){
        return G2;
    }

    public Ghost getG3(){
        return G3;
    }

    public Ghost getG4(){
        return G4;
    }

    public Ghost getG5(){
        return G5;
    }

    public int getLevel(){
        return level;
    }

    public int getCakes(){
        return cakes;
    }

    public void moveGhosts(){
        moveGhost(G1);
        moveGhost(G2);
        moveGhost(G3);
        moveGhost(G4);
        moveGhost(G5);
    }

    public void moveGhost(Ghost g){
        if(g == null){
            return;
        }
        boolean canMove = false;
        while(!canMove){
            if(g.getDirection() == 1 && (g.getRow() == 0 || grid[g.getRow()-1][g.getCol()] == 1)){
                g.newDirection();
            }
            else if(g.getDirection() == 2 && (g.getCol() == 13 || grid[g.getRow()][g.getCol()+1] == 1)){
                g.newDirection();
            }
            else if(g.getDirection() == 3 && (g.getRow() == 13 || grid[g.getRow()+1][g.getCol()] == 1)){
                g.newDirection();
            }
            else if(g.getDirection() == 4 && (g.getCol() == 0 || grid[g.getRow()][g.getCol()-1] == 1)){
                g.newDirection();
            }
            else{
                canMove = true;
            }
        }
        g.move();
    }

    public void setDirection(int direction){
        ash.setDirection(direction);
    }

    public void moveAsh(){
        if(ash.getDirection() == 1 && (ash.getRow() == 0 || grid[ash.getRow()-1][ash.getCol()] == 1)){
            ash.direction = 0;
        }
        else if(ash.getDirection() == 2 && (ash.getCol() == 13 || grid[ash.getRow()][ash.getCol()+1] == 1)){
            ash.direction = 0;
        }
        else if(ash.getDirection() == 3 && (ash.getRow() == 13 || grid[ash.getRow()+1][ash.getCol()] == 1)){
            ash.direction = 0;
        }
        else if(ash.getDirection() == 4 && (ash.getCol() == 0 || grid[ash.getRow()][ash.getCol()-1] == 1)){
            ash.direction = 0;
        }
        ash.move();
    }

    public void cheat(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] != 1){
                    grid[i][j] = 0;
                }
            }
        }
        grid[0][13] = 2;
        cakes = 1;
    }

    public boolean eatCake(){
        if(grid[ash.getRow()][ash.getCol()] == 2){
            grid[ash.getRow()][ash.getCol()] = 0;
            cakes--;
            return true;
        }
        return false;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setGrid(int[][] grid){
        this.grid = grid;
    }

    public void setCakes(int cakes){
        this.cakes = cakes;
    }

}
