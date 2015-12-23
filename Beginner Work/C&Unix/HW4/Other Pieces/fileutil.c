
#include "fileutil.h"


FILE * openInputFile(char * fileName)
{
	FILE * fin = fopen(fileName,"r");
	if(fin == NULL)
	{
		fin = openFilePrompt();
	}
	return fin;
}


FILE * openFilePrompt()
{
	char fileName[100];
	printf("Please specify the name of the input file(example.txt): ");
	scanf("%s",fileName);
	FILE * fin = fopen(fileName,"r");
	if(fin == NULL)
	{
		fin = openFilePrompt();
	}
	return fin;	
}


int countRecords(FILE * fin, int linesPerRec)
{
	int count = 0;
	char temp[100];
	fgets(temp,100,fin);
	while(!feof(fin))
	{
		count++;
		fgets(temp,100,fin);
	}
	int countR = count/linesPerRec;
	return countR;
}


FILE * openOutputPrompt()
{
	char fileName[100];
	printf("Please specify output file name(example.txt):");
	scanf("%s",fileName);
	FILE * fout = fopen(fileName,"w");
	return fout;	
}
