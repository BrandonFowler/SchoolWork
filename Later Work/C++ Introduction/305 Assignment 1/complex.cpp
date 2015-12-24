//Class: CSCD305
//Homework: Assignment 1
//Compilers used: g++ and cl
//Author: Brandon Fowler

#include "complex.h"

Complex::Complex(int real, int imaginary){
	this->real = real;
	this->imaginary = imaginary;
}

Complex Complex::operator+(const Complex& rhs) const{
	Complex result;
	
	result.real = this->real+rhs.real;
	result.imaginary = this->imaginary+rhs.imaginary;
	
	return result;
}

Complex Complex::operator-(const Complex& rhs) const{
	Complex result;
	
	result.real = this->real-rhs.real;
	result.imaginary = this->imaginary-rhs.imaginary;
	
	return result;
}

Complex Complex::operator*(const Complex& rhs) const{
	Complex result;
	
	result.real = (this->real*rhs.real)-(this->imaginary*rhs.imaginary);
	result.imaginary = (this->imaginary*rhs.real)+(this->real*rhs.imaginary);
	
	return result;
}

bool Complex::operator==(const Complex& rhs) const{
	return (this->real == rhs.real)&&(this->imaginary == rhs.imaginary);
}

bool Complex::operator!=(const Complex& rhs) const{
	return !(*(this) == rhs);
}

istream& operator>>(istream& in, Complex& complex){
	char sign, i;
	
	in>>complex.real>>sign>>complex.imaginary>>i;
	
	if(sign == '-'){
		complex.imaginary = complex.imaginary*(-1);
	}
	
	return in;
}

ostream& operator<<(ostream& out, Complex& complex){
	if(complex.real == 0 && complex.imaginary == 0)
	{
		out<<"0"<<endl;
	}
	else if(complex.real == 0){
		out<<complex.imaginary<<"i"<<endl;
	}
	else if(complex.imaginary == 0){
		out<<complex.real<<endl;
	}
	else if(complex.imaginary > 0){
		out<<complex.real<<"+"<<complex.imaginary<<"i"<<endl;
	}
	else{
		out<<complex.real<<complex.imaginary<<"i"<<endl;
	}
	
	return out;
}