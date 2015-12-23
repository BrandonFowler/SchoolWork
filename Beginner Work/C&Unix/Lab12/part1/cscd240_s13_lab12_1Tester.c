#include "lab12_1.h"

int main()
{
	char direction;
	int total, fdOut, shift;
	FILE * fin = openFilePrompt();

	fdOut = openOutputLow();

	total = countRecords(fin, 1);

	direction = readDirection(&shift);

	processFile(fin, fdOut, total, direction, shift);

	return 0;

}// end main
