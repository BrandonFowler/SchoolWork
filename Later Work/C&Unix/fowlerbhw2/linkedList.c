//CSCD340
//Homework 2
//Brandon Fowler

#include "linkedList.h"
#define MAX 100

//addLast: Simply adds node to end of list
//========================================================================
void addLast(LinkedList * theList, Node * nn){
	if(theList->head == NULL)
	{
		theList->head = nn;
	}
	else
	{
		Node * cur = theList->head;
		for(cur;cur->next != NULL;cur=cur->next);
		cur->next = nn;
	}
	theList->size = theList->size+1;
}

//addFirst: Simply adds node to start of list
//========================================================================
void addFirst(LinkedList * theList, Node * nn){
	nn->next = theList->head;
	theList->head = nn;
	theList->size = theList->size+1;
}

//clearList: Cleans list memory in a generic manner
//========================================================================
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

//linkedList: Returns a heap allocated LinkedList
//========================================================================
LinkedList * linkedList(){
	LinkedList * list = (LinkedList*)calloc(1,sizeof(LinkedList));
	return list;
}

//buildNode: Returns a heap allocated Node
//========================================================================
Node * buildNode(){
	Node * ptr = (Node*)calloc(1,sizeof(Node));
	return ptr;
}

