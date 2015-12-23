#ifndef MYHEADER_H
#define MYHEADER_H

#include <stdio.h>
#include <stdlib.h>

/*
Author: Brandon Fowler
Description: This file contains all of the functions used by the 
main program of lab4.
*/

//readNum function
//Reads in number from keyboard stating how many numbers will fill array
int readNum();

//fillArray function
//Fills array with user input
void fillArray(int array[],int num);

//sort function
//Sorts array of ints
void sort(int array[],int num);

//menu function
//Simply displays menu and returns valid user choice
int menu();

//findMean function
//Finds the mean of an int array
double findMean(int array[],int num);

//findMedian function
//Finds the medain value in an int array
double findMedian(int num,int array[]);

//goAgain function
//Prompts user to go again then takes response in 
//the form of y, Y, n or N and returns 1 or 0
int goAgain();

//printResults function
//Prints the mean and Median values
void printResults(double mean,double median);

#endif
