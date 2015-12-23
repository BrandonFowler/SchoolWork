#include "hw3.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/*
hw3.c
Functions for hw3
Author: Brandon Fowler
Class: CSCD240-02
*/



FILE * fout = NULL;

//Opens a input file specified by the user
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

//Opens a output file specified by the user
FILE * openOutFilePrompt()
{
	char fileName[100];
	printf("Please specify output file name(example.txt):");
	scanf("%s",fileName);
	FILE * fin = fopen(fileName,"w");
	return fin;	
}

//Counts the line of the file
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

//Fills a 2D array with data from the file
int ** fillArray(int total, FILE * fin)
{
	char month[100];
	int days;
	int x, y, m;
	
	int ** array = (int **)calloc(total,sizeof(int*));
	for(x=0;x<total;x++)
	{
		
		fscanf(fin,"%s",month);
		days = calcDays(month);
		m = calcMonth(month);
		array[x] = (int*)calloc(days+2,sizeof(int));
		*(array[x]+0) = m;
		for(y=1;y<days+2;y++)
		{
			fscanf(fin,"%d",(array[x]+y));
		}
	}
	fclose(fin);
	fin = NULL;
	return array;
}

//Displays a menu for the user
int menu()
{
	int choice;
	printf("\n\n");
	printf("You are at the menu options!\n");
	printf("1) Display or Write a months temperatures, and the average of the months tempuratures for all years.\n");
	printf("2) Display or write a years tempuratures, and the yearly average temperature for that year.\n");
	printf("3) Add a new months data to the array.\n");
	printf("4) Quit\n");
	printf("Type the number of your choice:");
	scanf("%d",&choice);
	if(choice < 1 || choice > 4)
	{
		choice = menu();
	}
	return choice;
}

//Displays temps and average for the month specified
void displayMonthData(int total, int ** temps)
{
	int x;
	int y;
	int length;
	int tot = 0;
	int count = 0;
	int avg;
	char Smonth[100];
	printf("\n\n");
	printf("What month would you like to display?:");
	scanf("%s",Smonth);
	int month = calcMonth(Smonth);
	if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
	{
		length = 33;
	}
	if(month == 2)
	{
		length = 30;
	}
	if(month == 4 || month == 6 || month == 9 || month == 11)
	{
		length = 32;
	}
	printf("\n\n");
	printf("You have a couple options!\n");
	printf("1) Print to screen.\n");
	printf("2) Write to file.\n");
	printf("Type the number of an option:");
	int choice;
	scanf("%d",&choice);
	printf("\n\n");
	if(choice == 1)
	{
		for(x=0;x<total;x++)
		{
			if(*(temps[x]+0) == month)
			{
				printf("%s ",Smonth);
				for(y=1; y<length;y++)
				{
					printf("%d ",*(temps[x]+y));
					tot = tot + *(temps[x]+y);
					count++;		
				}
				printf("\n");
				tot = tot - *(temps[x]+1);	
			}
		}
		avg = tot/count;
		printf("The average tempurature is %d\n",avg);	
	}
	if(choice == 2)
	{
		if(fout == NULL);
		{
			char fileName[100];
			printf("What file would you like to write too(example.txt)?:");
			scanf("%s",fileName);
			printf("\n\n");
			fout = fopen(fileName,"w");
		}

		for(x=0;x<total;x++)
		{
			if(*(temps[x]+0) == month)
			{
				fprintf(fout,"%s ",Smonth);
				for(y=1; y<length;y++)
				{
					fprintf(fout,"%d ",*(temps[x]+y));
					tot = tot + *(temps[x]+y);
					count++;		
				}
				fprintf(fout,"\n");
				tot = tot - *(temps[x]+1);	
			}
		}
		avg = tot/count;
		fprintf(fout,"The average tempurature is %d\n",avg);
		fprintf(fout,"\n\n");	
	}
}

