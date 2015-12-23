#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "fileutil.h"
#include "linkedList.h"


/*
hw4.h
Header for hw4.c and main
Author: Brandon Fowler
Class: cscd240-02
*/


/*
createList funtion:
Reads through input file, and constructs a linked list
containing weather data ordered by year then month.
*/
void createList(FILE * fin);


/*
menu function:
Simple menu to display options and return choice
*/
int menu();


/*
writeMonthData function:
Seeks all data in linked list pertaining to month
specified by user, and prints the data to the screen 
or file, along with an average.
*/
void writeMonthData();


/*
writeYearData function:
Seeks all data in linked list pertaining to year
specified by user, and prints the data to the screen 
or file, along with an average.
*/
void writeYearData();


/*
writeList funtion:
Simple writes all of the weather data in the linked
list to the screen or a file.
*/
void writeTheList();


/*
addMoreData function:
Allows user to enter more weather data into the linked list
either manually or from a file.
*/
void addMoreData();


/*
calcDays function:
Returns number of days in the month
*/
int calcDays(char month[100], int year);


/*
calcMonth function:
Returns an integer representation of a month.
Uses static char array
*/
int calcMonth(char month[100]);


/*
calcMonth2 function:
Returns an integer representation of a month.
Uses char *
*/
int calcMonth2(char * month);

