//CSCD340
//Lab 5
//Brandon Fowler

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define MAX 100

pthread_mutex_t the_mutex;
pthread_cond_t condc, condp;
int buffer = 0;

void * producer(void * var)
{
	int x;
	for(x = 1; x <= MAX; x++)
	{
		pthread_mutex_lock(&the_mutex);
		while(buffer != 0){
			printf("Producer Sleeps While Waiting For Consumer\n");
			pthread_cond_wait(&condp, &the_mutex);
		}
		buffer = x;
		printf("Producer adding %d to buffer\n", buffer);
		pthread_cond_signal(&condc);
		pthread_mutex_unlock(&the_mutex);
	}
}

void * consumer(void * var)
{
	int x;
	int end = 0;
	while(buffer <= MAX)
	{
		if(buffer == MAX-1) end = 1;
		pthread_mutex_lock(&the_mutex);
		while(buffer == 0){
			printf("Buffer Empty. Consumer Sleeps\n");
			pthread_cond_wait(&condc, &the_mutex);
		}
		buffer--;
		printf("Consume %d\n", buffer);
		pthread_cond_signal(&condp);
		pthread_mutex_unlock(&the_mutex);
		if(end == 1 && buffer == 0) break;
	}
}


int main()
{
	pthread_t pro, con;
	pthread_mutex_init(&the_mutex, 0);
	pthread_cond_init(&condc, 0);
	pthread_cond_init(&condp,0);
	pthread_create(&pro, 0, producer, NULL);
	pthread_create(&con, 0, consumer, NULL);
	pthread_join(con, NULL);
	pthread_join(pro, NULL);
	pthread_cond_destroy(&condc);
	pthread_cond_destroy(&condp);
	pthread_mutex_destroy(&the_mutex);
	exit(0);
	
}
