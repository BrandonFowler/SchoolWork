#include "hw5.h"


/*
hw5.c
General functions file for hw5
Author: Brandon Fowler
Class: cscd240-02
*/


Node * head = NULL;
int size = 0;


//Opens an input file with low level commands
int openInputFile()
{
	char temp[100] = "NULL";
	printf("Please enter file name:");
	scanf("%s",temp);
	int fdRead = open(temp,O_RDONLY);
	while(fgetc(stdin)!='\n');
	if(fdRead == -1)//File Doesn't exist
	{
		printf("Input file doesn't exist!\n");
		exit(-1);//End program
	}
	return fdRead;
}


//Reads from file into array, splits array, parse data,
//builds Stock structure, passes to addOrdered function.
void buildList(int fdRead, int lpr)
{
	char input[4096]="NULL";//Holds data from input file
	char temp;//Holds chars from input one by one
	char name[50]="NULL";//Holds parsed names
	char symbol[50]="NULL";//Holds parsed symbols
	double sharP;//Holds parsed share prices
	int shares;//Holds parsed share numbers
	int count = 0;//Array index
	
	Stock toAdd;//Initialize Stock structure
	int res = read(fdRead,&temp,1);
	while(res != 0)
	{
		input[count] = temp;
		count++;
		res = read(fdRead,&temp,sizeof(temp));			
	}
	close(fdRead);

	//Begin parsing
	char * t = strtok(input," ");
	strcpy(symbol,t);
	while(t != NULL)
	{
		t = strtok(NULL,"\n");
		strcpy(name,t);
		
		t = strtok(NULL,"\n");
		sharP = atof(t);

		t = strtok(NULL,"\n");
		shares = atoi(t);
		
		
		toAdd.c.name = (char*)calloc(50,sizeof(char));
		strcpy(toAdd.c.name,name);
		
		
		toAdd.c.symbol = (char*)calloc(50,sizeof(char));
		strcpy(toAdd.c.symbol,symbol);

		toAdd.shares = shares;

		toAdd.price = sharP;
		addOrdered(toAdd);
		
		t = strtok(NULL," ");
		if(t != NULL)
		{
			strcpy(symbol,t);
		}
	}
}


//Presents user with menu, gets and returns choice
int menu()
{
	int choice;
	printf("\n\n");
	printf("You are at the options menu!\n");
	printf("1) Print stock value\n");
	printf("2) Buy stock\n");
	printf("3) Sell stock\n");
	printf("4) Print stock info\n");
	printf("5) Quit\n");
	printf("Type the number of your choice:");
	scanf("%d",&choice);
	if(choice < 1 || choice > 5)//Bounds check
	{
		printf("\n\n");
		printf("Sorry choice is invalid");
		printf("\n\n");
		choice = menu();
	}
	return choice;
}


//Calls recurse clean function
void cleanUp()
{
	recurseClean(head);
}


//Recursivley frees all data in the linked list
void recurseClean(Node * cur)
{
	if(cur->next != NULL)
	{
		recurseClean(cur->next);
	}
	free(((Stock*)(cur->data))->c.name);
	free(((Stock*)(cur->data))->c.symbol);
	free(cur->data);
	free(cur);
	cur = NULL;
}


