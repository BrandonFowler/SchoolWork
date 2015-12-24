
//Author: Brandon Fowler
//Class: cscd305
//Assignment 0
//Total project time: 1.5 hours (Medium/slow paced work)
//Compilers used: cl and g++

#include "coaster.h"

//Prompts for and stores three numbers pertaining to amount of cars, people, and each car capacity
void getInput(int &cars, int &cCapacity, int &people){
	cin >> cars >> cCapacity >> people;
}

//Calculates how many times the coaster must run based on input values
int runCoaster(int cars, int cCapacity, int people){
	int count = 0;
	while (people > 0){
		people = people - (cars*cCapacity);
		count++;
	}
	return count;
}

//Checks to make sure input is within valid ranges(does not handle non-integer bad input)
int checkInput(int cars, int cCapacity, int people){
	if (cars < 0 || cars > 100 || cCapacity < 0 || cCapacity > 11 || people < 0 || people > 1000){
		return 0;
	}
}

//Prints final results
void printResults(int cars, int cCapacity, int people, int result){
	cout << result << endl;
}