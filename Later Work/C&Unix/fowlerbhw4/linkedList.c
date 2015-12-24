//Brandon Fowler
//CSCD340
//Homework 4

#include "linkedList.h"

/*
addLast()
Description: Adds a Node to the end of the Linked List
----------------------------------------------------------------------------
*/
void addLast(LinkedList * theList, Node * nn){
	if(theList->head == NULL)
	{
		theList->head = nn;
		theList->size = 1;
	}
	else
	{
		Node * cur = theList->head;
		for(cur;cur->next != NULL;cur=cur->next);
		cur->next = nn;
		theList->size = theList->size+1;
	}
	
}

/*
addFirst()
Description: Adds a Node to the start of the Linked List.
----------------------------------------------------------------------------
*/
void addFirst(LinkedList * theList, Node * nn){
	nn->next = theList->head;
	theList->head = nn;
	theList->size = theList->size+1;
}

/*
clearList()
Description: Cleans all memory associated with the Linked List.
----------------------------------------------------------------------------
*/
void clearList(LinkedList * theList){
	
	Node * cur = theList->head;
	Node * prev;
	if(cur != NULL){
		while(cur->next != NULL)
		{
			prev = cur;
			cur = cur->next;
			free(prev->data);
			free(prev);
			prev = NULL;
		}	
		free(cur->data);
		free(cur);
		cur = NULL;
	}
	free(theList);
}

/*
linkedList()
Description: Dynamically allocates a Linked List and returns it.
----------------------------------------------------------------------------
*/
LinkedList * linkedList(){
	LinkedList * list = (LinkedList*)calloc(1,sizeof(LinkedList));
	return list;
}

/*
buildNode()
Description: Dynamically allocates a Node and returns it.
----------------------------------------------------------------------------
*/
Node * buildNode(){
	Node * ptr = (Node*)calloc(1,sizeof(Node));
	return ptr;
}

