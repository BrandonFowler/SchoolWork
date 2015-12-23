#pragma once


#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/*
linkedList.h
Header for linkedList.c
Author: Brandon Fowler
Class: cscd240-02
*/


//Initialize weather structure
struct weather
{
	char * month;
	int year;
	int days;
	int * temps;
};
typedef struct weather Weather;


//Initialize node structure
struct node
{
	Weather data;
	struct node * next;
};
typedef struct node Node;


/*
addOrdered function:
Adds one Node to the linked list inserted in
the list at its ordered position based on
contained Weather data
*/
void addOrdered(Weather dta, int month, int year);


/*
clearList function:
Simply calls recurseClear()
*/
void clearList();


/*
addFirst function:
Simply adds a Node containing weather data to
the begining of the linked list
*/
void addfirst(Weather d);


/*
recurseClear function:
Recursivley moves through the linkedList of
Weather data, and frees all memory.
*/
void recurseClear(Node * cur);

