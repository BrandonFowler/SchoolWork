#pragma once

#include "stocks.h"
#include "linkedlist.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>


/*
hw5.h
Header for hw5
Author: Brandon Fowler
Class: cscd240-02
*/


//Opens an input file with low level commands
int openInputFile();


//Reads from file into array, splits array, parse data,
//builds Stock structure, passes to addOrdered function.
void buildList(int fdRead, int lpr);


//Presents user with menu, gets and returns choice
int menu();


//Calls recurse clean function
void cleanUp();


//Recursivley frees all data in the linked list
void recurseClean(Node * cur);









