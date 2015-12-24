
//Class: CSCD305
//Homework: Assignment 4
//Compilers used: g++ and cl
//Author: Brandon Fowler

#include "LinkedList.h"
#include "Student.h"
using namespace std;

//Helper methods for testing
int initialMenu();
void makeTestLists();
int testerMenu();
void studentListTester();
void intListTester();

//Initialize two of each type of linked list for testing
static LinkedList<Student> studentList1;
static LinkedList<Student> studentList2;
static LinkedList<int> intList1;
static LinkedList<int> intList2;

//Main
//===================================================================================
int main(){
	int choice = -1;
	while(choice != 0){
		makeTestLists();
		choice = initialMenu();
		if(choice == 1){
			cout<<"The first list contains: "<<endl;
			cout<<studentList1<<endl;
			cout<<"The second list contains: "<<endl;
			cout<<studentList2<<endl;
			studentListTester();
		}
		if(choice == 2){
			cout<<"The first list contains: "<<endl;
			cout<<intList1<<endl;
			cout<<"The second list contains: "<<endl;
			cout<<intList2<<endl;
			intListTester();
		}
		//Reset lists when switching between types
		studentList1 = LinkedList<Student>();
		studentList2 = LinkedList<Student>();
		intList1 = LinkedList<int>();
		intList2 = LinkedList<int>();
	}
}

//Initialize all linked lists with some data to run tests on
//===================================================================================
void makeTestLists(){
	studentList1.add(Student(string("Stu")));
	studentList1.add(Student(string("Chris")));
	studentList1.add(Student(string("Tom")));
	studentList1.add(Student(string("Chris")));
	studentList2.add(Student(string("Brandon")));
	studentList2.add(Student(string("Sally")));
	studentList2.add(Student(string("Sam")));
	studentList2.add(Student(string("Tod")));
	intList1.add(42);
	intList1.add(42);
	intList1.add(7);
	intList1.add(543);
	intList2.add(22);
	intList2.add(9999);
	intList2.add(42);
	intList2.add(22);
}

//Start menu
//===================================================================================
int initialMenu(){
	int choice = -1;
	while(choice < 0 || choice > 2){
		cout<<"What would you like to do?"<<endl; 
		cout<<" 0.Quit this program"<<endl;
		cout<<" 1.Test with two linked lists of students"<<endl;
		cout<<" 2.Test with two linked list of ints"<<endl;
		cout<<"choose: ";
		cin>>choice;
		cout<<endl;
		if(cin.fail() || choice < 0 || choice > 2){
			cin.clear();
			cin.ignore(10000,'\n');
			choice = 14;
			cout<<"Please enter a number between 0 and 2"<<endl; 
		}
	}
	return choice;
}

//Main testing menu
//===================================================================================
int testerMenu(){
	int choice = -1;
	while(choice < 0 || choice > 17){
		cout<<"What would you like to do?"<<endl; 
		cout<<" 0.Previous Menu"<<endl;
		cout<<" 1.Print first list(uses LinkedListIterator/output overload)"<<endl;
		cout<<" 2.Print second list(uses LinkedListIterator/output overload)"<<endl;
		cout<<" 3.Add a new item to the end of the first list"<<endl;
		cout<<" 4.Add a new item to a specific index of the first list"<<endl;
		cout<<" 5.Delete the first instance of matching data from the first list"<<endl;
		cout<<" 6.Delete an item at a specific index from the first list"<<endl;
		cout<<" 7.Delete all instances of matching data from the first list"<<endl;
		cout<<" 8.Sort the first list"<<endl;
		cout<<" 9.Combine both lists into a new list(Uses +)"<<endl;
		cout<<" 10.Set data at index of first list(uses [])"<<endl;
		cout<<" 11.Get data at index of first list(uses [])"<<endl;
		cout<<" 12.Check if lists are equal(uses ==)"<<endl;
		cout<<" 13.Assign first list into a new list(uses copy constructor)"<<endl;
		cout<<" 14.Move second list into a new list(uses move constructor)"<<endl;
		cout<<" 15.Assign first into a new list(uses assignment overload)"<<endl;
		cout<<" 16.Move first list into a new list(uses move assignment overload)"<<endl;
		cout<<" 17.Reset all lists back to default"<<endl;
		cout<<"choose: ";
		cin>>choice;
		cout<<endl;
		if(cin.fail() || choice < 0 || choice > 17){
			cin.clear();
			cin.ignore(10000,'\n');
			choice = -1;
			cout<<"Please enter a number between 0 and 17"<<endl; 
		}
	}
	return choice;
}

