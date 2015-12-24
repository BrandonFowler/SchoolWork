//CSCD340
//Homework 2
//Brandon Fowler

#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <dirent.h> 
#include "linkedList.h"

struct alias{
	char * cloak;
	char * original;
};
typedef struct alias Alias;

struct history{
	int tracker;
	char * command;	
};
typedef struct history History;

void interpreter(LinkedList *linkedAlias, LinkedList *linkedHistory, int* histTracker , char *s);
void loadMyshrc(LinkedList *linkedAlias, LinkedList *linkedHistory, int* histTracker);
void readFile(char *fileName, LinkedList *linkedAlias, LinkedList *linkedHistory, int* histTracker);
int lineCount(char *filename);
void getLines(int lines, char *fileName, LinkedList *linkedAlias, LinkedList *linkedHistory, int* histTracker);
int checkExtention(char *fileName);
void loadHistory(LinkedList * histList, int *count);
void outputHistory(LinkedList *histList);
int makeargs(char *s, char *** argv,LinkedList * linkedAlias);
void createAlias(char * s,LinkedList * linkedAlias);
void strip(char *s);
void pipeIt(char ** prePipe, char ** postPipe, int out, char * mS);
void forkIt(int retVal, char ** argv,char* s);
char * getHistoryCommand(char * s, LinkedList * linkedHistory);
void printHistory(LinkedList * theList);
void changePath(char * s);
void redirection(LinkedList * linkedAlias,char * s);
Node * checkAlias(char * item, LinkedList * linkedAlias);
void deleteAlias(LinkedList * theList, char * item);
void newHistory(char * s,LinkedList * linkedHistory,int * histTracker);
void clean(int argc, char **argv);
void clearlinkedAlias(LinkedList * linkedAlias);
void clearHistory(LinkedList * list);
void print(void * s);
