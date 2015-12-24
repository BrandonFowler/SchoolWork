//CSCD340
//Lab 5
//Brandon Fowler

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define MAX 100

pthread_mutex_t the_mutex;
pthread_cond_t condc, condp;
int i = 0;
int buffer[MAX];

void * producer(void * var)
{
	while(1)
	{
		pthread_mutex_lock(&the_mutex);
		while(i == MAX){
			printf("Producer Sleeps While Waiting For Consumer\n");
			pthread_cond_wait(&condp, &the_mutex);
		}
		buffer[i] = i;
		i++;
		printf("Producer adding %d to buffer\n", i);
		pthread_cond_signal(&condc);
		pthread_mutex_unlock(&the_mutex);
	}
}

void * consumer(void * var)
{
	while(1)
	{
		pthread_mutex_lock(&the_mutex);
		while(i == 0){
			printf("Buffer Empty. Consumer Sleeps\n");
			pthread_cond_wait(&condc, &the_mutex);
		}
		buffer[i] = 0;
		printf("Consume %d\n", i);
		i--;
		pthread_cond_signal(&condp);
		pthread_mutex_unlock(&the_mutex);
	}
}


int main()
{
	pthread_t pro, con;
	pthread_mutex_init(&the_mutex, 0);
	pthread_cond_init(&condc, 0);
	pthread_cond_init(&condp,0);
	pthread_create(&pro, 0, producer, NULL);
	pthread_create(&pro, 0, producer, NULL);
	pthread_create(&pro, 0, producer, NULL);
	pthread_create(&pro, 0, producer, NULL);
	pthread_create(&con, 0, consumer, NULL);
	pthread_join(con, NULL);
	pthread_join(pro, NULL);
	pthread_cond_destroy(&condc);
	pthread_cond_destroy(&condp);
	pthread_mutex_destroy(&the_mutex);
	exit(0);
	
}
