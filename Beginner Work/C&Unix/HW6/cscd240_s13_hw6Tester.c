#include "hw6.h"

int main(int argc, char *argv[])
{
	int choice;
	Node * lOne = NULL, *lTwo = NULL;

	lOne = readNumber();
	printNumber("One: ", lOne);

	lTwo = readNumber();
	printNumber("Two: ", lTwo);

	do
	{
		choice = menu();

		switch(choice)
		{
			case 1:	printNumber("One: ", lOne);
				printNumber("Two: ", lTwo);
				break;

			case 2:	clearList(lOne);	
				lOne = readNumber();
				printNumber("One: ", lOne);
				break;

			case 3:	clearList(lTwo);
				lTwo = readNumber();
				printNumber("Two: ", lTwo);
				break;

			case 4:	executeChoice(lTwo, lOne, &plus);
				break;

			case 5:	executeChoice(lTwo, lOne, &minus);
				break;

		}// end switch

	}while(choice != 6);	

	clearList(lOne);
	clearList(lTwo);

	return 0;
}// end main

