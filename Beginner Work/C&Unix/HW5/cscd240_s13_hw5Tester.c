#include "hw5.h"

int main()
{
	int fdRead, total, choice;

	fdRead = openInputFile();

	buildList(fdRead, 3);

	do
	{
		choice = menu();

		if(choice == 1)
			printStockValue();

		else if(choice == 2)
			buyStock();

		else if(choice == 3)
			sellStock();

		else if(choice == 4)
			printStockInfo();

	}while(choice != 5);

	cleanUp();

	return 0;

}// end main

