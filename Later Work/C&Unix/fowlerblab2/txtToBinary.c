//CSCD340
//Lab2
//Brandon Fowler

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

//Opens specified input file with fopen
FILE * openFile(){
	char name[100];
	printf("Enter the input file name: ");
	scanf("%s",name);
	printf("\n");
	FILE * fin = fopen(name,"r");
	if(fin == NULL)
	{
		fin = openFile();
	}
	return fin;
}

//Counts number of records in file
int recordCount(FILE * fin){
	int number = 0;
	int input;
	while(!feof(fin)){
		number++;
		fscanf(fin,"%d",&input);
	}
	rewind(fin);
	return number;
}

//Reads file and writes to bin(Low level open for output, low level write for output, high level read for input)
int main(){

	FILE * fin = openFile();

	int fd = open("myBinary.bin",O_CREAT|O_WRONLY,0777);

	if(fd < 0){
		printf("ERROR: CANNOT OPEN OUTPUT FILE!!!");
		exit(-1);
	}

	int input;
	int records = recordCount(fin);
	int result = 0;
	write(fd,&records,sizeof(records));//Write number of records from input file, as first line in output
	fscanf(fin,"%d",&input);
	while(!feof(fin)){
		if(result < 0){
			printf("ERROR: Problem Writing To File!!!");
			exit(-1);
		}
		write(fd,&input,sizeof(input));
		fscanf(fin,"%d",&input);
	}
	close(fd);
	fclose(fin);
	return 0;
}


