#include "lab8.h"

/*
lab8.c
Functions for lab8
Author: Brandon Fowler
Class: CSCD240-02
*/

//Opens file specified by user
FILE * openFilePrompt()
{
	char fileName[100];
	printf("Please specify input file name(example.txt):");
	scanf("%s",fileName);
	FILE * fin = fopen(fileName,"r");
	if(fin == NULL)
	{
		fin = openFilePrompt();
	}
	return fin;	
}

//Counts lines in file
int countLines(FILE * fin)
{
	int count = 0;
	char temp[100];
	fgets(temp,100,fin);
	while(!feof(fin))
	{
		count++;
		fgets(temp,100,fin);
	}
	rewind(fin);
	return count;
}

//Fills Person array with data from file
Person * fillArray(int total, FILE * fin)
{
	Person * array = (Person *)calloc(total/2,sizeof(Person));
	int x;
	for(x=0;x<total/2;x++)
	{
		fscanf(fin,"%s %s %d",array[x].fname,array[x].lname,&array[x].id);
	}
	return array;
}

//Displays menu, and gets choice from user
int menu()
{
	int choice;
	printf("\n\n");
	printf("You are at the menu options!\n");
	printf("1) Print the array sorted by last name\n");
	printf("2) Print the array sorted by first name\n");
	printf("3) Print the array sorted by id\n");
	printf("4) Quit\n");
	printf("Type the number of your choice:");
	scanf("%d",&choice);
	if(choice < 1 || choice > 4)
	{
		choice = menu();
	}
	return choice;
}

//Sorts Person array by last name, then prints it
void printLastNameSortedArray(Person * array, int total)
{
	int inner,outer,min;
	Person temp;
	for(outer=0;outer<(total/2)-1;outer++)
	{
		min = outer;
		for(inner=outer+1;inner<total/2;inner++)
		{
			if(strcmp(array[inner].lname,array[min].lname)<0)
			{
				min = inner;
			}
		}
		temp = array[min];
		array[min] = array[outer];
		array[outer] = temp;
	}
	printf("\n\n");
	printf("Sorting by last name:\n");
	int x;
	for(x=0;x<total/2;x++)
	{
		printf("%s %s ID: %d\n",array[x].fname,array[x].lname,array[x].id);
	}
	
}

//Sorts Person array by first name, then prints it
void printFirstNameSortedArray(Person * array, int total)
{
	int inner,outer,min;
	Person temp;
	for(outer=0;outer<(total/2)-1;outer++)
	{
		min = outer;
		for(inner=outer+1;inner<total/2;inner++)
		{
			if(strcmp(array[inner].fname,array[min].fname)<0)
			{
				min = inner;
			}
		}
		temp = array[min];
		array[min] = array[outer];
		array[outer] = temp;
	}
	printf("\n\n");
	printf("Sorting by first name:\n");
	int x;
	for(x=0;x<total/2;x++)
	{
		printf("%s %s ID: %d\n",array[x].fname,array[x].lname,array[x].id);
	}
}

//Sorts Person array by ID, then prints it
void printIDSortedArray(int total, Person * array)
{
	int inner,outer,min;
	Person temp;
	for(outer=0;outer<(total/2)-1;outer++)
	{
		min = outer;
		for(inner=outer+1;inner<total/2;inner++)
		{
			if(array[inner].id < array[min].id)
			{
				min = inner;
			}
		}
		temp = array[min];
		array[min] = array[outer];
		array[outer] = temp;
	}
	printf("\n\n");
	printf("Sorting by ID number:\n");
	int x;
	for(x=0;x<total/2;x++)
	{
		printf("%s %s ID: %d\n",array[x].fname,array[x].lname,array[x].id);
	}
}
