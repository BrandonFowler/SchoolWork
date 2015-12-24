//CSCD340
//Lab2
//Brandon Fowler

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

//Reads from binary file with low level read and prints to the sceen with fprintf
void convertToText(int fd){
	
	int input;
	int records;
	int result;
	result = read(fd,&records,sizeof(records));
	while(records != 1){
		if(result < 0){
			printf("Error: Can't read input from myBinary.bin");
			exit(-1);
		}
		result = read(fd,&input,sizeof(int));
		fprintf(stdout,"%d\n",input);
		records--;
	}
}

//Reads from binary file with low level read and prints to the screen with low level write
void convert(int fd){
	lseek(fd,0,SEEK_SET);//Rewind file
	int input;
	int records;
	int result;
	result = read(fd,&records,sizeof(records));//Read number of records from first line in file
	while(records != 1){
		if(result < 0){
			printf("Error: Can't read input from myBinary.bin");
			exit(-1);
		}
		result = read(fd,&input,sizeof(int));

		//Find amount of didgits in input, adjusting for minus sign
		int digits;
		if(input > 0){
			digits = 1;
		}
		else{
			digits = 2;
		}
		int tempInt = input;
		do {
     			++digits; 
     			tempInt = tempInt/10;
		} while (tempInt);

		//Convert integer to char array
		char output[digits];
		snprintf(output, digits,"%d",input);
		
		//Write to screen
		write(1,output,sizeof(output));
		write(1,"\n",sizeof(char));

		records--;
	}
}

//Opens .bin with low level open then calls convert functions
int main(){
	
	int fd = open("myBinary.bin",O_RDONLY);

	if(fd == 2){
		printf("Error: Cannot open myBinary.bin!");
		exit(-1);
	}

	convertToText(fd);
	convert(fd);
	close(fd);
	return 0;
}
