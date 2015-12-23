#pragma once

#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include "dynlib.h"


/*
lab12_1.h
Header file for lab12 part 1
Author: Brandon Fowler
Class:cscd240-02
*/


//Opens output file with low level commands
int openOutputLow();

//Reads shift direction from user
char readDirection(int * shift);

//Reads through input file, shifts charcters, writes data to file with low level commands
void processFile(FILE * fin, int fout, int total, char direction, int shift);