//Displays temps and average for the month specified
void displayYearData(int total, int ** temps)
{
	int year;
	int x;
	int y;
	int length;
	int tot = 0;
	int count = 0;
	int avg;
	printf("\n\n");
	printf("What year would you like to display?:");
	scanf("%d",&year);
	printf("\n\n");
	printf("You have a couple options!\n");
	printf("1) Print to screen.\n");
	printf("2) Write to file.\n");
	printf("Type the number of an option:");
	int choice;
	scanf("%d",&choice);
	printf("\n\n");
	if(choice == 1)
	{
		printf("Temps for the year %d:\n",year);
		for(x=0; x<total; x++)
		{
		
			if(*(temps[x]) == 1 || *(temps[x]) == 3 || *(temps[x]) == 5  || *(temps[x]) == 7 || *(temps[x]) == 8 || *(temps[x]) == 10 || *(temps[x]) == 12)
			{
				length = 33;
			}
			if(*(temps[x]) == 2)
			{
				length = 30;
			}
			if(*(temps[x]) == 4 || *(temps[x]) == 6 || *(temps[x]) == 9  || *(temps[x]) == 11)
			{
				length = 32;
			}

			if(*(temps[x]+1) == year)
			{
				for(y=2; y<length; y++)
				{
					printf("%d ",*(temps[x]+y));
					tot = tot + *(temps[x]+y);
					count++;
				}
				printf("\n");
			}
		}
		avg = tot/count;
		printf("The average temperature for the year %d is: %d",year,avg);
	}
	if(choice == 2)
	{
		if(fout == NULL);
		{
			char fileName[100];
			printf("What file would you like to write too(example.txt)?:");
			scanf("%s",fileName);
			printf("\n\n");
			fout = fopen(fileName,"w");
		}

		fprintf(fout,"Temps for the year %d:\n",year);
		for(x=0; x<total; x++)
		{
		
			if(*(temps[x]) == 1 || *(temps[x]) == 3 || *(temps[x]) == 5  || *(temps[x]) == 7 || *(temps[x]) == 8 || *(temps[x]) == 10 || *(temps[x]) == 12)
			{
				length = 33;
			}
			if(*(temps[x]) == 2)
			{
				length = 30;
			}
			if(*(temps[x]) == 4 || *(temps[x]) == 6 || *(temps[x]) == 9  || *(temps[x]) == 11)
			{
				length = 32;
			}

			if(*(temps[x]+1) == year)
			{
				for(y=2; y<length; y++)
				{
					fprintf(fout,"%d ",*(temps[x]+y));
					tot = tot + *(temps[x]+y);
					count++;
				}
				fprintf(fout,"\n");
			}
		}
		avg = tot/count;
		fprintf(fout,"The average temperature for the year %d is: %d",year,avg);
		fprintf(fout,"\n\n");
	}	
}

//Allows user to add another month of temperatures
int ** addMoreTemps(int ** temps, int * totalP)
{
	char month[30];
	int year;
	int length;
	int iMonth;
	int x;
	printf("Please state the month you wish to add(Example: january): ");
	scanf("%s",month);
	printf("\n");
	printf("Please state the year: ");
	scanf("%d",&year);
	printf("\n");
	iMonth = calcMonth(month);
	if(iMonth == 1 || iMonth == 3 || iMonth == 5 || iMonth == 7 || iMonth == 8 || iMonth == 10 || iMonth == 12)
	{
		length = 33;
	}
	if(iMonth == 2)
	{
		length = 30;
	}
	if(iMonth == 4 || iMonth == 6 || iMonth == 9 || iMonth == 11)
	{
		length = 32;
	}
	int temp[length];
	temp[0] = iMonth;
	temp[1] = year;
	for(x=2; x<length; x++)
	{
		printf("Enter a tempurature: ");
		scanf("%d", &temp[x]);
		printf("\n");
	}
	int ** array = (int **)calloc((*totalP)+1,sizeof(int*));
	for(x=0; x<(*totalP); x++)
	{
		array[x] = temps[x];
	}
	
	array[*totalP+1] = (int*)calloc(length,sizeof(int));
	int y;
	for(y=0; y<length; y++)
	{
		*(array[(*totalP+1)]+y) = *(temp+y);
	}
	
	return array;
	
}

//Cleans up memory
void cleanUp(int total, int ** temps)
{
	int x;
	for(x=0;x<total;x++)
	{
		free(temps[x]);
	}
	free(temps);
	temps = NULL;
	if(fout != NULL)
	{
		fclose(fout);
		fout = NULL;
	}
}

//Returns an integer representation of a month
int calcDays(char month[100])
{
	if(strncmp("january", month, 7)==0)
	{
		return 31;
	}
	else if(strncmp("february", month, 8)==0)
	{
		return 28;
	}
	else if(strncmp("march", month, 5)==0)
	{
		return 31;
	}
	else if(strncmp("april", month, 5)==0)
	{
		return 30;
	}
	else if(strncmp("may", month, 3)==0)
	{
		return 31;
	}
	else if(strncmp("june", month, 4)==0)
	{
		return 30;
	}
	else if(strncmp("july", month, 4)==0)
	{
		return 31;
	}
	else if(strncmp("august", month, 6)==0)
	{
		return 31;
	}
	else if(strncmp("september", month, 9)==0)
	{
		return 30;
	}
	else if(strncmp("october", month, 7)==0)
	{
		return 31;
	}
	else if(strncmp("november", month, 8)==0)
	{
		return 30;
	}
	else if(strncmp("december", month, 8)==0)
	{
		return 31;
	}
}

//Returns how many days are in the month given
int calcMonth(char month[100])
{
	if(strncmp("january", month, 7)==0)
	{
		return 1;
	}
	else if(strncmp("february", month, 8)==0)
	{
		return 2;
	}
	else if(strncmp("march", month, 5)==0)
	{
		return 3;
	}
	else if(strncmp("april", month, 5)==0)
	{
		return 4;
	}
	else if(strncmp("may", month, 3)==0)
	{
		return 5;
	}
	else if(strncmp("june", month, 4)==0)
	{
		return 6;
	}
	else if(strncmp("july", month, 4)==0)
	{
		return 7;
	}
	else if(strncmp("august", month, 6)==0)
	{
		return 8;
	}
	else if(strncmp("september", month, 9)==0)
	{
		return 9;
	}
	else if(strncmp("october", month, 7)==0)
	{
		return 10;
	}
	else if(strncmp("november", month, 8)==0)
	{
		return 11;
	}
	else if(strncmp("december", month, 8)==0)
	{
		return 12;
	}
}
