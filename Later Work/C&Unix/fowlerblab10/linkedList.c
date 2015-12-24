//Brandon Fowler
//CSCD340
//Lab10

#include "linkedList.h"
#define MAX 100

/*
addLast()
Description: Adds a node to the end of the linked list
===============================================================
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
Description: Adds a node to the start of the linked list
===============================================================
*/
void addFirst(LinkedList * theList, Node * nn){
	nn->next = theList->head;
	theList->head = nn;
	theList->size = theList->size+1;
}

/*
clearList()
Description: Frees all memory associated with the linked list
===============================================================
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
Description: Dynamically allocate linked list then return pointer
===============================================================
*/
LinkedList * linkedList(){
	LinkedList * list = (LinkedList*)calloc(1,sizeof(LinkedList));
	list->size = 0;
	return list;
}

/*
buildNode()
Description: Dynamically allocate node then return pointer
===============================================================
*/
Node * buildNode(){
	Node * ptr = (Node*)calloc(1,sizeof(Node));
	return ptr;
}

