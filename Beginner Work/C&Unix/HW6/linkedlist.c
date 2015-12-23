#include "linkedlist.h"


/*
linkedlist.c
Functions file for linkedlists
Author: Brandon Fowler
Class:cscd240-02
*/


/*
addfirst function
Adds a Node to the beging of a linked list
*/
void addfirst(Node ** head,Node * d)
{
	d->next = *head;
	*head = d;
}


/*
addlast function
Adds a Node to the end of a linked list
*/
void addlast(Node ** head,Node * d)
{
	if(*head==NULL)
	{
		*head = d;
	}
	else
	{
		Node * cur = *head;
		for(cur;cur->next != NULL;cur=cur->next);
		cur->next = d;
	}
}


/*
recurseClean function
Recursivly moves through a linked list, and
frees all data and Nodes.
*/
void recurseClean(Node * cur)
{
	if(cur->next != NULL)
	{
		recurseClean(cur->next);
	}
	free(cur->data);
	free(cur);
	cur = NULL;
}


/*
clearList function
Needed for compatability with tester.
Simply calls recurseClean function.
*/
void clearList(Node * head)
{
	recurseClean(head);
}
