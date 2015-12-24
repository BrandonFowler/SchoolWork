//Brandon Fowler
//CSCD340
//Homework 3

#include "linkedList.h"

/*
linkedList()
Description: Makes a dynamically allocated LinkedList
then returns it.
==================================================================
*/
LinkedList * linkedList(){
	LinkedList * list = (LinkedList*)calloc(1,sizeof(LinkedList));
	return list;
}

/*
buildNode()
Description: Makes a dynamically allocated Node
then returns it.
==================================================================
*/
Node * buildNode(){
	Node * ptr = (Node*)calloc(1,sizeof(Node));
	return ptr;
}

/*
addLast()
Description: Adds a Node to the end
of the LInkedList
==================================================================
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
Description: Adds a Node to the start
of the LinkedList
==================================================================
*/
void addFirst(LinkedList * theList, Node * nn){
	nn->next = theList->head;
	theList->head = nn;
	theList->size = theList->size+1;
}

/*
clearList()
Description: Frees all memory allocated
in the LinkedList
==================================================================
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

