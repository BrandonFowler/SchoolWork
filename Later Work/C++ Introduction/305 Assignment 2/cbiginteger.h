
//Class: CSCD305
//Homework: Assignment 2
//Compilers used: g++ and cl
//Author: Brandon Fowler

#ifndef CBIGINTEGER_H
#define CBIGINTEGER_H

#include<iostream>
#include<cstdlib>
#include<string>
using namespace std;

/**
CBigInteger class:
This class contains data and functions necessary to 
preform simple mathematical and relational operations 
for big integer numbers as specified by the user. 
**/
class CBigInteger{

	//Overloaded IO operators
	friend istream& operator>>(istream& in, CBigInteger& biginteger);
	friend ostream& operator<<(ostream& out, CBigInteger& biginteger);
	
	private:
		int *digits;
		int size;
		int sign; //-1 or 1
		
	public:
		//Constructor, copy constructor, and destructor
		CBigInteger(int size = 0, int sign = 1);
		CBigInteger(const CBigInteger& original);
		~CBigInteger();
		
		//Overloaded assignment operator
		CBigInteger& operator=(const CBigInteger& original);
		
		//Overloaded relational operators
		bool operator>(const CBigInteger& rhs) const;
		bool operator<(const CBigInteger& rhs) const;
		bool operator==(const CBigInteger& rhs) const;
		bool operator!=(const CBigInteger& rhs) const;
		bool operator>=(const CBigInteger& rhs) const;
		bool operator<=(const CBigInteger& rhs) const;
		
		//Overloaded mathematical operators
		CBigInteger operator+(const CBigInteger& rhs) const;
		CBigInteger operator-(const CBigInteger& rhs) const;
		CBigInteger operator*(const CBigInteger& rhs) const;
		
		//Strip leading zeros from digits
		void stripZeros();
};

//Helper methods for main tester
void executeChoice(int choice, CBigInteger& bigInt1, CBigInteger& bigInt2);
int bigIntMenu();

#endif