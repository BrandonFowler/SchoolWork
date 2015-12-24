
/*
Author: Brandon Fowler
Class: CSCD305
Assignment: 3
Quarter: Summer 2014
Compilers Used: g++ and cl
*/

#include "Package.h"

using namespace std;

//=======================================
string Package::getType(){
	return this->type;
}

//=======================================
long Package::getTrackingNumber(){
	return this->trackingNumber;
}

//=======================================
double Package::getWeight(){
	return this->weight;
}

//=======================================
double Package::getCostPU(){
	return this->costPU;
}

