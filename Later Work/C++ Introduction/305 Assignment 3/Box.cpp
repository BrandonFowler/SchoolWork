
/*
Author: Brandon Fowler
Class: CSCD305
Assignment: 3
Quarter: Summer 2014
Compilers Used: g++ and cl
*/

#include "Box.h"

using namespace std;

//=========================================================
Box::Box(int trackingNumber, int weight){
	this->trackingNumber = trackingNumber;
	this->weight = weight;
	this->type = "box";
	this->costPU = 2;
}

//=========================================================
string Box::description(){
	
	stringstream desc; 
	
	desc<<"Package type: "<<this->type<<"\n"
		<<"Tracking Number: "<<this->trackingNumber<<"\n"
		<<"Weight: "<<this->weight<<" pounds";
		
	string description = desc.str();
	
	return description;
}

//=========================================================
double Box::getCost(){
	return this->costPU*this->weight;
}

//=========================================================
double Box::operator+(Package& rhs){
	return this->weight+rhs.getWeight();
}