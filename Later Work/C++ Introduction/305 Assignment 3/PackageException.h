
/*
Author: Brandon Fowler
Class: CSCD305
Assignment: 3
Quarter: Summer 2014
Compilers Used: g++ and cl
*/

#pragma once

#include "Package.h"

using namespace std;

class PackageException:public Package{
	
	protected:
		string problem;
	public:
		PackageException(int trackingNumber, int weight, string problem, string type);
		virtual string description(); //Return package description, and why it is invalid for shipping
		virtual double getCost();//Needed to stub out because declared pure virtual in base class
		virtual double operator+(Package& rhs);//Needed to stub out because declared pure virtual in base class
};