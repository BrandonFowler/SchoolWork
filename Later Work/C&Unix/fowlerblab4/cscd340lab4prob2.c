//CSCD340
//Lab4
//Brandon Fowler

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main(){
	int fd[2];
	if(pipe(fd) < 0){
		exit(-1);
	}
	if(fork() == 0){
		close(fd[0]);
		close(1);
		dup(fd[1]);
		close(fd[1]);
		execlp("ls","ls","-l",NULL);
	}
	else{
		close(fd[1]);
		close(0);
		dup(fd[0]);
		close(fd[0]);
		execlp("wc","wc","-w",NULL);	
	}
}
