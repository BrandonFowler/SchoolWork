#include "openOutputPrompt.h"



FILE * openOutputPrompt()
{
	char fileName[100];
	printf("Please specify output file name(example.txt):");
	scanf("%s",fileName);
	FILE * fin = fopen(fileName,"w");
	return fin;	
}