//Executes tests on student linked lists
//===================================================================================
void studentListTester(){
	int choice = -1; 
	while(choice != 0){
		choice = testerMenu();
		if(choice == 1){
			cout<<"The list contains: "<<endl;
			cout<<studentList1<<endl;
		}
		else if(choice == 2){
			cout<<"The list contains: "<<endl;
			cout<<studentList2<<endl;
		}
		else if(choice == 3){
			string name;
			cout<<"Enter the name of the student you would like to add: ";
			cin.ignore(10000,'\n');
			getline(cin,name);
			cout<<endl;
			studentList1.add(name);
			cout<<"The list now contains: "<<endl;
			cout<<studentList1<<endl;
		}
		else if(choice == 4){
			int index;
			cout<<"Choose the index:";
			cin>>index;
			cout<<endl;
			while(cin.fail()){
				cin.clear();
				cin.ignore(10000,'\n');
				choice = -1;
				cout<<"Please enter a number"<<endl; 
				cout<<"Choose the index:";
				cin>>index;
				cout<<endl;
			}
			string name;
			cout<<"Enter the name of the student you would like to add: ";
			cin.ignore(10000,'\n');
			getline(cin,name);
			cout<<endl;
			studentList1.add(index,name);
			cout<<"The list now contains: "<<endl;
			cout<<studentList1<<endl;
		}
		else if(choice == 5){
			string name;
			cout<<"Enter the name of the student you would like to delete(case sensitive!): ";
			cin.ignore(10000,'\n');
			getline(cin,name);
			cout<<endl;
			studentList1.deleteData(name);
			cout<<"The list now contains: "<<endl;
			cout<<studentList1<<endl;
		}
		else if(choice == 6){
			int index;
			cout<<"Choose the index:";
			cin>>index;
			cout<<endl;
			while(cin.fail()){
				cin.clear();
				cin.ignore(10000,'\n');
				choice = -1;
				cout<<"Please enter a number"<<endl; 
				cout<<"Choose the index:";
				cin>>index;
				cout<<endl;
			}
			studentList1.deleteIndex(index);
			cout<<"The list now contains: "<<endl;
			cout<<studentList1<<endl;
		}
		else if(choice == 7){
			string name;
			cout<<"Enter the name of the student you would like to delete(case sensitive!): ";
			cin.ignore(10000,'\n');
			getline(cin,name);
			cout<<endl;
			studentList1.deleteAll(name);
			cout<<"The list now contains: "<<endl;
			cout<<studentList1<<endl;
		}
		else if(choice == 8){
			studentList1.sort();
			cout<<"The list now contains: "<<endl;
			cout<<studentList1<<endl;
		}
		else if(choice == 9){
			LinkedList<Student> tempList = studentList1+studentList2;
			cout<<"The new list contains: "<<endl;
			cout<<tempList<<endl;
		}
		else if(choice == 10){
			int index;
			cout<<"Choose the index:";
			cin>>index;
			cout<<endl;
			while(cin.fail()){
				cin.clear();
				cin.ignore(10000,'\n');
				choice = -1;
				cout<<"Please enter a number"<<endl; 
				cout<<"Choose the index:";
				cin>>index;
				cout<<endl;
			}
			try{
				string name;
				cout<<"Enter the name of the student you would like to add: ";
				cin.ignore(10000,'\n');
				getline(cin,name);
				cout<<endl;
				studentList1[index] = Student(name);
				cout<<"The list now contains: "<<endl;
				cout<<studentList1<<endl;
			}
			catch(...){
				cout<<"Index chosen is out of range!"<<endl;
			}
		}
		else if(choice == 11){
			int index;
			cout<<"Choose the index:";
			cin>>index;
			cout<<endl;
			while(cin.fail()){
				cin.clear();
				cin.ignore(10000,'\n');
				choice = -1;
				cout<<"Please enter a number"<<endl; 
				cout<<"Choose the index:";
				cin>>index;
				cout<<endl;
			}
			try{
				cout<<"Student at specified index is: "<<studentList1[index]<<endl;
			}
			catch(...){
				cout<<"Index chosen is out of range!"<<endl;
			}
		}
		else if(choice == 12){
			if(studentList1 == studentList2){
				cout<<"The two lists are equal"<<endl;
			}
			else{
				cout<<"The two lists are not equal"<<endl;
			}
		}
		else if(choice == 13){
			LinkedList<Student> tempList = studentList1;
			cout<<"New list contents: "<<endl;
			cout<<tempList<<endl;
		}
		else if(choice == 14){
			LinkedList<Student> tempList = move(studentList2);
			cout<<"New list contents: "<<endl;
			cout<<tempList<<endl;
		}
		else if(choice == 15){
			LinkedList<Student> tempList;
			tempList = studentList1;
			cout<<"New list contents: "<<endl;
			cout<<tempList<<endl;
		}
		else if(choice == 16){
			LinkedList<Student> tempList; 
			tempList = move(studentList1);
			cout<<"New list contents: "<<endl;
			cout<<tempList<<endl;
		}
		else if(choice == 17){
			studentList1 = LinkedList<Student>();
			studentList2 = LinkedList<Student>();
			intList1 = LinkedList<int>();
			intList2 = LinkedList<int>();
			makeTestLists();
		}
	}
}

