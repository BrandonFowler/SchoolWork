
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

class Crate:public Package{
	
	public:
		/*
			Needed this class to be abstract for project specifications,
			and could not think of anything useful for it to contain. So,
			I gave it a pure virtual method to return material type, even though
			it has no real use.
		*/
		virtual string getMaterial()=0;
		
		/*
			Needed something to put in the Crate.cpp file so gave it a convert
			pounds to kilos method, even though it has no real use.
		*/
		virtual double kiloWeight();
};