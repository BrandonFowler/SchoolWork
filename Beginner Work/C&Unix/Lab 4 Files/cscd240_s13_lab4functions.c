#include "cscd240_s13_lab4.h"

/*
Author: Brandon Fowler
Description: This file contains all of the functions used by the 
main program of lab4.
*/

extern const int SIZE; //Size of array

//readNum function
//Reads in number from keyboard stating how many numbers will fill array
int readNum()
{
	int num; //Holds keyboard input
	printf("How many numbers?(Between 1 and 25)"); 
	scanf("%d",&num); 
	if(num > 0 && num <26) //Check to make sure input is valid
	{
		return num; 
	}
	else
	{
		num = readNum(); //Starts over
		return num; //Returns when valid input is recieved
	}
}

//fillArray function
//Fills array with user input
void fillArray(int array[],int num)
{	
	printf("\n \n");
	printf("Please enter %d numbers: \n",num);
	printf("\n \n"); 
	int i = 0; //Counter in for loop
	for(i;i<num;i++) 
	{
		scanf("%d", &array[i]); //Get keyboard input to fill array 
	}
	printf("\n \n");
}

//sort function
//Sorts array of ints
void sort(int array[],int num)
{
	int start = 0;
	int cur = 0;
	int smallest;
	int temp;
	for(start;start<num;start++)
	{
		cur = start;
		smallest = start;
		for(cur; cur<num;cur++)
		{
			if(array[cur]<array[smallest])
			{
				smallest = cur;
			}
		}
		temp = array[start];
		array[start] = array[smallest];
		array[smallest] = temp;
	}
	
}

//menu function
//Simply displays menu and returns valid user choice
int menu()
{
	int choice;
	printf("Please choose: \n \n");
	printf("1) Change values in the array \n \n");
	printf("2) Change N and the values in the array \n \n");
	printf("3) Find and display the mean \n \n");
	printf("   Find and display the median \n \n");
	printf("Choice-->");
	scanf("%d",&choice);
	printf("\n \n");
	
	if(choice != 1 && choice != 2 && choice !=3)
	{
		choice = menu();
	}
	
	return choice;
}

//findMean function
//Finds the mean of an int array
double findMean(int array[],int num)
{
	double mean;
	double total = 0.0;
	int i = 0;
	for(i;i<num;i++)
	{
		total = total+array[i];	
	}
	mean = total/num;
	return mean;
}

//findMedian function
//Finds the medain value in an int array
double findMedian(int num,int array[])
{
	double median = 0.0; //Initialize median variable
	int middleIndexA; //Store index of median
	int middleIndexB; //In case of even amount of numbers, store second value needed to find true median
	double tot = 0.0;//Initialize variable to add array values at both indexes 
	if(num%2 != 0) //Odd amount of numbers in array
	{
		middleIndexA = (num/2); //Preform integer division to get the middle index
		median = median+array[middleIndexA]; //Store value in array at middle index as median
		return median;
	}
	else //Even amount of numbers in array
	{
		middleIndexA = num/2; //Get first index of two middle values
		middleIndexB = (num/2)-1; //Get second index of two middle values
		tot = tot+array[middleIndexA]+array[middleIndexB]; //Add values
		median = tot/2.0; //Average both values to produce median
		return median;
	}	
}

//goAgain function
//Prompts user to go again then takes response in 
//the form of y, Y, n or N and returns 1 or 0
int goAgain()
{
	printf("Go Again: ");
	while(fgetc(stdin)!='\n');
	char ans = getchar();//Get user response
	if(ans == 'Y' || ans == 'y')
	{
		printf("\n \n");
		return 1;
	}
	else if(ans == 'N' || ans == 'n')
	{
		printf("\n \n");
		return 0;
	}
	else//Bad entry
	{
		int returnValue = goAgain();//Start over
		return returnValue;//Return after valid input
	}
}

//printResults function
//Prints the mean and Median values
void printResults(double mean,double median)
{
	printf("The Mean is: %lf      The Median is: %lf \n \n",mean,median);
	printf("\n \n");
}
