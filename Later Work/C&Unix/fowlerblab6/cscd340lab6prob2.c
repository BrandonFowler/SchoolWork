//CSCD340
//Lab6
//Brandon Fowler

#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>

#define Readers 5
#define Writers 3

pthread_mutex_t mutex;
pthread_mutex_t db;
pthread_mutex_t writerLock;
pthread_cond_t conditionRead;
pthread_cond_t conditionWrite;
int rc = 0;
int writerState = 0;
int waitLimit = 3;

void writeData(int v);
void readDB(int v);
void * writer(void * v);
void * reader(void * v);

int main(){
	//Initialize all needed mutexes
	pthread_mutex_init(&mutex,0);
	pthread_mutex_init(&db,0);
	pthread_mutex_init(&writerLock,0);
	pthread_cond_init(&conditionRead,0);
	pthread_cond_init(&conditionWrite,0);
	
	//Make arrays for tid's and thread numbers
	pthread_t readtid[Readers];
	pthread_t writetid[Writers];
	int readNum[Readers];
	int writeNum[Writers];
	
	int x;
	for(x=0;x < Readers;x++){//Create readers and pass reader numbers
		readNum[x] = x;
		pthread_create(&readtid[x],0,reader,&(readNum[x]));
	}
	for(x=0;x < Writers;x++){//Create writers and pass writer numbers
		writeNum[x] = x;
		pthread_create(&writetid[x],0,writer,&(writeNum[x]));
	}
	for(x=0;x < Readers;x++){//Join readers
		pthread_join(readtid[x],NULL);
	}
	for(x=0;x < Writers;x++){//Join writers
		pthread_join(writetid[x],NULL);
	}
	
	return 0;
}

void * reader(void * v){
	int j = *((int *)v);
	while(1){
		pthread_mutex_lock(&mutex);//Lock out other readers
		rc++;
		if(rc == 1){
			pthread_mutex_lock(&db);//Lock database
		}
		pthread_mutex_unlock(&mutex);//Unlock for other readers
		readDB(j);
		pthread_mutex_lock(&mutex);//Lock out other readers
		if(writerState == 1 && rc%waitLimit == 0){//If writer ready and readers have had time
			pthread_cond_signal(&conditionWrite);//Signal writer
			pthread_cond_wait(&conditionRead,&db);//Sleep reader/toggle db mutex
			//Other readers should wait for this reader to leave critical region
		}
		pthread_mutex_unlock(&mutex);//Unlock for other readers
	}
}

void * writer(void * v){
	int j = *((int *)v);
	while(1){
		pthread_mutex_lock(&writerLock);//Lock other writers out
		writerState = 1;//Set writer state ready
		pthread_cond_wait(&conditionWrite,&writerLock);//Wait for readers
		pthread_mutex_lock(&db);//Lock database
		printf("Writer %d locking db\n",j);
		writeData(j);
		printf("Writer %d unlocking db\n",j);
		pthread_mutex_unlock(&db);//Unlock database
		writerState = 0;//Set writer state not-ready
		pthread_cond_signal(&conditionRead);//Signal reader
		pthread_mutex_unlock(&writerLock);//Unlock for other writers
	}
}

void readDB(int v){
	printf("Reader %d reading\n",v);
}

void writeData(int v){
	printf("Writer %d writing\n",v);
}
