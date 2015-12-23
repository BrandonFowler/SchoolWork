
#include "hw4.h"


/*
hw4.c
Contains all funtions used to create a linked list of weather
data; then manipulate, add too, and display the weather data, and
averages.
Author: Brandon Fowler
Class:cscd240-02
*/


Node * head = NULL;//Initialize linkedList
int size = 0;//Initialize size of linkedList


/*
createList funtion:
Reads through input file, and constructs a linked list
containing weather data ordered by year then month.
*/
void createList(FILE * fin)
{
	int total = countRecords(fin,1);
	rewind(fin);
	char month[20];
	int Imonth;//Int representaion of month
	int * Tarray;//TempArray
	char * Marray;//MonthString
	int year;
	int days;
	int x, y;
	Weather data;
	for(x=0;x<total;x++)
	{
		fscanf(fin,"%s",month);
		fscanf(fin,"%d",&year);
		days = calcDays(month, year);
		Tarray = (int*)calloc(days,sizeof(int));
		Marray = (char*)calloc(20,sizeof(char));
		strcpy(Marray,month);
		Imonth = calcMonth(month);
		for(y=0; y<days;y++)
		{
			fscanf(fin,"%d",&Tarray[y]);
		}
		data.month = Marray;
		data.year = year;
		data.days = days;
		data.temps = Tarray;
		addOrdered(data,Imonth,year);//Add data to list
	}
	fclose(fin);
	fin = NULL;	
}


/*
menu function:
Simple menu to display options and return choice
*/
int menu()
{
	int choice;
	printf("\n\n");
	printf("You are at the menu options!\n");
	printf("1) Display or Write a months temperatures, and the average of the months tempuratures for all years.\n");
	printf("2) Display or write a years tempuratures, and the yearly average temperature for that year.\n");
	printf("3) Display or write the entire list.\n");
	printf("4) Add a new months data to the list.\n");
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


/*
writeMonthData function:
Seeks all data in linked list pertaining to month
specified by user, and prints the data to the screen 
or file, along with an average.
*/
void writeMonthData()
{
	int x;
	double tot = 0.0;//Added temp values
	double count = 0.0;//Number of temps
	double avg;//Average temp
	int Imonth;//Int representaion of a month
	char Smonth[100];//User entered month
	Node * cur = head;
	printf("\n\n");
	printf("What month would you like to display?:");
	scanf("%s",Smonth);
	Imonth = calcMonth(Smonth);
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
		for(cur;cur != NULL;cur = cur->next)
		{
			if(Imonth == calcMonth2(cur->data.month))
			{
				printf("%s %d ",cur->data.month,cur->data.year);
				for(x=0;x<cur->data.days;x++)
				{
					printf("%d ",cur->data.temps[x]);
					tot = tot + cur->data.temps[x];
					count++;
				}
				printf("\n");
			}
		}
		avg = tot/count;
		printf("\n");
		printf("Average for the year: %lf",avg);
	}
	else if(choice == 2)
	{
		FILE * fout = openOutputPrompt();
		for(cur;cur != NULL;cur = cur->next)
		{
			if(Imonth == calcMonth2(cur->data.month))
			{
				fprintf(fout,"%s %d ",cur->data.month,cur->data.year);
				for(x=0;x<cur->data.days;x++)
				{
					fprintf(fout,"%d ",cur->data.temps[x]);
					tot = tot + cur->data.temps[x];
					count++;
				}
				fprintf(fout,"\n");
			}
		}
		avg = tot/count;
		fprintf(fout,"\n");
		fprintf(fout,"Average for the year: %lf",avg);
		fclose(fout);
	}
	else//Invalid choice
	{
		printf("\n\n");
		printf("Sorry choice is invalid");
		printf("\n\n");
		writeMonthData();
	}
}


