#include "hw3.h"

int main()
{
    FILE * fin = NULL;
    int ** temps = NULL;
    int choice, total = 0;

    fin = openFilePrompt();

    total = countLines(fin);

    temps = fillArray(total, fin);

    do
    {
        choice = menu();

        if(choice == 1)
            displayMonthData(total, temps);

        else if(choice == 2)
            displayYearData(total, temps);

        else if(choice == 3)
            temps = addMoreTemps(temps, &total);

    }while (choice != 4);

    cleanUp(total, temps);

    return 0;
}// end main
