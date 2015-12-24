//Brandon Fowler
//CSCD340
//Homework 3

#pragma once

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "linkedList.h"

struct PCB
{
int processID;
long arrivalTimeStamp;
long totalBurstTime;
long executionStartTime;
long executionEndTime;
long remainingBurstTime;
long waitTime;
int processPriority;
int scheduled;
};
typedef struct PCB PCB;

void getNextProcess(FILE * fin, LinkedList * pList);
void runPriority(LinkedList * pList);
void runSJF(LinkedList * pList);
void resetList(LinkedList * pList);
int countLines(FILE * fin);