/*
writeYearData function:
Seeks all data in linked list pertaining to year
specified by user, and prints the data to the screen 
or file, along with an average.
*/
void writeYearData()
{
	int year;
	double tot = 0.0;
	double count = 0.0;
	double avg;
	int x;
	Node * cur = head;
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
		for(cur;cur != NULL; cur = cur->next)
		{
			if(cur->data.year == year)
			{
				printf("%s %d ",cur->data.month,cur->data.year);
				for(x=0;x<cur->data.days;x++)
				{
					printf("%d ",cur->data.temps[x]);
					tot = tot + cur->data.temps[x];
					count++;
				}
				printf("\n");
			}
		}
		avg = tot/count;
		printf("\n");
		printf("Average for the year: %lf",avg);
	}
	else if(choice == 2)
	{
		FILE * fout = openOutputPrompt();
		for(cur;cur != NULL; cur = cur->next)
		{
			if(cur->data.year == year)
			{
				fprintf(fout,"%s %d ",cur->data.month,cur->data.year);
				for(x=0;x<cur->data.days;x++)
				{
					fprintf(fout,"%d ",cur->data.temps[x]);
					tot = tot + cur->data.temps[x];
					count++;
				}
				fprintf(fout,"\n");
			}
		}
		avg = tot/count;
		fprintf(fout,"\n");
		fprintf(fout,"Average for the year: %lf",avg);
		fclose(fout);
	}	
	else//Invalid Choice
	{
		printf("\n\n");
		printf("Sorry choice is invalid");
		printf("\n\n");
		writeYearData();
	}
}


/*
writeList funtion:
Simple writes all of the weather data in the linked
list to the screen or a file.
*/
void writeTheList()
{
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
		Node * cur = head;
		int x;
		for(cur;cur != NULL;cur = cur->next)
		{
			printf("%s %d ",cur->data.month,cur->data.year);
			for(x=0;x<cur->data.days;x++)
			{
				printf("%d ",cur->data.temps[x]);
			}
			printf("\n");
		}
	}
	else if(choice == 2)
	{
		FILE * fout = openOutputPrompt();
		Node * cur = head;
		int x;
		for(cur;cur != NULL;cur = cur->next)
		{
			fprintf(fout,"%s %d ",cur->data.month,cur->data.year);
			for(x=0;x<cur->data.days;x++)
			{
				fprintf(fout,"%d ",cur->data.temps[x]);
			}
			fprintf(fout,"\n");
		}
		fclose(fout);
	}
	else//Invalid Choice
	{
		printf("\n\n");
		printf("Sorry choice is invalid");
		printf("\n\n");
		writeTheList();
	}
}


/*
addMoreData function:
Allows user to enter more weather data into the linked list
either manually or from a file.
*/
void addMoreData()
{

	printf("\n\n");
	printf("You have a couple options!\n");
	printf("1) Add manually.\n");
	printf("2) Add from file.\n");
	printf("Type the number of an option:");
	int choice;
	scanf("%d",&choice);
	printf("\n\n");
	if(choice == 1)
	{
		Weather data;
		int * Tarray;
		char * Marray;
		char month[20];
		int year;
		int Imonth;
		int days;
		int x;
		printf("Please state the month you wish to add(Example: january): ");
		scanf("%s",month);
		printf("\n");
		printf("Please state the year: ");
		scanf("%d",&year);
		printf("\n");
		Imonth = calcMonth(month);
		days = calcDays(month, year);
		Tarray = (int*)calloc(days,sizeof(int));
		for(x=0; x<days; x++)
		{
			printf("Enter a tempurature: ");
			scanf("%d", &Tarray[x]);
			printf("\n");
		}
		Marray = (char*)calloc(20,sizeof(char));
		strcpy(Marray,month);
		data.month = Marray;
		data.year = year;
		data.days = days;
		data.temps = Tarray;
		addOrdered(data,Imonth,year);
	}
	else if(choice == 2)
	{
		FILE * fin = openFilePrompt();
		createList(fin);
	}
	else//Invalid Choice
	{
		printf("\n\n");
		printf("Sorry choice is invalid");
		printf("\n\n");
		addMoreData();
	}
}


/*
calcDays function:
Returns number of days in the month
*/
int calcDays(char month[100], int year)
{
	if(strncmp("january", month, 7)==0)
	{
		return 31;
	}
	else if(strncmp("february", month, 8)==0)
	{
		if(year%4 == 0)//Leap year
		{
			return 29;
		}
		else
		{
			return 28;
		}
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

/*
calcMonth function:
Returns an integer representation of a month.
Uses static char array
*/
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


/*
calcMonth2 function:
Returns an integer representation of a month.
Uses char *
*/
int calcMonth2(char * month)
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


