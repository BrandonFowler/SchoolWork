
/*
Author: Brandon Fowler
Class: CSCD305
Assignment: 3
Quarter: Summer 2014
Compilers Used: g++ and cl
*/

#pragma once

#include "Crate.h"

using namespace std;

class MetalCrate:public Crate{
	
	public:
		MetalCrate(int trackingNumber, int weight);
		virtual double getCost();
		virtual double operator+(Package& rhs);
		virtual string description();
		virtual string getMaterial();//To fulfil abstract function from Crate
};