#include "lab7.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/*
lab7.c
File containing Functions for lab 7
Author: Brandon Fowler
Class: CSCD240-02
*/


/*
openFileNoPromt Function
Opens file and returns pointer				
*/
FILE * openFileNoPrompt(char * fileName)
{
	FILE * fin = fopen(fileName,"r");
	return fin;
}


/*
openFilePromt Function
Opens file specified by user and returns pointer
NOTE: I couldn't figure out how to error check for
invalid file names. If you enter a bad file name
the program will fail.				
*/
FILE * openFilePrompt()
{
	char fileName[10];
	printf("\n");
	printf("Please enter a valid name of the input file(example.txt):");
	scanf("%s",fileName);
	FILE * fin = fopen(fileName,"r");
	return fin;
	
}


/*
readName Function
Reads the name from the input file and returns it
*/
char * readName(FILE * fin)
{
	char name[100];
	fscanf(fin,"%[^\n]s",name);
	char * array = (char *)calloc(strlen(name)+1,sizeof(char));
	strcpy(array,name);
	printf("\n");
	printf("Name: %s",array);
	printf("\n\n");
	return array;
}


/*
createAndFillArray Function
Reads lines from file and populates arrays
*/
int * createAndFillArray(int * total, FILE * fin)
{
	char type[10];
	fscanf(fin,"%s %d",type,total);
	int * array = NULL;
	array = (int *)calloc(*total,sizeof(int));
	int i;
	for(i=0;i<*total;i++)
	{
		fscanf(fin,"%d",&array[i]);
		printf("Score for %s %d: %d",type,i+1,array[i]);
		printf("\n");
	}
	printf("\n");
	return array;
}


/*
fillArray Function
Fills array with exam scores from file
*/
void fillArray(int exams[2], FILE * fin)
{
	fscanf(fin,"%d",&exams[0]);
	fscanf(fin,"%d",&exams[1]);
	printf("Score for Exam 1: %d",exams[0]);
	printf("\n");
	printf("Score for Exam 2: %d",exams[1]);
	printf("\n\n");
}


/*
readFinalScore Function
Reads final score in from file
*/
int readFinalScore(FILE * fin)
{
	int final;
	fscanf(fin,"%d",&final);
	printf("Score for Final Exam: %d",final);
	printf("\n\n");
	return final;
}


/*
calculatePercentage Function
Calaculates grade percentages
*/
double calculatePercentage(int * assignments, int totalAquired, int worthEach, double scale)
{
	int total = 0;
	int i;
	for(i=0; i<totalAquired; i++)
	{
		total = total+assignments[i];
	}
	double totalWorth = totalAquired*worthEach;
	double percentTotal = total/totalWorth;
	double weightedTotal = percentTotal*scale;
	return weightedTotal;
	
}


/*
displayCategoryPercentage Function
Displayes grades
*/
void displayCategoryPercentage(double assignments, double labs, double quizzes, double exams, double final)
{
	assignments = assignments*100;
	labs = labs*100;
	quizzes = quizzes*100;
	exams = exams*100;
	final = final*100;
	
	printf("Percentage per category\n");
	printf("Assignments: %lf",assignments);
	printf("%%");
	printf("\n");
	printf("Labs: %lf",labs);
	printf("%%");
	printf("\n");
	printf("Quizzes: %lf",quizzes);
	printf("%%");
	printf("\n");
	printf("Exams: %lf",exams);
	printf("%%");
	printf("\n");
	printf("Final Exams: %lf",final);
	printf("%%");
	printf("\n\n");
}


/*
calcWeightedAvg Function
Calculates weighted grade average
*/
double calcWeightedAvg(double assignments, double labs, double quizzes, double exams, double final)
{
	assignments = assignments*100;
	labs = labs*100;
	quizzes = quizzes*100;
	exams = exams*100;
	final = final*100;
	int total = assignments+labs+quizzes+exams+final;
	return total;
}


/*
calcGPA Function
Calaculates GPA
*/
double calcGPA(double weightedAverage)
{
	if(weightedAverage >= 95)
	{
		return 4.0;
	}
	else if(weightedAverage < 95 && weightedAverage >= 65)
	{
		double diff = 95-weightedAverage;
		diff = diff*.1;
		return 4.0-diff;
	}
	else if(weightedAverage < 65 && weightedAverage >= 63)
	{
		return 0.9;
	}
	else if(weightedAverage < 63 && weightedAverage >= 61)
	{
		return 0.8;
	}
	else if(weightedAverage < 61 && weightedAverage >= 60)
	{
		return 0.7;
	}
	else
	{
		return 0.0;
	}
}


/*
displayGrade Function
Displays grade percentages
*/
void displayGrade(char * name, double weightedAverage, double gpa)
{
	int weightedAvg = weightedAverage;
	printf("%s\n",name);
	printf("Your weighted percentage is: %d%%\n",weightedAvg);
	printf("Your GPA is: %lf",gpa);
	printf("\n\n");
}


/*
clean Function
Cleans up array memory
*/
void clean(char * toClean)
{
	if(toClean != NULL)
	{
		free(toClean);
	}
}


/*
cleanUp Function
Cleans up array memory
*/
void cleanUp(int * toClean)
{
	if(toClean != NULL)
	{
		free(toClean);
	}
}


/*
goAgain Function
Promts user to go again or quit
*/
int goAgain()
{
	char answer[4];
	printf("Would You like to calculate another GPA(yes/no)?: ");
	scanf("%s",answer);
	if(strncmp("yes", answer, 4)==0)
	{
		return 1;
	}
	else
	{
		return 0;
	}

}
