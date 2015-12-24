
/*
Author: Brandon Fowler
Class: CSCD305
Assignment: 3
Quarter: Summer 2014
Compilers Used: g++ and cl
*/

#include "Letter.h"

using namespace std;

//==================================================================
Letter::Letter(int trackingNumber, int weight){
	this->trackingNumber = trackingNumber;
	this->weight = weight;
	this->type = "letter";
	this->costPU = 0.05;
}

//===================================================================
string Letter::description(){
	
	stringstream desc; 
	
	desc<<"Package type: "<<this->type<<"\n"
		<<"Tracking Number: "<<this->trackingNumber<<"\n"
		<<"Weight: "<<this->weight<<" ounces";
		
	string description = desc.str();
	
	return description;
}

//===================================================================
double Letter::getCost(){
	return this->costPU*this->weight;
}

//Converts from ounces to pounds
//===================================================================
double Letter::getWeight(){
	return (this->weight*0.0625);
}

//Converts from ounces to pounds
//===================================================================
double Letter::operator+(Package& rhs){
	return (this->weight*0.0625)+rhs.getWeight();
}