
/*
Author: Brandon Fowler
Class: CSCD305
Assignment: 3
Quarter: Summer 2014
Compilers Used: g++ and cl
*/

#pragma once

#include<string>
#include<sstream>

using namespace std;

class Package{
	
	protected:
		string type; //Type(letter, box, unknown, etc)
		long trackingNumber;
		int weight;
		double costPU;// Cost per weight unit
	public:
		virtual string getType(); //Return Package type
		virtual long getTrackingNumber(); //Return tracking number
		virtual double getWeight(); //Return weight(in pounds)
		virtual double getCostPU(); //Return cost per weight unit
		virtual string description()=0; //Return description defined my specific type
		virtual double getCost()=0; //Return total cost calculated from costPU and weight
		virtual double operator+(Package& rhs)=0; //Add the weight of one package to another(pounds)
};