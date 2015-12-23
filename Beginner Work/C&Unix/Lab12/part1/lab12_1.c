#include "lab12_1.h"


/*
lab12_1.c
Functions file for lab12 part 1
Author: Brandon Fowler
Class: cscd240-02
*/



//Opens/Creates an output file of user choice
int openOutputLow()
{
	char file[100];

	printf("Enter name of output file:");

	scanf("%s", file);
	
	int res = open(file, O_CREAT | O_WRONLY, 0775);

	return res;
}


//Asks user for R or L direction and insures valid input
//then returns usure input, also gets shift amount from user
char readDirection(int * shift)
{
	while(fgetc(stdin)!='\n');

	char temp;

	printf("Would you like to shift Left(L) or shift Right(R)?:");

	temp = getchar();
	
	while(temp != 'R' && temp != 'L')//check if valid
	{
		while(fgetc(stdin)!='\n');
		printf("Would you like to shift Left(L) or shift Right(R)?:");
		temp=getchar();
	}
	
	
	printf("How far would you like to shift?:");

	scanf("%d", shift);
	
	if(*shift > 25)
	{
		*shift = *shift %26;
	}
	return temp;
}


//Reads through file and writes ints and chars one at a time to output file
//Also shifts chars the desired amount before writing them.
void processFile(FILE * fin, int fout, int total, char direction, int shift)
{
	rewind(fin);//Make sure file is at the begining after counting lines
	int x;
	int y;
	char temp[100];//Holds each string brought in
	int length;//Length of string
	char ch;//Hold characters from string as string is broken down
	
	write(fout, &total, sizeof(total));//Write total lines
	write(fout, &direction, 1);//Write shift direction
	write(fout, &shift, sizeof(shift));//Write shift amount
	write(fout, " ", 1);//Space before words
	
	for(x = 0; x < total; x++)
	{
		fscanf(fin, "%s", temp);
		length = strlen(temp);
		write(fout,&length,sizeof(length));//Write length of string
		for(y = 0; y < length; y++)
		{	
			ch = temp[y];
			
			if(direction=='R')
			{
				
				if(ch > 64 && ch < 91)//Upper case
					if(ch+shift > 90)//Greater than Z
						ch = ch+shift-26;//Wrap back around to begining of alphabet with shift
					else 
						ch = ch+shift;
				else if(ch > 96 && ch < 123)//Lower case
					if(ch+shift > 122)//Greater than z
						ch = ch+shift-26;//Wrap back around to begining of alphabet with shift
				else 
					ch = ch+shift;
			}
			else
			{
				if(ch > 64 && ch < 91)
					if(ch-shift < 65)
						ch = ch-shift+26;
					else 
						ch = ch-shift;
				else if(ch > 96 && ch < 123)
					if(ch-shift < 97)
						ch = ch-shift+26;
				else 
					ch = ch-shift;
			}
			
			
			
			write(fout,&ch,1);//Write char to file
			
		
		}
		
	}
	close(fout);
	fclose(fin);
}


