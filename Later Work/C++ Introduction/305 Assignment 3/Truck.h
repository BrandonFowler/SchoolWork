
/*
Author: Brandon Fowler
Class: CSCD305
Assignment: 3
Quarter: Summer 2014
Compilers Used: g++ and cl
*/


#pragma once

#include<string>
#include<vector>
#include "Package.h"
#include "Letter.h"
#include "Box.h"
#include "WoodCrate.h"
#include "MetalCrate.h"
#include<memory>
#include<fstream>
#include<cstdlib>
#include<iostream>
#include "PackageException.h"


using namespace std;

class Truck{
	
	private:
		ifstream fin;//input file
		ofstream fout;//output file
		vector<shared_ptr<Package>> packages;//list of packages
		int capacity;//amount of packages truck can hold
		int loadedPackages;//amount of packages that have been loaded
		int unloadedWeight;//unloaded truck weight
		string driverName;
		string source;
		string destination;
	public:
		Truck(string manifest = "manifest.txt"); 
		void load();
		void drive();
		void unload();
		void printTruckInfo();//Driver name, truck weight, capacity, etc
		double getWeight();
		double getTotalCost(); //Total amount that truck earned from shipping costs
		string upper(string& str); //Converts a string to upper case(used for printing source and destination cities) 
		string isValid(string type, int weight); //Checks if a package is valid and returns either "ok" or a description of the problem
};