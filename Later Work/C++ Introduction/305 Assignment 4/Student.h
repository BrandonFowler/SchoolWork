
//Class: CSCD305
//Homework: Assignment 4
//Compilers used: g++ and cl
//Author: Brandon Fowler

#ifndef STUDENT_H
#define STUDENT_H

#include<cstdlib>
#include<string>
#include<iostream>
using namespace std;

//Very simple Student class for testing LinkedLists
//===================================================================================
class Student{

	friend ostream& operator<<(ostream& out, Student student);
	
	private:
		string name;
		
	public:
		Student(string name = "Random Student"){
			this->name = name;
		}

		bool operator==(const Student& rhs) const;
		bool operator!=(const Student& rhs) const;
		bool operator>(const Student& rhs) const;

};

//Simple output operator overload for Student
//===================================================================================
ostream& operator<<(ostream& out, Student student){
	
	cout<<"Student Name: "<<student.name<<endl;
	return out;
}

//Simple equality overload for Student
//===================================================================================
bool Student::operator==(const Student& rhs) const{
	return (this->name.compare(rhs.name) == 0);
}

//Simple non-equality overload for Student
//===================================================================================
bool Student::operator!=(const Student& rhs) const{
	return !(*this == rhs);
}

//Simple greater-than overload for Student
//===================================================================================
bool Student::operator>(const Student& rhs) const{
	string temp = this->name;
	string temp2 = rhs.name;
	temp[0] = toupper(temp[0]);
	temp2[0] = toupper(temp2[0]);
	return (temp.compare(temp2) > 0);
}



#endif