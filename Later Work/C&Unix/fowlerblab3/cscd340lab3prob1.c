//CSCD340
//Lab 3
//Brandon Fowler

#include <signal.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char *argv[])
{
    
    	//Store sleep time and iterations
    	int sleepTime = atoi(argv[1]);
    	int iterations = atoi(argv[2]);
    
    	//Get name of program, without path
    	char * lPtr = strrchr(argv[0], '/');
    	int start = (lPtr - argv[0]) + 1;
    	char name[strlen(argv[0]) - (start) + 1];
    	int i = start;
    	int j = 0;
    	for(i; i < (strlen(argv[0]));i++){
		name[j] = argv[0][i];
		j++;
    	}
	name[j] = '\0';
	   
    	//Get pid
    	pid_t pid = getpid();

    	//Print information
    	int c;
    	for(c = 0; c < iterations; c++)
    	{
        	fprintf(stderr,"Executing %s, process id %d, iteration number %d\n", name, pid, c+1);
        	sleep(sleepTime);   
    	}
 
    	printf("%s is now exiting\n",name);
        
    	return 0;
}


