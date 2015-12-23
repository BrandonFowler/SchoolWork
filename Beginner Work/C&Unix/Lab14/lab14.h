#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/*
Lab14.h
Header file for lab14
Author: Brandon Fowler
Class:cscd240-02
*/


//Reads in a string from the user
void readString(char theString[650]);


//Presents user with a menu choice and returns the choice
int menu();


//Parses the string into words and print statistics
void parseString(char theString[650]);
