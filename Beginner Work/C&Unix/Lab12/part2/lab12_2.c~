#include "lab12_2.h"


/*
lab12_2.c
Functions file for lab12 part 2
Author: Brandon Fowler
Class:cscd240-02
*/


//Opens input file with low level commands
int openInputLow()
{
	char file[100];
	printf("Enter input filename:");
	scanf("%s", file);
	int res = open(file, O_RDONLY,0775);
	return res;
}


//Reads total lines from file(low level)
int readTotal(int fin)
{
	int res = -1;
	int temp = read(fin, &res, sizeof(res));
	return res;
	
}


//Reads shift direction from file(low level)
char readDirection(int fin, int *shift)
{
	char res;
	read(fin, &res, sizeof(res));
	read(fin, shift, sizeof(shift));
	return res;
}


//Reads file with low level commands, decrypts characters, and prints to screen
void processFile(int fin, int total, char direction, int shift)
{
	int x;
	int y;
	char ch;
	char temp;
	int length;
	
	
	read(fin, &temp, 1);

	for(x = 0; x < total; x++)
	{
		read(fin, &length, 4);
		for(y = 0; y < length; y++)
		{
			read(fin, &ch, 1);
			if(direction=='L')//Shift back right
			{
				
				if(ch > 64 && ch < 91)//Upper case
					if(ch+shift > 90)//Greater than Z
						ch = ch+shift-26;//Wrap back around
					else 
						ch = ch+shift;
				else if(ch > 96 && ch < 123)//Lower case
					if(ch+shift > 122)//Greater than z
						ch = ch+shift-26;//Wrap back around
				else 
					ch = ch+shift;
			}
			else//Shift back left
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
			printf("%c", ch);
		}
		printf("\n");
	}
	close(fin);
	
}


