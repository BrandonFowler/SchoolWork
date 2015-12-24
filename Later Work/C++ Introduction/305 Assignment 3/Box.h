
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

class Box:public Package{

	public:
		Box(int trackingNumber, int weight);
		virtual string description();
		virtual double getCost();
		virtual double operator+(Package& rhs);
};