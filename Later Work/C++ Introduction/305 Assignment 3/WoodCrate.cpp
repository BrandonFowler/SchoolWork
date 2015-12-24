
/*
Author: Brandon Fowler
Class: CSCD305
Assignment: 3
Quarter: Summer 2014
Compilers Used: g++ and cl
*/

#include "WoodCrate.h"

using namespace std;

//=========================================================
WoodCrate::WoodCrate(int trackingNumber, int weight){
	this->trackingNumber = trackingNumber;
	this->weight = weight;
	this->type = "wood crate";
	this->costPU = 2.5;
}

//=========================================================
string WoodCrate::description(){
	
	stringstream desc; 
	
	desc<<"Package type: "<<this->type<<"\n"
		<<"Tracking Number: "<<this->trackingNumber<<"\n"
		<<"Weight: "<<this->weight<<" pounds";
		
	string description = desc.str();
	
	return description;
}

//=========================================================
double WoodCrate::getCost(){
	return this->costPU*this->weight;
}

//=========================================================
double WoodCrate::operator+(Package& rhs){
	return this->weight+rhs.getWeight();
}

//=========================================================
string WoodCrate::getMaterial(){
	return "wood";
}