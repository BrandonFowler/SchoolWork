
//Class: CSCD305
//Homework: Assignment 2
//Compilers used: g++ and cl
//Author: Brandon Fowler

#include "cbiginteger.h"

//==========================================================================
int main(){
	
	CBigInteger bigInt1;
	CBigInteger bigInt2;
	int choice = 1;
	
	cout<<endl;
	cout<<"Please enter your first big integer: ";
	cin>>bigInt1;
	cin.ignore(10000,'\n');//Clear extra input in case of spaces(example: 123 456, only 123 will be used)
	
	cout<<endl;
	cout<<"Please enter your second big integer: ";
	cin>>bigInt2;
	cin.ignore(10000,'\n');
	cout<<endl;
	
	while(choice){
		choice = bigIntMenu();
		executeChoice(choice, bigInt1, bigInt2);
	}	
}

//===========================================================================
int bigIntMenu(){
	int choice = 0;
	cout<<"Your options are: "<<endl;
	cout<<" 0.Quit this program"<<endl;
	cout<<" 1.Change first big integer"<<endl;
	cout<<" 2.Change second big integer"<<endl;
	cout<<" 3.View first big integer"<<endl;
	cout<<" 4.View second big integer"<<endl;
	cout<<" 5.Test first integer > second integer"<<endl;
	cout<<" 6.Test first integer < second integer"<<endl;
	cout<<" 7.Test first integer >= second integer"<<endl;
	cout<<" 8.Test first integer <= second integer"<<endl;
	cout<<" 9.Test first integer == second integer"<<endl;
	cout<<"10.Test first integer != second integer"<<endl;
	cout<<"11.Test first integer + second integer"<<endl;
	cout<<"12.Test first integer - second integer"<<endl;
	cout<<"13.Test first integer * second integer"<<endl;
	cout<<"Choose the number of your option(example: 1): ";
	cin>>choice;
	if(cin.fail()){//Error check for unusable input(example: abcd)
		cin.clear();
		cin.ignore(10000,'\n');
		choice = 14;
	}
	cout<<endl;
	return choice;
}

//============================================================================
void executeChoice(int choice, CBigInteger& bigInt1, CBigInteger& bigInt2){
	if(choice == 0){
		cout<<"Bye"<<endl;
	}
	else if(choice == 1){
		cout<<"Please enter your first big integer: ";
		cin>>bigInt1;
		cin.ignore(10000,'\n');
		cout<<endl;
	}
	else if(choice == 2){
		cout<<"Please enter your second big integer: ";
		cin>>bigInt2;
		cin.ignore(10000,'\n');
		cout<<endl;
	}
	else if(choice == 3){
		cout<<bigInt1<<endl;
		cout<<endl;
	}
	else if(choice == 4){
		cout<<bigInt2<<endl;
		cout<<endl;
	}
	else if(choice == 5){
		if(bigInt1 > bigInt2){
			cout<<"The first integer is greater than the second"<<endl;
			cout<<endl;
		}
		else{
			cout<<"The first integer is not greater than the second"<<endl;
			cout<<endl;
		}
	}
	else if(choice == 6){
		if(bigInt1 < bigInt2){
			cout<<"The first integer is less than the second"<<endl;
			cout<<endl;
		}
		else{
			cout<<"The first integer is not less than the second"<<endl;
			cout<<endl;
		}
	}
	else if(choice == 7){
		if(bigInt1 >= bigInt2){
			cout<<"The first integer is greater than or equal to the second"<<endl;
			cout<<endl;
		}
		else{
			cout<<"The first integer is not greater than or equal to the second"<<endl;
			cout<<endl;
		}
	}
	else if(choice == 8){
		if(bigInt1 <= bigInt2){
			cout<<"The first integer is less than or equal to the second"<<endl;
			cout<<endl;
		}
		else{
			cout<<"The first integer is not less than or equal to the second"<<endl;
			cout<<endl;
		}
	}
	else if(choice == 9){
		if(bigInt1 == bigInt2){
			cout<<"The first integer is equal to the second"<<endl;
			cout<<endl;
		}
		else{
			cout<<"The first integer is not equal to the second"<<endl;
			cout<<endl;
		}
	}
	else if(choice == 10){
		if(bigInt1 != bigInt2){
			cout<<"The first integer is not equal to the second"<<endl;
			cout<<endl;
		}
		else{
			cout<<"The first integer is equal to the second"<<endl;
			cout<<endl;
		}
	}
	else if(choice == 11){
		CBigInteger result = bigInt1 + bigInt2;
		cout<<"The result is: "<<result<<endl;
		cout<<endl;
	}
	else if(choice == 12){
		CBigInteger result = bigInt1 - bigInt2;
		cout<<"The result is: "<<result<<endl;
		cout<<endl;
	}
	else if(choice == 13){
		CBigInteger result = bigInt1 * bigInt2;
		cout<<"The product is: "<<result<<endl;
		cout<<endl;
	}
	else{//Valid option was not chosen
		cout<<endl;
		cout<<"Bad input, please only enter numbers 0-13"<<endl;
		cout<<endl;
	}
	
}