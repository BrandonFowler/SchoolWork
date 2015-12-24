//CSCD340
//Lab4
//Brandon Fowler

#include "linkedList.h"
#define MAX 100


LinkedList * linkedList(){
	LinkedList * list = (LinkedList*)calloc(1,sizeof(LinkedList));
	return list;
}
Node * buildNode(){
	Node * ptr = (Node*)calloc(1,sizeof(Node));
	return ptr;
}
void sort(LinkedList * theList){
	Node * start = theList->head;
	Node * next = start->next;
	char * temp;
	while(next != NULL){
		while(next != start ){
			if(strncmp((char*)next->data,(char*)start->data,100) <0 ){
				temp = start->data;
				start->data = next->data;
				next->data = temp;
			}
			start = start->next;
		}
		start = theList->head;
		next = next->next;
	}	
}

void clearList(LinkedList * theList){
	Node * cur = theList->head;
	Node * prev;
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
	free(theList);
}

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
void addFirst(LinkedList * theList, Node * nn){
	nn->next = theList->head;
	theList->head = nn;
	theList->size = theList->size+1;
}


