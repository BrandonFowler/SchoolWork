#include "lab7.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
cscd240_s13_lab7Tester.c
Tester given for this homework
Modifier: Brandon Fowler
Class: CSCD240-02
*/

int main(int argc, char *argv[])
{
	int EXAMS_TOTAL = 2;
	FILE * fin = NULL;
	char * name = NULL;
	double weightedAvg, gpa;
	int assignmentsTotal, labsTotal, quizzesTotal;
	int * assignments = NULL, *labs = NULL, *quizzes = NULL, exams[EXAMS_TOTAL], finalExam;
	double assignmentsPercentage, labsPercentage, quizzesPercentage, examsPercentage, finalPercentage;

	if(argc == 2)
		fin = openFileNoPrompt(argv[1]);

	do
	{
		if(fin == NULL)
			fin = openFilePrompt();

		name = readName(fin);
	
		assignments = createAndFillArray(&assignmentsTotal, fin);
		labs = createAndFillArray(&labsTotal, fin);
		quizzes = createAndFillArray(&quizzesTotal, fin);
		fillArray(exams, fin);
		finalExam = readFinalScore(fin);

		fclose(fin);
		fin = NULL;

		assignmentsPercentage = calculatePercentage(assignments, assignmentsTotal, 100, .25);
		labsPercentage = calculatePercentage(labs, labsTotal, 50, .20);
		quizzesPercentage = calculatePercentage(quizzes, quizzesTotal, 25, .05);
		examsPercentage = calculatePercentage(exams, EXAMS_TOTAL, 100, .30);
		finalPercentage = calculatePercentage(&finalExam, 1, 200, .20);

		displayCategoryPercentage(assignmentsPercentage, labsPercentage, quizzesPercentage, examsPercentage, finalPercentage);

		weightedAvg = calcWeightedAvg(assignmentsPercentage, labsPercentage, quizzesPercentage, examsPercentage, finalPercentage);
		gpa = calcGPA(weightedAvg);
		displayGrade(name, weightedAvg, gpa);

		clean(name);
		cleanUp(assignments);
		cleanUp(labs);
		cleanUp(quizzes);
		assignments = labs = quizzes = NULL;		
		name = NULL;

	}while(goAgain());

	return 0;

}// end main

