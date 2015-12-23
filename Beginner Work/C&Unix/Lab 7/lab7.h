
#ifndef LAB7_H
#define LAB7_H


#include "lab7.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/*
lab7.h
Header for lab 7
Author: Brandon Fowler
Class: CSCD240-02
*/


/*
openFileNoPromt Function
Opens file and returns pointer				
*/
FILE * openFileNoPrompt(char * fileName);


/*
openFilePromt Function
Opens file specified by user and returns pointer
NOTE: I couldn't figure out how to error check for
invalid file names. If you enter a bad file name
the program will fail.				
*/
FILE * openFilePrompt();


/*
readName Function
Reads the name from the input file and returns it
*/
char * readName(FILE * fin);


/*
createAndFillArray Function
Reads lines from file and populates arrays
*/
int * createAndFillArray(int * total, FILE * fin);


/*
fillArray Function
Fills array with exam scores from file
*/
void fillArray(int exams[2], FILE * fin);


/*
readFinalScore Function
Reads final score in from file
*/
int readFinalScore(FILE * fin);


/*
calculatePercentage Function
Calaculates grade percentages
*/
double calculatePercentage(int * assignments, int totalAquired, int worthEach, double scale);


/*
displayCategoryPercentage Function
Displayes grades
*/
void displayCategoryPercentage(double assignments, double labs, double quizzes, double exams, double final);


/*
calcWeightedAvg Function
Calculates weighted grade average
*/
double calcWeightedAvg(double assignments, double labs, double quizzes, double exams, double final);


/*
calcGPA Function
Calaculates GPA
*/
double calcGPA(double weightedAverage);


/*
displayGrade Function
Displays grade percentages
*/
void displayGrade(char * name, double weightedAverage, double gpa);


/*
clean Function
Cleans up array memory
*/
void clean(char * toClean);


/*
cleanUp Function
Cleans up array memory
*/
void cleanUp(int * toClean);


/*
goAgain Function
Promts user to go again or quit
*/
int goAgain();


#endif
