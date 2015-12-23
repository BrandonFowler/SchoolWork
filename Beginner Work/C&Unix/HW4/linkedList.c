#include "hw4.h"
#include "linkedList.h"

/*
linked List.c
Contains funtions used to manipulate a linked list
of nodes with Weather data.
Author: Brandon Fowler
Class: cscd240-02
*/


extern Node * head;//Head for linked list
extern int size;//Size of linked list


/*
addOrdered function:
Adds one Node to the linked list inserted in
the list at its ordered position based on
contained Weather data
*/
void addOrdered(Weather dta, int month, int year)
{
	if(head == NULL)//Empty list
	{
		addfirst(dta);
	}
	else if(size == 1)//Only head node
	{
		if(year < head->data.year || year == head->data.year && month < calcMonth2(head->data.month))//Smaller that head
		{
			addfirst(dta);
		}
		else//Not smaller than head
		{
			Node * newNode = (Node *)calloc(1,sizeof(Node));
			newNode->data = dta;
			head->next = newNode;
			size++;
		}
	}
	else if(year < head->data.year || year == head->data.year && month < calcMonth2(head->data.month))//Smaller than head
	{
		addfirst(dta);
	}
	else//List is not empty and contains more than just head Node
	{
		Node * newNode = (Node *)calloc(1,sizeof(Node));
		newNode->data = dta;
		int added = 0;//Tracks if a node has been added or not
		Node * prev = head;
		Node * cur = head->next;
		for(cur;cur != NULL && added != 1;cur = cur->next)
		{
			if(year < cur->data.year || year == cur->data.year && month < calcMonth2(cur->data.month))//Cur bigger than new node
			{
				newNode->next = cur;
				prev->next = newNode;
				size++;
				added = 1;
			}
			prev = cur;
		}
		if(added == 0)//End of list and node not added yet
		{
			newNode->next = cur;
			prev->next = newNode;
			size++;
			added = 1;
		}
	}
}


/*
addFirst function:
Simply adds a Node containing weather data to
the begining of the linked list
*/
void addfirst(Weather d)
{
	Node * t = (Node *)calloc(1,sizeof(Node));
	t->data = d;
	t->next = head;
	head = t;
	size++;
}


/*
clearList function:
Simply calls recurseClear()
*/
void clearList()
{
	recurseClear(head);
}


/*
recurseClear function:
Recursivley moves through the linkedList of
Weather data, and frees all memory.
*/
void recurseClear(Node * cur)
{
	if(cur->next != NULL)//Not at end of list yet
	{
		recurseClear(cur->next);	
	}
	free(cur->data.month);
	free(cur->data.temps);
	free(cur);
	cur = NULL;
}



