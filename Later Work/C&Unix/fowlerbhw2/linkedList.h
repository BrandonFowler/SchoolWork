//CSCD340
//Homework 2
//Brandon Fowler

#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct node
{
    void * data;
    struct node * next;
};
typedef struct node Node;


struct linkedlist
{
	Node * head;
	int size;
	int printSize;
	int writeSize;
};
typedef struct linkedlist LinkedList;

void addLast(LinkedList * theList, Node * nn);
void addFirst(LinkedList * theList, Node * nn);
void clearList(LinkedList * theList);
LinkedList * linkedList();
Node * buildNode();

#endif // LINKEDLIST_H
