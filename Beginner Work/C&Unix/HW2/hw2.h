#ifndef HW2_H
#define HW2_H


#include <stdio.h>
#include <stdlib.h>
#include <time.h>


/*
hw2.h
Part of Homework 2
Author: Brandon Fowler
Class: CSCD240-02
*/


/*
createMaze function:
Initialize 2D maze, brings in chars from input into a char 2D array,
then populates an int 2D array(maze) based on chars in the 2D char array.
*/
void createMaze(int maze[20][25]);


/*
placeHag function:
Randomly place int marker for hag into maze with error checking
for valid placement.
*/
void placeHag(int maze[20][25]);


/*
placeStart function:
Randomly place int marker for start into maze with error checking
for valid placement.
*/
void placeStart(int maze[20][25]);


/* 
readDirection function:
Gets char input from user signifying move north south
east or west, converts to upper, then returns input.
*/
char readDirection();


/*
move function:
Error checks for out of array bounds, then calls functions
to move the player.
*/
void move(int maze[20][25],char direction);


/*
displayerMaze function:
Uses integer markers in maze 2D array to print out a char
representation of the maze.
*/
void displayMaze(int maze[20][25]);


/*
peek function:
Make areas directly connected to the players 
position visible, and displays the maze.
*/
void peek(int maze[20][25]);


/*
menu function:
Print menu and get choice from user.
*/
int menu();

/*
moveNorth function
Moves player location North if possible, and places proper markers
*/
void moveNorth(int maze[20][25]);


/*
moveSouth function
Moves player location South if possible, and places proper markers
*/
void moveSouth(int maze[20][25]);


/*
moveEast function
Moves player location East if possible, and places proper markers
*/
void moveEast(int maze[20][25]);


/*
moveWest function
Moves player location West if possible, and places proper markers
*/
void moveWest(int maze[20][25]);


/*
moveHag function
Moves the hag randomly in a direction, if possible.
*/
void moveHag(int maze[20][25]);


/*
endGame function:
Checks for player at finish or caught by hag.
*/
int endGame(int maze[20][25]);


#endif
