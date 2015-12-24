
//Author: Brandon Fowler
//Class: cscd305
//Assignment 0
//Total project time: 1.5 hours (Medium/slow paced work)
//Compilers used: cl and g++

#pragma once

#include<cstdlib>
#include<iostream>
using namespace std;

//Prompts for and stores three numbers pertaining to amount of cars, people, and each car capacity
void getInput(int &cars, int &cCapacity, int &people);

//Calculates how many times the coaster must run based on input values
int runCoaster(int cars, int cCapacity, int people);

//Checks to make sure input is within valid ranges(does not handle non-integer bad input)
int checkInput(int cars, int cCapacity, int people);

//Prints final results
void printResults(int cars, int cCapacity, int people, int result);