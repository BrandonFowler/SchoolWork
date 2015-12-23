#include "hw4.h"

int main()
{
    int choice;
    FILE * fin = openFilePrompt();

    createList(fin);
  
    do
    {
            choice = menu();

            if(choice == 1)
                writeMonthData();

            else if(choice == 2)
                writeYearData();

            else if(choice == 3)
                writeTheList();

            else if(choice == 4)
                addMoreData();

    }while(choice != 5);

    clearList();

    return 0;
}// end main
