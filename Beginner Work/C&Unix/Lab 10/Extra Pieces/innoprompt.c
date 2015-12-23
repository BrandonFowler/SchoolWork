#include "innoprompt.h"
#include "inprompt.h"


FILE * openInputFile(char * fileName)
{
	FILE * fin = fopen(fileName,"r");
	if(fin == NULL)
	{
		fin = openFilePrompt();
	}
	return fin;
}
