#pragma once


#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>


/*
lab12_2.h
Header file for lab12 part 2
Author: Brandon Fowler
Class:cscd240-02
*/


//Opens input file with low level commands
int openInputLow();

//Reads number of lines from file
int readTotal(int fin);

//Reads shift direction from file
char readDirection(int fin, int *shift);

//Reads through file with low level commands, decrypts file, prints result to screen
void processFile(int fin, int total, char direction, int shift);
