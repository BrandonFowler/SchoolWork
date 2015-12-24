
/*
Author: Brandon Fowler
Class: CSCD305
Assignment: 3
Quarter: Summer 2014
Compilers Used: g++ and cl
*/

#include "PackageException.h"

using namespace std;

//========================================================================================================
PackageException::PackageException(int trackingNumber, int weight, string problem, string type){
	this->trackingNumber = trackingNumber;
	this->weight = weight;
	this->problem = problem;
	this->type = type;
}

//========================================================================================================
string PackageException::description(){
	
	stringstream desc; 
	
	desc<<"Package type: "<<this->type<<"\n"
		<<"Tracking Number: "<<this->trackingNumber<<"\n"
		<<"Weight: "<<this->weight<<" pounds"<<"\n"
		<<"NOT LOADED: "<<this->problem<<endl;
		
	string description = desc.str();
	
	return description;
}

//Needed to stub out because declared pure virtual in base class
//========================================================================================================
double PackageException::getCost(){
	return 0;
}

//Needed to stub out because declared pure virtual in base class
//========================================================================================================
double PackageException::operator+(Package& rhs){
	return 0;
}