#include "inprompt.h"


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
