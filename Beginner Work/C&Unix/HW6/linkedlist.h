#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/*
linkedlist.c
Functions file for linkedlists
Modifier: Brandon Fowler
Class:cscd240-02
*/


struct node
{
	void * data;
	struct node * next;

};

typedef struct node Node;


/*
addfirst function
Adds a Node to the beging of a linked list
*/
void addfirst(Node ** head,Node * d);


/*
addlast function
Adds a Node to the end of a linked list
*/
void addlast(Node ** head,Node * d);


/*
recurseClean function
Recursivly moves through a linked list, and
frees all data and Nodes.
*/
void recurseClean(Node * cur);


/*
clearList function
Needed for compatability with tester.
Simply calls recurseClean function.
*/
void clearList(Node * head);
