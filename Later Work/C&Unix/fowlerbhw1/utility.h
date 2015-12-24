#ifndef UTILITY_H
#define UTILITY_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "linkedList.h"

#define MAX 100

int menu();
FILE * openFile();
void strip(char *array);
int countRecords(FILE * fin, int lines);
void buildList(LinkedList * myList, FILE * fin, int total);

#endif // UTILITY_H
