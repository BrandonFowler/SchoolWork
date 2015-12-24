//CSCD340
//Lab4
//Brandon Fowler

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#define MAX 100

void pipeIt(char ** prePipe, char ** postPipe);
void strip(char *s);
int makeargs(char *s, char *** argv);
void clean(int argc, char **argv);
void forkIt(char ** argv);

int main()
{
	int res;
	char s[MAX];
	char ** prePipe = NULL, ** postPipe = NULL;
	char ** singleCommand = NULL;
	printf("Please enter a command (exit to exit) ");
	fgets(s, MAX, stdin);
	strip(s);

	while(strcmp(s, "exit") != 0)
	{
		char * pipePos;
		pipePos = strrchr(s, '|');
		if(pipePos != NULL){
			int pos = pipePos - s;
			char * preHalf = (char*)calloc(pos+1,sizeof(char));
			char * postHalf = (char*)calloc(strlen(s)-pos+1,sizeof(char));
			int i = 0;
			for(i;i<pos;i++){
				preHalf[i] = s[i];
			}
			preHalf[i] = '\0';
			i++;
			for(i;i<strlen(s);i++){
				postHalf[i-pos-1] = s[i];
			}
			postHalf[strlen(postHalf)] = '\0';

			int preToks = makeargs(preHalf, &prePipe);
			int postToks = makeargs(postHalf, &postPipe);

			free(preHalf);
			free(postHalf);

			pipeIt(prePipe, postPipe);
		
			clean(preToks,prePipe);
			clean(postToks,postPipe);
		}
		else{
			int toks = makeargs(s, &singleCommand);
			forkIt(singleCommand);
  			clean(toks, singleCommand);
			singleCommand = NULL;
		}
		printf("Please enter a command (exit to exit) ");
		fgets(s, MAX, stdin);
		strip(s);
	}
}// end main

void strip(char *s)
{
	if(strlen(s) > 1){
  		int len;
  		len = strlen(s);
  		if(s[len - 2] == '\r')
    		s[len -2] = '\0';

  		else if(s[len - 1] == '\n')
    		s[len -1] = '\0';
	}
}// end strip

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

void clean(int argc, char **argv)
{
	int i = 0;
	while(argv[i] != NULL){
		free(argv[i]);
		i++;
	}
	free(argv);
}// end clean

void pipeIt(char ** prePipe, char ** postPipe)
{
	if(fork()==0){
		int fd[2];
		if(pipe(fd) < 0){
			exit(-1);
		}
		if(fork() == 0){
			close(fd[0]);
			close(1);
			dup(fd[1]);
			close(fd[1]);
			int res = execvp(prePipe[0],prePipe);	
			if(res < 0){
				exit(127);//Invalid command exit code
			}
		}
		else{
			int status;
			waitpid(-1,&status,NULL);
			if(WEXITSTATUS(status) == 127){
				printf("Invalid Pre Pipe Command\n");
				exit(-1);
			}
			else{
				close(fd[1]);
				close(0);
				dup(fd[0]);
				close(fd[0]);
				int res = execvp(postPipe[0],postPipe);	
				if(res < 0){
					printf("Invalid Post Pipe Command\n");
					exit(-1);
				}	
			}
		}
	}
	else{
		int status;
		waitpid(-1,&status,NULL);
	}
}// end pipeIt

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
