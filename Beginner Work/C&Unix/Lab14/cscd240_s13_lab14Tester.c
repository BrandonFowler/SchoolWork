#include "lab14.h"

#define MAX 25
#define MAXSTRING 650

int main()
{
    int choice;
    char theString[MAXSTRING];

    readString(theString);

    do
    {
        choice = menu();

        switch(choice)
        {

            case 1: readString(theString);
                    break;

            case 2: parseString(theString);

        }// end switch

     }while(choice != 3);

    return 0;

}// end main

