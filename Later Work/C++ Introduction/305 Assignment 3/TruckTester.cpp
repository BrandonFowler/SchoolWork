
/*
Author: Brandon Fowler
Class: CSCD305
Assignment: 3
Quarter: Summer 2014
Compilers Used: g++ and cl
*/

#include<cstdlib>
#include "Truck.h"

using namespace std;

int main(){

	Truck myTruck;
	myTruck.load();
	myTruck.drive();
	myTruck.unload();
	
	return EXIT_SUCCESS;
}