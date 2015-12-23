#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct node
{
	void * data;
	struct node * next;

};

typedef struct node Node;


//Adds a node to the begining of the list
void addFirst(Stock d);


//Adds a node in order in the list, if the data already
//exists, then it is updated.
void addOrdered(Stock d);

