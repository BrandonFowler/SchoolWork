

#ifndef HW3_H
#define HW3_H


#include "hw3.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
hw3.h
Header for hw3
Author: Brandon Fowler
Class: CSCD240-02
*/

//Opens a input file specified by the user
FILE * openFilePrompt();

//Opens a output file specified by the user
FILE * openOutFilePrompt();

//Counts the line of the file
int countLines(FILE * fin);

//Fills a 2D array with data from the file
int ** fillArray(int total, FILE * fin);

//Displays a menu for the user
int menu();

//Displays temps and average for the month specified
void displayMonthData(int total, int ** temps);

//Displays temps and average for the month specified
void displayYearData(int total, int ** temps);

//Allows user to add another month of temperatures
int ** addMoreTemps(int ** temps, int * totalP);

//Cleans up memory
void cleanUp(int total, int ** temps);

//Returns an integer representation of a month
int calcMonth(char month[100]);

//Returns how many days are in the month given
int calcDays(char month[100]);


#endif
