#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
dynlib.h
Header file for libdynfileutil.so
Author: Brandon Fowler
Class:cscd240-02
*/


//Opens an input file passed to main when program is run
FILE * openInputFile(char * fileName);

//Prompts user for file name, then opens that file for reading
FILE * openFilePrompt();

//Counts the records stored in a file
int countRecords(FILE * fin, int linesPerRec);

//Prompts the user for file name, then opens that file for writing
FILE * openOutputPrompt();
