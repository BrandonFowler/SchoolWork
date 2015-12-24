
/*
Author: Brandon Fowler
Class: CSCD305
Assignment: 3
Quarter: Summer 2014
Compilers Used: g++ and cl
*/

#include "Truck.h"

using namespace std;

//========================================================================================================================
Truck::Truck(string manifest){
	this->fin.open(manifest);//open input file
	this->fout.open("log.txt");//open output file
	string emptySpace;//Used to clean new line characters between getting integers and getting lines from input
	
	//Set precision for printing doubles
	this->fout.setf( std::ios::fixed, std:: ios::floatfield );
	this->fout.precision(2);
	
	this->loadedPackages = 0;//Track number of packages loaded
	
	if(fin.fail()){
		cout<<"Failed to open input file: "<<manifest<<endl;
		exit(EXIT_FAILURE);
	}
	getline(fin,this->driverName);
	fin >> this->unloadedWeight;
	getline(fin,emptySpace);
	getline(fin,this->source);
	getline(fin,this->destination);
	fin >> this->capacity;
}

//=========================================================================================================================
void Truck::load(){
	long tempTracking = 0;
	int tempWeight = 0;
	string tempType;
	printTruckInfo();
	this->fout<<"PACKAGE LOADING INFORMATION:"<<endl;
	this->fout<<"----------------------------"<<endl;
	
	while(fin>>tempTracking){//While there is another tracking number(handles possibility of newline at file end) 
		fin>>tempWeight;
		try{
			if(tempTracking%10 == 0){
				tempType = "letter";
			}
			else if(tempTracking%10 == 1){
				tempType = "box";
			}
			else if(tempTracking%10 == 2){
				tempType = "wood crate";
			}
			else if(tempTracking%10 == 3){
				tempType = "metal crate";
			}
			else{
				tempType = "unknown";
			}
		
			string validity = isValid(tempType, tempWeight);//Check if package is valid
		
			if(validity.compare("ok") != 0){//If validity is not "ok", throw a PackageException exception
				throw PackageException(tempTracking, tempWeight, validity, tempType);
			}
			else{//If is valid, "get space" for it and load it
				if(tempTracking%10 == 0){
					this->packages.push_back(shared_ptr<Package>(new Letter(tempTracking, tempWeight)));
				}
				else if(tempTracking%10 == 1){
					this->packages.push_back(shared_ptr<Package>(new Box(tempTracking, tempWeight)));
				}
				else if(tempTracking%10 == 2){
					this->packages.push_back(shared_ptr<Package>(new WoodCrate(tempTracking, tempWeight)));
				}
				else{
					this->packages.push_back(shared_ptr<Package>(new MetalCrate(tempTracking, tempWeight)));
				}
				this->fout<<(*this->packages.back()).description()<<endl;//Write loaded package description to log
				this->fout<<"LOADED"<<endl<<endl;
				this->loadedPackages++;//Keep track of how many packages are loaded
			}
		}
		catch(PackageException e){
			this->fout<<e.description()<<endl;
		}
	}
	this->fin.close();
}

//=========================================================================================================================
void Truck::drive(){
	this->fout<<"LOADED TRUCK WEIGHT: "<<getWeight()<<" pounds"<<endl<<endl;
	this->fout<<"DRIVING TRUCK FROM "<<upper(this->source)<<" TO "<<upper(this->destination)<<"..."<<endl;
	this->fout<<"ARRIVED AT "<<upper(this->destination)<<endl<<endl;
}

//=========================================================================================================================
void Truck::unload(){
	this->fout<<"PACKAGE UNLOADING INFORMATION"<<endl;
	this->fout<<"-----------------------------"<<endl;
	for(shared_ptr<Package> p: this->packages){
		this->fout<<(*p).description()<<endl<<endl;
	}
	
	this->fout<<"FINAL TRUCK INFORMATION"<<endl;
	this->fout<<"-----------------------"<<endl;
	this->fout<<"Packages Delivered: "<<this->loadedPackages<<endl;
	this->fout<<"Total cost: $"<<getTotalCost()<<endl;
	this->fout.close();
}

//=========================================================================================================================
string Truck::isValid(string type, int weight){
	if(this->loadedPackages == this->capacity){
		return "TRUCK FULL";
	}
	else if(weight <= 0){
		return "INVALID WEIGHT";
	}
	else if(type.compare("unknown") == 0){
		return "UNKNOWN PACKAGE TYPE";
	}
	else if(type.compare("letter") == 0 && weight > 32){
		return "TOO HEAVY";
	}
	else if(type.compare("box") == 0 && weight > 40){
		return "TOO HEAVY";
	}
	else if(type.compare("wood crate") == 0 && weight > 80){
		return "TOO HEAVY";
	}
	else if(type.compare("metal crate") == 0 && weight > 100){
		return "TOO HEAVY";
	}
	else{
		return "ok";
	}
}

//=========================================================================================================================
double Truck::getWeight(){
	double lWeight = this->unloadedWeight;
	for(shared_ptr<Package> p: this->packages){
		lWeight += (*p).getWeight();
	}
	return lWeight;
}

//=========================================================================================================================
void Truck::printTruckInfo(){
	this->fout<<"Driver name: "<<this->driverName<<endl;
	this->fout<<"Unloaded truck weight: "<<this->unloadedWeight<<endl;
	this->fout<<"Source city: "<<this->source<<endl;
	this->fout<<"Destination city: "<<this->destination<<endl;
	this->fout<<"Maximum packages truck can carry: "<<this->capacity<<endl<<endl;
}

//=========================================================================================================================
string Truck::upper(string& orig)
{
	string upper;

	for(int i = 0; i < orig.size(); ++i)
		upper += (toupper(orig[i]));
	
	return upper;
}

//=========================================================================================================================
double Truck::getTotalCost(){
	double tCost = 0;
	for(shared_ptr<Package> p: this->packages){
		tCost += (*p).getCost();
	}
	return tCost;
}