#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
lab8.h
Header file for lab8
Author: Brandon Fowler
Class: CSCD240-02
*/

struct person
{
	char fname[100];
	char lname[100];
	int id;	
};

typedef struct person Person;

//Opens file specified by user
FILE * openFilePrompt();

//Counts lines in file
int countLines(FILE * fin);

//Fills Person array with data from file
Person * fillArray(int total, FILE * fin);

//Displays menu, and gets choice from user
int menu();

//Sorts Person array by last name, then prints it
void printLastNameSortedArray(Person * array, int total);

//Sorts Person array by first name, then prints it
void printFirstNameSortedArray(Person * array, int total);

//Sorts Person array by ID, then prints it
void printIDSortedArray(int total, Person * array);
