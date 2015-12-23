#include "lab14.h"


/*
Lab14.c
Function file for lab14
Author: Brandon Fowler
Class:cscd240-02
*/


//Reads in a string from the user
void readString(char theString[650])
{
	printf("\n\n");
	printf("Please Enter a String:");
	fgets(theString,650,stdin);
}


//Presents user with a menu choice and returns the choice
int menu()
{
	int choice;
	printf("\n\n");
	printf("Menu\n");
	printf("1) Enter a new string\n");
	printf("2) Print string statistics\n");
	printf("3) Quit\n");
	printf("Choice-->");
	scanf("%d",&choice);
	while(fgetc(stdin)!='\n');//Clear bufffer
	if(choice > 3 || choice < 1)//Check for valid input
	{	
		printf("\n\n");
		printf("Invalid choice! Try Again!");
		choice = menu();//Re-prompt for choice
	}
	return choice;
}


//Parses the string into words and print statistics
void parseString(char theString[650])
{
	char tempS[650];//Temporary string to be tokenized
	strcpy(tempS, theString);
	int x = 0;
	for(x;x < strlen(tempS);x++)
	{
		if(tempS[x] == '\n')
		{
			tempS[x] = '\0';//Strip carriage return
		}
	}
	int tempI;//Holds each string length
	int longest;//Holds longest string length
	int words = 0;//Amount of words in string
	char * t = strtok(tempS," ");
	longest = strlen(t);
	while(t != NULL)
	{
		words++;
		tempI = strlen(t);
		t = strtok(NULL," ");
		if(tempI > longest)
		{
			longest = tempI;
		}
	}
	printf("\n\n");
	printf("There were %d words in the string\n",words);
	printf("The longest word was %d letters in size\n",longest);
}
