//CSCD340
//Lab 3
//Brandon Fowler

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define MAX 100

void strip(char *s);
void clean(int argc, char **argv);
void printargs(int argc, char **argv);
int makeargs(char *s, char *** argv);
void forkIt(char ** argv);


int main()
{
	char **argv = NULL, s[MAX];
	int argc;

	printf("Please enter a command (exit to exit) ");
	fgets(s, MAX, stdin);
	strip(s);

 	while(strcmp(s, "exit") != 0)
  	{
		argc = makeargs(s, &argv);
  		if(argc != -1)
  		{
    			printf("There are %d tokens.\nThe tokens are:\n", argc);
    			printargs(argc, argv);
			forkIt(argv);
  			clean(argc, argv);
		}// end if

		
  		argv = NULL;

		printf("Please enter a command (exit to exit) ");
		fgets(s, MAX, stdin);
		strip(s);

	}// end while

}// end main

void strip(char *s)
{
  	int len;
  	len = strlen(s);
  	if(s[len - 2] == '\r')
    	s[len -2] = '\0';

  	else if(s[len - 1] == '\n')
    	s[len -1] = '\0';
}// end strip

void clean(int argc, char **argv)
{
	int i = 0;
	while(argv[i] != NULL){
		free(argv[i]);
		i++;
	}
	free(argv);
}// end clean

void printargs(int argc, char **argv)
{
	int x;
	for(x = 0; x < argc; x++)
		printf("%s\n", argv[x]);

}// end printargs

int makeargs(char *s, char *** argv)
{

	if(strlen(s)%16 == 0){
		strcat(s," ");
	}
	int tokenNumber = 0;
	char tempS[strlen(s)];
	strcpy(tempS, s);
	char delimiter[] = " ";
	char * curToken="";
	char * savePtr;

	//Find number of tokens
	for (strtok_r(tempS, delimiter,&savePtr); curToken; curToken = strtok_r(NULL, delimiter,&savePtr))
	{
		tokenNumber++;	
	}
	
	//Allocate pointers for amount of tokens + 1
	(*argv)=(char**)calloc(tokenNumber+1, sizeof(char*));
	
	//Allocate space for indivudual tokens and copy them
	int i;
	int length = 0;
	curToken = strtok_r(s, delimiter, &savePtr);
	for (i=0; i < tokenNumber; i++)
	{
		length = strlen(curToken);
		(*argv)[i] = (char*)calloc(length, sizeof(char*));
		strcpy((*argv)[i], curToken);
		
		curToken = strtok_r(NULL, delimiter, &savePtr);
	}

	//Return number of tokens or failure
	if (tokenNumber > 0){
	 	return tokenNumber;
	}
	else{
		return -1;
	}
}// end makeArgs

void forkIt(char ** argv)
{
	pid_t id = fork();

	if(id != 0){
		int status;
		waitpid(-1,&status,NULL);
	}
	else{
		int res = execvp(argv[0],argv);	
		if(res < 0){
			printf("Invalid Command\n");
			exit(-1);
		}
	}

}// end forkIt

