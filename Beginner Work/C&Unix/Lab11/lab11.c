#include "lab11.h"

/*
lab11.c
Functions for lab11
Author: Brandon Fowler
Class: CSCD240-02
*/





//Fills Person array with data from file
Person * fillArray(int total, FILE * fin)
{
	rewind(fin);
	Person * array = (Person *)calloc(total,sizeof(Person));
	char fname[100];
	char lname[100];
	char * fn;
	char * ln;
	int x;
	for(x=0;x<total;x++)
	{
		fscanf(fin,"%s",fname);
		fscanf(fin,"%s",lname);
		fscanf(fin,"%d",&array[x].id);
		fn = (char *)calloc(100,sizeof(char));
		ln = (char *)calloc(100,sizeof(char));
		strcpy(fn,fname);
		strcpy(ln,lname);
		array[x].fname=fn;
		array[x].lname=ln;
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
	qsort(array,total,sizeof(Person),lastSort);
	printf("\n\n");
	printf("Sorting by last name:\n");
	int x;
	for(x=0;x<total;x++)
	{
		printf("%s %s ID: %d\n",array[x].fname,array[x].lname,array[x].id);
	}
	
}

//Sorts Person array by first name, then prints it
void printFirstNameSortedArray(Person * array, int total)
{
	qsort(array,total,sizeof(Person),firstSort);
	printf("\n\n");
	printf("Sorting by first name:\n");
	int x;
	for(x=0;x<total;x++)
	{
		printf("%s %s ID: %d\n",array[x].fname,array[x].lname,array[x].id);
	}
}

//Sorts Person array by ID, then prints it
void printIDSortedArray(int total, Person * array)
{
	qsort(array,total,sizeof(Person),idSort);
	printf("\n\n");
	printf("Sorting by ID number:\n");
	int x;
	for(x=0;x<total;x++)
	{
		printf("%s %s ID: %d\n",array[x].fname,array[x].lname,array[x].id);
	}
}

//Frees up in use memory before program close
void cleanUp(Person * array, int total)
{
	int x;
	for(x=0;x<total;x++)
	{
		free(array[x].fname);
		free(array[x].lname);
		array[x].fname = NULL;
		array[x].lname = NULL;
	}
	free(array);
}

//Compares Last names and returns integer
int lastSort(const void* p1,const void* p2)
{
	Person * P1 = (Person *)p1;
	Person * P2 = (Person *)p2;
	return strcmp(P1->lname,P2->lname);
}

//Compares First names and returns integer
int firstSort(const void* p1,const void* p2)
{
	Person * P1 = (Person *)p1;
	Person * P2 = (Person *)p2;
	return strcmp(P1->fname,P2->fname);
}

//Compares IDs names and returns integer
int idSort(const void* p1,const void* p2)
{
	Person * P1 = (Person *)p1;
	Person * P2 = (Person *)p2;
	return (P1->id - P2->id);
}


