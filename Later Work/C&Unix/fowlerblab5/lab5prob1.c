//CSCD340
//Lab 5
//Brandon Fowler

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

void * print_hello(void * tid);
void * print_goodbye(void * tid);

int main(){
	pthread_t tid[6];
	long threadNum = 1;
	int status;

	int i = 0;
	for(i; i < 6; i++){
		printf("Creating thread %ld\n",threadNum);
		if(i%2 == 0){
			status = pthread_create(&tid[i],NULL,print_goodbye,(void*)threadNum);
		}
		else{
			status = pthread_create(&tid[i],NULL,print_hello,(void*)threadNum);
		}
		threadNum++;
	}

	int j = 0;
	for(j; j < 6; j++){
		pthread_join(tid[j],NULL);
	}	
	
	return 0;
}

void * print_hello(void * tid){
	printf("Hello thread number: %ld\n",(long)tid);
}

void * print_goodbye(void * tid){
	printf("Goodbye thread number: %ld\n",(long)tid);
}
