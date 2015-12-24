//Brandon Fowler
//CSCD340
//Homework 4

#include <stdio.h>
#include <stdlib.h>
#include "linkedList.h"

struct MemoryBlock
{
int startAddress;
int endAddress;
int segmentSize;
int processId;
};
typedef struct MemoryBlock MB;

void addProcess(LinkedList * list, MB * newMB);
LinkedList * duplicateLinkedList(LinkedList * list);
int countLines(FILE * fin);
void constructMemoryMap(int lines, LinkedList * firstFit, FILE * fin);
void newProc(LinkedList * firstFit, LinkedList * bestFit, LinkedList * worstFit);
void terminateProcess(LinkedList * firstFit, LinkedList * bestFit, LinkedList * worstFit);
void printListMap(LinkedList * firstFit, LinkedList * bestFit, LinkedList * worstFit);
void printStats(LinkedList * firstFit, LinkedList * bestFit, LinkedList * worstFit);
void firstFitPlacer(int pid,int size, LinkedList * firstFit);
void bestFitPlacer(int pid,int size, LinkedList * bestFit);
void worstFitPlacer(int pid,int size, LinkedList * worstFit);
void terminateSpecified(int pid, LinkedList * firstFit,char type[]);
void printFragmentDetails(LinkedList * list, char type[]);
