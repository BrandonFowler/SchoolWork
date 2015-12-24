//Class: CSCD305
//Homework: Assignment 1
//Compilers used: g++ and cl
//Author: Brandon Fowler

#ifndef COMPLEX_H
#define COMPLEX_H

#include<iostream>
#include<cstdlib>
using namespace std;

/**
Complex class:
This class contains data and functions necessary to 
preform simple mathematical operations for complex
numbers as specified by the user. 
**/
class Complex{
	
	//Overloaded non-member functions
	friend istream& operator>>(istream& in, Complex& complex);
	friend ostream& operator<<(ostream& out, Complex& complex);
	
	private:
		//Data fields
		int real;
		int imaginary;
	public:
		//Overloaded member functions
		Complex(int real = 0, int imaginary = 0);
		Complex operator+(const Complex& rhs) const;
		Complex operator-(const Complex& rhs) const;
		Complex operator*(const Complex& rhs) const;
		bool operator==(const Complex& rhs) const;
		bool operator!=(const Complex& rhs) const;
};


#endif