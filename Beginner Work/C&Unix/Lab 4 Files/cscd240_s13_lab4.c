#include <stdio.h>
#include <stdlib.h>
#include "cscd240_s13_lab4.h"

/*
Author: Brandon Fowler
Description: This program takes in a list of up to 25 numbers from the
user. It then calls functions to make an array for the numbers, sort the array, 
and find the median, and mean.
*/

const int SIZE = 25;//Size of array

//Start of main
int main()
{
	int num;//Stores ammount of numbers to be entered into array
	int choice;//Stores choice for menu
	double median;
	double mean;
	int array[SIZE];
	
	num = readNum();
	fillArray(array,num);
	sort(array,num);
	
	do
	{
		choice = menu();
	
		if(choice == 1)
		{
			fillArray(array, num);
			sort(array, num);
		}
		else if(choice == 2)
		{
			num = readNum();
			fillArray(array, num);
			sort(array, num);
		}
		else if(choice == 3)
		{
			mean = findMean(array, num);
			median = findMedian(num, array);
			printResults(mean, median);
		}
		
		
	}while(goAgain());

	return 0;		
}//End of main
