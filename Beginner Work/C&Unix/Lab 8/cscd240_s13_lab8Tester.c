#include "lab8.h"

int main()
{
    FILE * fin = NULL;
    int total, choice;
    Person * array = NULL;

    fin = openFilePrompt();

    total = countLines(fin);

    array = fillArray(total, fin);

    fclose(fin);
    fin = NULL;

    do
    {
        choice = menu();

        if(choice == 1)
            printLastNameSortedArray(array, total);

        else if(choice == 2)
            printFirstNameSortedArray(array, total);

        else if(choice == 3)
            printIDSortedArray(total, array);

    }while(choice != 4);

	free(array);
	array = NULL;

    return 0;
}// end main
