//Class: CSCD305
//Homework: Assignment 1
//Compilers used: g++ and cl
//Author: Brandon Fowler

#include "complex.h"

int main(){
	
	Complex complex1, complex2;
	int x = 1;
	
	cout<<"Enter a complex number(Example: 1 - 2i): ";
	cin>>complex1;
	cout<<endl;
	cout<<"Enter another complex number: ";
	cin>>complex2;
	cout<<endl;
	
	while(x){
		int choice = 0;
		cout<<endl;
		cout<<"Please type the number of the option you would like to use(Example: 1)"<<endl;
		cout<<" 1. Change first complex number"<<endl;
		cout<<" 2. Change second complex number"<<endl;
		cout<<" 3. Show first complex number"<<endl;
		cout<<" 4. Show second complex number"<<endl;
		cout<<" 5. Add both complex numbers"<<endl;
		cout<<" 6. Subtract the second complex number from the first(first - second)"<<endl;
		cout<<" 7. Subtract the first complex number from the second(second - first)"<<endl;
		cout<<" 8. Multiply both complex numbers together"<<endl;
		cout<<" 9. Check if both complex numbers are equivalent(first == second)"<<endl;
		cout<<"10. Check if both complex numbers are not equivalent(first != second)"<<endl;
		cout<<"11. Quit this program"<<endl;
		cout<<"Please Choose: ";
		cin>>choice;
		cout<<endl;
		
		if(choice == 1){
			cout<<"Enter a complex number(Example: 1 - 2i): ";
			cin>>complex1;
			cout<<endl;
		}
		else if(choice == 2){
			cout<<"Enter a complex number(Example: 1 - 2i): ";
			cin>>complex2;
			cout<<endl;
		}
		else if(choice == 3){
			cout<<complex1;
		}
		else if(choice == 4){
			cout<<complex2;
		}
		else if(choice == 5){
			Complex result = complex1+complex2;
			cout<<"The result is: "<<result;
		}
		else if(choice == 6){
			Complex result = complex1-complex2;
			cout<<"The result is: "<<result;
			
		}
		else if(choice == 7){
			Complex result = complex2-complex1;
			cout<<"The result is: "<<result;
		}
		else if(choice == 8){
			Complex result = complex1*complex2;
			cout<<"The result is: "<<result;
		}
		else if(choice == 9){
			if(complex1 == complex2){
				cout<<"The two complex numbers are equivalent"<<endl;
			}
			else{
				cout<<"The two complex numbers are not equivalent"<<endl;
			}
		}
		else if(choice == 10){
			if(complex1 != complex2){
				cout<<"The two complex numbers are not equivalent"<<endl;
			}
			else{
				cout<<"The two complex numbers are equivalent"<<endl;
			}
		}
		else if(choice == 11){
			x = 0;
		}
		else{
			cout<<"Invalid choice. Try again!"<<endl;
		}
	
	}
	
}