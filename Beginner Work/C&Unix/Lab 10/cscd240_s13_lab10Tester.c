#include "lab10.h"

#define MAX 100

int main(int argc, char * argv[])
{
	int x, count;
	char temp[MAX];
	FILE *fin = NULL, *fout = NULL;

	if(argc > 1)
		fin = openInputFile(argv[1]);

	else
		fin = openFilePrompt();

	fgets(temp, MAX, fin);
	while(!feof(fin))
	{
		printf("%s", temp);
		fgets(temp, MAX, fin);

	}// end while

	fclose(fin);


	// PART 2 of Lab 10 the code below

	fout = openOutputPrompt();

	fin = openFilePrompt();

	count = countRecords(fin, 1);

	rewind(fin);

	fprintf(fout, "count is: %d\n", count);
	fprintf(fout, "\n\n");

	fgets(temp, MAX, fin);
	while(!feof(fin))
	{
		fprintf(fout, "%s", temp);
		fgets(temp, MAX, fin);

	}// end while

	fclose(fout);
	fclose(fin);

	return 0;

}// end main

