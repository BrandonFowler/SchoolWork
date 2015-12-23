#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct company
{
	char * name;
	char * symbol;
};

typedef struct company Company;

struct stock
{
	Company c;
	int shares;
	double price;
};

typedef struct stock Stock;


//Opens user specified file, compares data, and prints results
void printStockValue();


//Ask user for input, adds data to list or updates
//existing data.
void buyStock();


//Allows user to sell stock, if they have it
void sellStock();


//Prints data in entire linked list
void printStockInfo();
