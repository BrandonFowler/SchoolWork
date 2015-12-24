//CSCD340
//Lab 5
//Brandon Fowler

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int dex = 0;
int value = 100;
int numberOfElements;
int * array;

void * fillArray(void* threadNumber){
	while(dex < numberOfElements){
		array[dex] = value;
		printf("Thread %ld filling array element %d with %d\n",(long)threadNumber,dex,value);
		value++;
		dex++;
	}
}

int main(int argc,char * argv[]){
	if(argc < 3){
		printf("Not enough arguments!\n");
		exit(-1);
	}

	int status;
	numberOfElements = atoi(argv[1]);
	int numberOfThreads = atoi(argv[2]);
	pthread_t tid[numberOfThreads];
	array = (int*)calloc(numberOfElements,sizeof(int));

	int i = 0;
	long threadNumber = 0;
	for(i; i < numberOfThreads;i++){
		status = pthread_create(&tid[i],NULL,fillArray,(void*)threadNumber);
		threadNumber++;
	}

	for(i=0;i < numberOfThreads; i++){
		pthread_join(tid[i],NULL);
	}

	for(i=0;i < numberOfElements; i++){
		printf("At index %d: %d\n",i,array[i]);
	}

	free(array);
	return 0;
}
