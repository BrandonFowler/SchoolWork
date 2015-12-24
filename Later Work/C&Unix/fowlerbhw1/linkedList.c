//CSCD340
//HomeWork 1
//Brandon Fowler

#include "utility.h"

//Initializes a LinkedList and returns the pointer
LinkedList * linkedList(){
	LinkedList * linkedList = (LinkedList*)calloc(1,sizeof(LinkedList));
	return linkedList;
}

//Initializes a Node and returns the pointer
Node * buildNode(){
	Node * node = (Node*)calloc(1,sizeof(Node));
	return node;
}

//Sorts a linked list in alphebetical ascending order
void sort(LinkedList * theList){
	Node * cur = theList->head;
	Node * mark = cur->next;
	char * temp;
	while(mark != NULL){
		while(mark != cur ){
			if(strncmp((char*)mark->data,(char*)cur->data,MAX) < 0 ){
				temp = cur->data;
				cur->data = mark->data;
				mark->data = temp;
			}
			cur = cur->next;
		}
		cur = theList->head;
		mark = mark->next;
	}	
}

//Frees all space allocated by a LinkedList
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
	theList->size = 0;
	free(theList);
}

//Prints a LinkedList in order
void printList(LinkedList * theList){
	if(theList->head == NULL){
		printf("Empty List\n");
	}
	else{	
		Node* cur = theList->head;
		while(cur != NULL){
			printf("%s\n",(char*)(cur->data));
			cur = cur->next;
		}
	}
}

//Adds a Node to the end of a LinkedList
void addLast(LinkedList * theList, Node * nn){
	char * input = (char*)calloc(MAX,sizeof(char));
	printf("Please enter the word you would like to add:");
	scanf("%s",input);
	nn->data = input;
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
	theList->size++;
}

//Adds a Node to the beginning of a LinkedList
void addFirst(LinkedList * theList, Node * nn){
	char * input = (char*)calloc(MAX,sizeof(char));
	printf("Please enter the word you would like to add:");
	scanf("%s",input);
	nn->data = input;
	nn->next = theList->head;
	theList->head = nn;
	theList->size++;
}

//Removes a specified Node from a LinkedList
void removeItem(LinkedList * theList, Node * nn){
	free(nn);
	nn = NULL;
	char input[MAX];
	printf("What item would you like to remove?:");
	scanf("%s",input);
	Node* cur = theList->head;
	Node* prev = cur;
	Node* temp;
	while(cur != NULL){
		if(strncmp(input,(char*)cur->data,MAX) == 0){
			if(cur == theList->head){
				theList->head = theList->head->next;
			}
			else{
				prev->next = cur->next;
			}
			temp = cur;
			cur = cur->next;
			free(temp->data);
			free(temp);
			temp = NULL;
		}
		else{
			prev = cur;
			cur = cur->next;
		}
	}	
}

