//CSCD340
//Lab4
//Brandon Fowler

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "linkedList.h"

#define MAX 100

struct com
{
	int num;
	char * theCommand;
};
typedef struct com commands;

int commandCount = 0;

void strip(char *s);
void clean(int argc, char **argv);
void printargs(int argc, char **argv);
int makeargs(char *s, char *** argv);
void forkIt(char ** argv);
void freeCommands(LinkedList * list);
void newCommand(char * s,LinkedList * commandList);
void printList(LinkedList * theList);


int main()
{
	LinkedList * commandList = (LinkedList *)calloc(1,sizeof(LinkedList));
	char **argv = NULL, s[MAX];
	int argc;
	printf("Please enter a command (exit to exit) ");
	fgets(s, MAX, stdin);
	strip(s);
	newCommand(s,commandList);

 	while(strcmp(s, "exit") != 0)
  	{
		if(strcmp(s, "print") == 0)
		{
			printList(commandList);		
		}
		else{
			argc = makeargs(s, &argv);
  			if(argc != -1)
  			{
    				printf("There are %d tokens.\nThe tokens are:\n", argc);
    				printargs(argc, argv);
				forkIt(argv);
			
			}// end if
		
			clean(argc, argv);
	  		argv = NULL;
		}

		printf("Please enter a command (exit to exit) ");
		fgets(s, MAX, stdin);
		strip(s);
		newCommand(s,commandList);
	}// end while
	
	freeCommands(commandList);
	clearList(commandList);

}// end main

void newCommand(char * s,LinkedList * commandList){
	commandCount++;
	Node * newNode = (Node*)calloc(1,sizeof(Node));
	newNode->data = (commands *)calloc(1,sizeof(commands));
	((commands*)(newNode->data))->theCommand = (char *)calloc(strlen(s)+1,sizeof(char));
	((commands*)(newNode->data))->num = commandCount;
	strcpy(((commands*)(newNode->data))->theCommand,s);
	addLast(commandList, newNode);
}

void strip(char *s)
{
	if(strlen(s) > 1){
  		int len;
  		len = strlen(s);
  		if(s[len - 2] == '\r')
    		s[len -2] = '\0';

  		else if(s[len - 1] == '\n')
    		s[len -1] = '\0';
	}
}// end strip

void clean(int argc, char **argv)
{
	int i = 0;
	while(argv[i] != NULL){
		free(argv[i]);
		i++;
	}
	free(argv);
}// end clean

void printargs(int argc, char **argv)
{
	int x;
	for(x = 0; x < argc; x++)
		printf("%s\n", argv[x]);

}// end printargs

int makeargs(char *s, char *** argv)
{

	if(strlen(s)%16 == 0){
		strcat(s," ");
	}
	int tokenNumber = 0;
	char tempS[strlen(s)];
	strcpy(tempS, s);
	char delimiter[] = " ";
	char * curToken="";
	char * savePtr;

	//Find number of tokens
	for (strtok_r(tempS, delimiter,&savePtr); curToken; curToken = strtok_r(NULL, delimiter,&savePtr))
	{
		tokenNumber++;	
	}
	
	//Allocate pointers for amount of tokens + 1
	(*argv)=(char**)calloc(tokenNumber+1, sizeof(char*));
	
	//Allocate space for indivudual tokens and copy them
	int i;
	int length = 0;
	curToken = strtok_r(s, delimiter, &savePtr);
	for (i=0; i < tokenNumber; i++)
	{
		length = strlen(curToken);
		(*argv)[i] = (char*)calloc(length, sizeof(char*));
		strcpy((*argv)[i], curToken);
		
		curToken = strtok_r(NULL, delimiter, &savePtr);
	}

	//Return number of tokens or failure
	if (tokenNumber > 0){
	 	return tokenNumber;
	}
	else{
		return -1;
	}
}// end makeArgs

void forkIt(char ** argv)
{
	pid_t id = fork();

	if(id != 0){
		int status;
		waitpid(-1,&status,NULL);
	}
	else{
		int res = execvp(argv[0],argv);	
		if(res < 0){
			printf("Invalid Command\n");
			exit(-1);
		}
	}

}// end forkIt

void freeCommands(LinkedList * list){
	Node* cur = list->head;
	while(cur != NULL){
		free(((commands*)cur->data)->theCommand);
		cur = cur->next;
	}
}

void printList(LinkedList * theList){
	if(theList->head == NULL){
		printf("Empty List\n");
	}
	else{	
		Node* cur = theList->head;
		while(cur != NULL){
			printf("%d ",((commands*)cur->data)->num);
			printf("%s\n",((commands*)cur->data)->theCommand);
			cur = cur->next;
		}
	}
}

