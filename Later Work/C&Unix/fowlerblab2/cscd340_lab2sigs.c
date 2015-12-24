//CSCD340
//Lab2
//Brandon Fowler

#include <signal.h>
#include <stdlib.h>
#include <stdio.h>

void f(int signum)
{
	printf("Sorry can’t CTRL-C");
}

int main()
{
	int x;
	signal(SIGINT,f);
	for(x = 0; x < 15; x++)
	{
		printf("val is: %d\n", x);
		sleep(1);
	}
	return 0;
}
