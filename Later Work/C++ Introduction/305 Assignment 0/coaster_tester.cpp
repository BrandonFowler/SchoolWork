
//Author: Brandon Fowler
//Class: cscd305
//Assignment 0
//Total project time: 1.5 hours (Medium/slow paced work)
//Compilers used: cl and g++

#include "coaster.h"

int main(){

	int cars, cCapacity, people;

	int x = 1; //Switch for  while loop

	int result = 0;

	//Run until end input is given
	while (x){
		
		getInput(cars, cCapacity, people); //Gets input using int references as parameters

		if (cars == 0 && cCapacity == 0 && people == 0){ //Check for end condition
			x = 0;
		}
		else if (!checkInput(cars, cCapacity, people)){ //Check for valid input range
			
			cout << "Invalid input was entered and will be skipped" << endl;
		}
		else{ //Get result and print it
			result = runCoaster(cars, cCapacity, people);
			printResults(cars, cCapacity, people, result);
		}
	}
}