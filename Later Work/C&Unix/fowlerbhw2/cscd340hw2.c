//CSCD340
//Homework 2
//Brandon Fowler

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cscd340hw2.h"

#define MAX 100

int main()
{
	char s[MAX];
	LinkedList * linkedAlias = linkedList();
	LinkedList * linkedHistory = linkedList();
	int histTracker = 0;//Keeps track of history numbers
	linkedHistory->printSize = 100;//Default number for HISTCOUNT
	linkedHistory->writeSize = 100;//Default number for HISTFILECOUNT
	printf("Loading History\n");
	loadHistory(linkedHistory, &histTracker);//Load history from mysh.mysh_history(If exists)
	printf("Loading .myshrc\n");
	loadMyshrc(linkedAlias, linkedHistory, &histTracker);//Load .myshrc files
	
	printf("?: ");
	fgets(s, MAX, stdin);
	strip(s);

	if(strcmp(s,"\n") != 0){
		newHistory(s,linkedHistory,&histTracker);//Store command in history
	}

	while(strcmp(s, "exit") != 0)
	{
		interpreter(linkedAlias, linkedHistory, &histTracker, s);//Run command(If possible)

		printf("?: ");
		fgets(s, MAX, stdin);
		strip(s);

		if(strcmp(s,"\n") != 0){
			newHistory(s,linkedHistory,&histTracker);
		}
	}

	//Write history, and clean lists
	outputHistory(linkedHistory);
	clearlinkedAlias(linkedAlias);
	clearList(linkedAlias);
	clearHistory(linkedHistory);
	clearList(linkedHistory);
}




