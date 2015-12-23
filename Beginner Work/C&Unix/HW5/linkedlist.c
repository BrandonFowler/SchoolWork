#include "stocks.h"
#include "linkedlist.h"
#include "hw5.h"


/*
linkedlist.c
Functions for linkedlists
Author: Brandon Fowler
Class: cscd240-02
*/


extern Node * head;
extern int size;


//Adds a node to the begining of the list
void addfirst(Stock d)
{
	Node * t = (Node *)calloc(1,sizeof(Node));
	t->data = (Stock *)calloc(1,sizeof(Stock));
	*(Stock *)t->data = d;
	t->next = head;
	head = t;
	size++;
}


//Adds a node in order in the list, if the data already
//exists, then it is updated.
void addOrdered(Stock d)
{
	double shareT;//Stores total dollar value
	int totS;//Stores total shares
	double avg;//Stores average of dollar value and shares

	if(head == NULL)
	{
		addfirst(d);
	}
	else if(strcmp(d.c.symbol,((Stock *)(head->data))->c.symbol) < 0)//Smaller than head
	{
		addfirst(d);
	}
	else if(strcmp(d.c.symbol,((Stock *)(head->data))->c.symbol) == 0)//Same as head
	{
		shareT = ((((Stock *)(head->data))->price)*(((Stock *)(head->data))->shares))+((d.price)*(d.shares));
		totS = (((Stock *)(head->data))->shares)+(d.shares);
		avg = (shareT/totS);
		(((Stock *)(head->data))->price) = avg;
		(((Stock *)(head->data))->shares) = totS;
		free(d.c.symbol);
		free(d.c.name);
	}
	else
	{
		
		int added = 0;//Tracks if node is added
		Node * prev = head;//Used in for loop
		Node * cur = head->next;//Used in for loop
		int done = 0;//Tracks if data has been found
		Node * check = head;//Used in for loop
		for(check; check != NULL && done != 1; check = check->next)
		{
			if(strcmp(d.c.symbol,((Stock *)(check->data))->c.symbol) == 0)//Same data found
			{
				//Update data
				shareT = ((((Stock *)(check->data))->price)*(((Stock *)(check->data))->shares))+((d.price)*(d.shares));
				totS = (((Stock *)(check->data))->shares)+(d.shares);
				avg = (shareT/totS);
				(((Stock *)(check->data))->price) = avg;
				(((Stock *)(check->data))->shares) = totS;
				added++;
				done = 1;
				free(d.c.symbol);//Free memory not added to linked list
				free(d.c.name);	//Free memory not added to linked list
			}
		}
		
		Node * newNode;//Initialize node to add
		if(added == 0)//Data not added yet
		{
			
			newNode = (Node *)calloc(1,sizeof(Node));//Initialize node memory
			newNode->data = (Stock *)calloc(1,sizeof(Stock));//Initialize structure memory
			*(Stock *)newNode->data = d;//Add data to node
			for(cur;cur != NULL && added != 1;cur = cur->next)
			{
				if(strcmp(d.c.symbol,((Stock *)(cur->data))->c.symbol) < 0)//Smaller
				{
					//Add in list
					newNode->next = cur;
					prev->next = newNode;
					size++;
					added++;
				}
				prev = cur;
			}
			
		}

		if(added == 0)//Data not added yet
		{
			//Add to end
			newNode->next = cur;
			prev->next = newNode;
			size++;
			added++;
		}
		
	}
}
