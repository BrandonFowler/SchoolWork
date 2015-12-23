#include <stdio.h>
#include <stdlib.h>


/*
cscd240_s13_lab15Tester.c
Main and functions for lab 15
Author: Brandon Fowler
Class:cscd240-02
*/


//Checks the number or letter passed and returns a score
int binaryConvert(char bin)
{
	
	if(bin == '1' ||bin == '2'|| bin == '4' || bin == '8')
	{
		return 500;
	}
	else if(bin == '3' ||bin == '5' ||bin == '6' ||bin == '9' ||bin == 'A' || bin == 'C' )
	{
		return 1000;
	}
	else if(bin == '7' || bin == 'B' || bin == 'D' || bin == 'E')
	{
		return 1500;
	}
	else
	{
		return 0;
	}
	
}


//Counts how many balls are played in input file
int ballCounter(FILE * fin)
{
	int count = 0;
	char temp;
	fscanf(fin,"%c",&temp);
	while(!feof(fin))
	{
		fscanf(fin,"%c",&temp);
		if(temp == '#')
		{
			count++;
		}	
	}
	rewind(fin);
	count = count-2;
	return count;
}


//Runs main program, reads file, does bitwise operations, prints scores
int main()
{
	
	char Rbin=0;//Stores value from bitwise operation
	char Lbin=0;//Stores value from bitwise operation
	int Rbank=0;//Stores score
	int Lbank=0;//Stores score
	int RTbank=0;//Stores temporary score if over 2000
	int LTbank=0;//Stores temporary score if over 2000
	char Rdata;//Stores input from file
	char Ldata;//Stores input from file
	char temp;//Stores useless info in file, and # symbols
	int count;//Stores how many balls played in file
	int total;//stores total scores
	int x;//Used in for loop
	FILE * fin = fopen("lab15.txt","r");
	count = ballCounter(fin);

	for(x=0;x < count;x++)
	{
		fscanf(fin,"%c",&temp);
		if(temp == '\n')
		{
			fscanf(fin,"%c",&temp);	
		}
		do
		{
			fscanf(fin,"%c",&temp);

			fscanf(fin,"%c",&Ldata);
			fscanf(fin,"%c",&Rdata);
			
			Rbin = Rdata | Rbin;//Bitwise operation
			Lbin = Ldata | Lbin;//Bitwise operation
			
			Rbank = binaryConvert(Rbin);
			Lbank = binaryConvert(Lbin);

			if(Rdata == 'F')
			{
				Rbin = 0;
				RTbank = RTbank+2000;
			}
			if(Ldata == 'F')
			{
				Lbin = 0;
				LTbank = LTbank+2000;
			}


			fscanf(fin,"%c",&temp);
			if(temp == '\n')
			{
				fscanf(fin,"%c",&temp);	
			}
		}while(temp != '#');
		Rbank = Rbank + RTbank;
		Lbank = Lbank + LTbank;
		total = Rbank + Lbank;

		printf("R %d L %d Total %d\n",Rbank,Lbank,total);

		Rbank = 0;
		Lbank = 0;
		RTbank = 0;
		LTbank = 0;
		Rbin = 0;
		Lbin = 0;
		total = 0;
	}
	fclose(fin);
	return 0;
}




