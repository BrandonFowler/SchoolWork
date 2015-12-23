#include "lab12_2.h"


int main()
{
	char direction;
	int total, fdIn, shift;
	
	fdIn = openInputLow();

	total = readTotal(fdIn);

	direction = readDirection(fdIn, &shift);

	processFile(fdIn, total, direction, shift);

	return 0;

}// end main
