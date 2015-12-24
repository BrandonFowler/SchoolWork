#include "linkedList.h"
#include "utility.h"

int main()
{

    int total, choice;
    Node * nn = NULL;
	FILE * fin = NULL;
	LinkedList * myList = linkedList();

	fin = openFile();
	total = countRecords(fin, 1);
    buildList(myList, fin, total);

    do
    {
        choice = menu();

        switch(choice)
        {
            case 1: printList(myList);
                    break;

            case 2: nn = buildNode();
                    addFirst(myList, nn);
                    break;

            case 3: nn = buildNode();
                    addLast(myList, nn);
                    break;

            case 4: sort(myList);
                    break;

            case 5: nn = buildNode();
                    removeItem(myList, nn);
                    break;
        }// end switch

    }while(choice != 6);

    clearList(myList);

	return 0;

}// end main

