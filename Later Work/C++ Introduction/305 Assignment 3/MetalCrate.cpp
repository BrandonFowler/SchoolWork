
/*
Author: Brandon Fowler
Class: CSCD305
Assignment: 3
Quarter: Summer 2014
Compilers Used: g++ and cl
*/

#include "MetalCrate.h"

using namespace std;

//==============================================================
MetalCrate::MetalCrate(int trackingNumber, int weight){
	this->trackingNumber = trackingNumber;
	this->weight = weight;
	this->type = "metal crate";
	this->costPU = 3;
}

//==============================================================
string MetalCrate::description(){
	
	stringstream desc; 
	
	desc<<"Package type: "<<this->type<<"\n"
		<<"Tracking Number: "<<this->trackingNumber<<"\n"
		<<"Weight: "<<this->weight<<" pounds";
		
	string description = desc.str();
	
	return description;
}

//==============================================================
double MetalCrate::getCost(){
	return this->costPU*this->weight;
}

//==============================================================
double MetalCrate::operator+(Package& rhs){
	return this->weight+rhs.getWeight();
}

//==============================================================
string MetalCrate::getMaterial(){
	return "metal";
}