//Executes tests on int linked lists
//===================================================================================
void intListTester(){
	int choice = -1; 
	while(choice != 0){
		choice = testerMenu();
		if(choice == 1){
			cout<<"The list contains: "<<endl;
			cout<<intList1<<endl;
		}
		else if(choice == 2){
			cout<<"The list contains: "<<endl;
			cout<<intList2<<endl;
		}
		else if(choice == 3){
			int num;
			cout<<"Choose and integer to add:";
			cin>>num;
			cout<<endl;
			while(cin.fail()){
				cin.clear();
				cin.ignore(10000,'\n');
				choice = -1;
				cout<<"Please enter an integer"<<endl; 
				cout<<"Choose and integer:";
				cin>>num;
				cout<<endl;
			}
			intList1.add(num);
			cout<<"The list now contains: "<<endl;
			cout<<intList1<<endl;
		}
		else if(choice == 4){
			int index;
			cout<<"Choose the index:";
			cin>>index;
			cout<<endl;
			while(cin.fail()){
				cin.clear();
				cin.ignore(10000,'\n');
				choice = -1;
				cout<<"Please enter a number"<<endl; 
				cout<<"Choose the index:";
				cin>>index;
				cout<<endl;
			}
			int num;
			cout<<"Choose and integer to add:";
			cin>>num;
			cout<<endl;
			while(cin.fail()){
				cin.clear();
				cin.ignore(10000,'\n');
				choice = -1;
				cout<<"Please enter an integer"<<endl; 
				cout<<"Choose and integer:";
				cin>>num;
				cout<<endl;
			}
			intList1.add(index,num);
			cout<<"The list now contains: "<<endl;
			cout<<intList1<<endl;
		}
		else if(choice == 5){
			int num;
			cout<<"Choose and integer to remove:";
			cin>>num;
			cout<<endl;
			while(cin.fail()){
				cin.clear();
				cin.ignore(10000,'\n');
				choice = -1;
				cout<<"Please enter an integer"<<endl; 
				cout<<"Choose and integer:";
				cin>>num;
				cout<<endl;
			}
			intList1.deleteData(num);
			cout<<"The list now contains: "<<endl;
			cout<<intList1<<endl;
		}
		else if(choice == 6){
			int index;
			cout<<"Choose the index:";
			cin>>index;
			cout<<endl;
			while(cin.fail()){
				cin.clear();
				cin.ignore(10000,'\n');
				choice = -1;
				cout<<"Please enter a number"<<endl; 
				cout<<"Choose the index:";
				cin>>index;
				cout<<endl;
			}
			intList1.deleteData(index);
			cout<<"The list now contains: "<<endl;
			cout<<intList1<<endl;
		}
		else if(choice == 7){
			int num;
			cout<<"Choose and integer to remove:";
			cin>>num;
			cout<<endl;
			while(cin.fail()){
				cin.clear();
				cin.ignore(10000,'\n');
				choice = -1;
				cout<<"Please enter an integer"<<endl; 
				cout<<"Choose and integer:";
				cin>>num;
				cout<<endl;
			}
			intList1.deleteAll(num);
			cout<<"The list now contains: "<<endl;
			cout<<intList1<<endl;
		}
		else if(choice == 8){
			intList1.sort();
			cout<<"The list now contains: "<<endl;
			cout<<intList1<<endl;
		}
		else if(choice == 9){
			LinkedList<int> tempList = intList1+intList2;
			cout<<"The new list contains: "<<endl;
			cout<<tempList<<endl;
		}
		else if(choice == 10){
			int index;
			cout<<"Choose the index:";
			cin>>index;
			cout<<endl;
			while(cin.fail()){
				cin.clear();
				cin.ignore(10000,'\n');
				choice = -1;
				cout<<"Please enter a number"<<endl; 
				cout<<"Choose the index:";
				cin>>index;
				cout<<endl;
			}
			try{
				int num;
				cout<<"Enter the integer you would like to add: ";
				cin>>num;
				cout<<endl;
				while(cin.fail()){
					cin.clear();
					cin.ignore(10000,'\n');
					choice = -1;
					cout<<"Please enter an integer"<<endl; 
					cout<<"Choose the integer:";
					cin>>num;
					cout<<endl;
				}	
				intList1[index] = num;
				cout<<"The list now contains: "<<endl;
				cout<<intList1<<endl;
			}
			catch(...){
				cout<<"Index chosen is out of range!"<<endl;
			}
		}
		else if(choice == 11){
			int index;
			cout<<"Choose the index:";
			cin>>index;
			cout<<endl;
			while(cin.fail()){
				cin.clear();
				cin.ignore(10000,'\n');
				choice = -1;
				cout<<"Please enter a number"<<endl; 
				cout<<"Choose the index:";
				cin>>index;
				cout<<endl;
			}
			try{
				cout<<"The integer at the specified index is: "<<intList1[index]<<endl;
			}
			catch(...){
				cout<<"Index chosen is out of range!"<<endl;
			}
		}
		else if(choice == 12){
			if(intList1 == intList2){
				cout<<"The two lists are equal"<<endl;
			}
			else{
				cout<<"The two lists are not equal"<<endl;
			}
		}
		else if(choice == 13){
			LinkedList<int> tempList = intList1;
			cout<<"New list contents: "<<endl;
			cout<<tempList<<endl;
		}
		else if(choice == 14){
			LinkedList<int> tempList = move(intList2);
			cout<<"New list contents: "<<endl;
			cout<<tempList<<endl;
		}
		else if(choice == 15){
			LinkedList<int> tempList;
			tempList = intList1;
			cout<<"New list contents: "<<endl;
			cout<<tempList<<endl;
		}
		else if(choice == 16){
			LinkedList<int> tempList; 
			tempList = move(intList1);
			cout<<"New list contents: "<<endl;
			cout<<tempList<<endl;
		}
		else if(choice == 17){
			studentList1 = LinkedList<Student>();
			studentList2 = LinkedList<Student>();
			intList1 = LinkedList<int>();
			intList2 = LinkedList<int>();
			makeTestLists();
		}
	}
}






