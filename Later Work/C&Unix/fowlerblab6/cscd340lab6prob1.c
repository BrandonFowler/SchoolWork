//CSCD340
//Lab6
//Brandon Fowler

#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>

#define N 5

int state[N];
pthread_mutex_t mutex;
pthread_mutex_t s[N];

void eat(int i);
void think(int i);
void test(int i);
void * phil(void * i);
void putForks(int i);
void takeFork(int i);

int main(){
	pthread_mutex_init(&mutex,0);//Initialize mutex
	int x;
	for(x=0;x<N;x++){//Initialize mutexes in s
		pthread_mutex_init(&s[N],0);
	}
	pthread_t tid[N];
	int num[N];
	for(x=0;x < N;x++){//Create philosopher threads
		num[x] = x;
		pthread_create(&tid[x],0,phil,&(num[x]));
	}
	for(x=0;x < N;x++){//Join threads
		pthread_join(tid[x],NULL);
	}

	return 0;
}

void eat(int i){
	printf("Philospher %d is Eating\n",i);
}

void think(int i){
	printf("Philosopher %d is Thinking\n",i);
}

void test(int i){
	//Check neighboring philosophers state, and current philosophers state
	if(state[i] == 1 && state[(i+1-1)%N] != 2 && state[(i+1)%N] != 2){
		state[i] = 2;//Current philospher eats if allowed
		pthread_mutex_unlock(&s[i]);//Unlock fork
	}

}

void * phil(void * i){
	while(1){
		int j = *((int*)i);
		think(j);
		takeFork(j);
		eat(j);
		putForks(j);
	}	
}

void putForks(int i){
	pthread_mutex_lock(&mutex);//Lock out other philosophers
	state[i] = 0;//Set state thinking
	test((i+1-1)%N);//Test philosopher a neighbors state
	test((i+1)%N);//Test philosopher to right state(and right's neighbors)
	pthread_mutex_unlock(&mutex);//Unlock for other philosophers
}

void takeFork(int i){
	pthread_mutex_lock(&mutex);//Lock out other philosophers
	printf("Philosopher %d is Hungry\n",i);
	state[i] = 1;//Set state hungry
	test(i);//Test if can eat yet
	pthread_mutex_unlock(&mutex);//Unlock for other philosophers
	pthread_mutex_lock(&s[i]);//Lock fork
}

