
//Class: CSCD305
//Homework: Assignment 4
//Compilers used: g++ and cl
//Author: Brandon Fowler

#ifndef CLINKEDLIST_H_H
#define CLINKEDLIST_H

#include<cstdlib>
#include<string>
#include<iostream>
using namespace std;

//Linked List Class
//===================================================================================
template <class T>
class LinkedList{

	//Friended output operator(Uses LinkedListIterator)
	template <class F>
	friend ostream& operator<<(ostream& out, LinkedList<F>& list);
	
	private:
		//Nested Node Class
		//===========================================================================
		class Node{
		
			public:
				//Node fields
				T* data;
				Node* next = NULL;
				
				//In-lined short necessary Node functions 
				Node(){this->data = NULL;}
				Node(T data){this->data = new T(data);}
				~Node(){delete this->data;}
		};//End Nested Node
		
		//Linked List Fields
		Node* head;
		int size = 0;
		
	public:
		//Nested LinkedListIterator Class
		//============================================================================
		class LinkedListIterator{
			private:
				//Iterator fields
				LinkedList<T>* list;
				Node* cur;
				
			public:
			//In-lined short necessary iterator functions 
			LinkedListIterator(LinkedList<T>& list, Node* node){this->list = &list;	this->cur = node;}
			T& operator*() const {return *(cur->data);}
			LinkedListIterator& operator++() {this->cur = cur->next;	return *this;}
			bool operator==(LinkedListIterator const& other) const {return cur == other.cur;}
			bool operator!=(LinkedListIterator const& other) const {return !(*this == other);}
		};//End Nested LinkedListIterator
		
		//Linked List Function Declarations
		//===================================================================================
		LinkedList();
		LinkedList(const LinkedList<T>& original);
		LinkedList(LinkedList<T>&& other);
		~LinkedList();
		LinkedList<T>& operator=(const LinkedList<T>& original);
		LinkedList<T>& operator=(LinkedList<T>&& other);
		void clean();
		void recurseClean(Node * cur);
		void add(T data);
		void add(int i, T data);
		bool deleteData(T data);
		T deleteIndex(int i);
		bool deleteAll(T data);
		bool operator==(const LinkedList<T>& rhs) const;
		LinkedList<T> operator+(const LinkedList<T>& rhs) const;
		const T& operator [](int i) const;
		T& operator [](int i);
		void sort();
		LinkedListIterator begin();
		LinkedListIterator end();
};

//Outputs contents of a linked list and demonstrates 
//custom iterator compatibility with new c++ 11 for loop
//===================================================================================
template <class T>
ostream& operator<<(ostream& out, LinkedList<T>& list){
	for(T cur : list){
		cout<<cur<<endl;
	}
	return out;
}

//Basic LinkedList constructor
//===================================================================================
template <class T>
LinkedList<T>::LinkedList(){
	this->head = NULL;
	this->size = 0;
}

//LinkedList copy constructor
//===================================================================================
template <class T>
LinkedList<T>::LinkedList(const LinkedList<T>& original){
	this->head = NULL;
	this->size = 0;
	*this = original;
}

//LinkedList move copy constructor
//===================================================================================
template <class T>
LinkedList<T>::LinkedList(LinkedList<T>&& other){
	this->head = NULL;
	this->size = 0;
	*this = move(other);
}

//LinkedList destructor
//===================================================================================
template <class T>
LinkedList<T>::~LinkedList(){
	this->clean();
}

//LinkedList overloaded assignment operator
//===================================================================================
template <class T>
LinkedList<T>& LinkedList<T>::operator=(const LinkedList<T>& original){
	Node* cur = original.head;
	if(this->head != NULL){
		this->clean();
	}
	while(cur != NULL){
		this->add(*(cur->data));
		cur = cur->next;
	}
	return *this;
}

//LinkedList overloaded move assignment operator
//===================================================================================
template <class T>
LinkedList<T>& LinkedList<T>::operator=(LinkedList<T>&& other){
	if(this != &other){
		if(this->head != NULL){
			this->clean();
		}
		this->head = other.head;
		this->size = other.size;
		
		other.head = NULL;
		other.size = 0;
	}
	return *this;
}

//Used to start the clean up process of dynamically allocated memory of LinkedLists
//===================================================================================
template <class T>		
void LinkedList<T>::clean(){
	if(this->head != NULL){
		recurseClean(this->head);
		this->size = 0;
	}
}

//Recursively cleans dynamically allocated memory of LinkedLists
//===================================================================================
template <class T>		
void  LinkedList<T>::recurseClean(Node * cur){
	if(cur->next != NULL)
	{
		recurseClean(cur->next);
	}
	delete cur;
	cur = NULL;
}

//Adds a new node to the end of a LinkedList
//===================================================================================
template <class T>
void LinkedList<T>::add(T data){
	if(this->head == NULL){
		this->head = new Node(data);
	}
	else{
		Node* cur = this->head;
		while(cur->next != NULL){
			cur = cur->next;
		}
		cur->next = new Node(data);
	}
	this->size++;
}

//Adds a new Node at the specified index of a LinkedList
//===================================================================================
template <class T>
void LinkedList<T>::add(int i, T data){

	if(i < 0 || i > this->size){
		try{
			throw "Out of range";
		}
		catch(...){
			cout<<"Index is out of range"<<endl;
		}
	}
	else{
		if(this->head == NULL){
			add(data);
		}
		else if(i == 0){
			Node* newNode = new Node(data);
			newNode->next = this->head;
			this->head = newNode;
			this->size++;
		}
		else{
			Node* cur = this->head;
			Node* prev = cur;
	
			for(int j = 0; j < i; j++){
				prev = cur;
				cur = cur->next;
			}
			Node* newNode = new Node(data);
			newNode->next = cur;
			prev->next = newNode;
			this->size++;
		}
	}
}

//Deletes the first instance of specified data in a LinkedList
//===================================================================================
template <class T>
bool LinkedList<T>::deleteData(T data){
	if(this->head == NULL){
		return false;
	}
	if(*(this->head->data) == data){
		Node* temp = this->head;
		this->head = this->head->next;
		delete temp;
		this->size--;
		return true;
	}
	else{
		Node* cur = this->head;
		Node* prev = cur;
		while(cur != NULL){
			if(*(cur->data) == data){
				prev->next = cur->next;
				delete cur;
				this->size--;
				return true;
			}
			prev = cur;
			cur = cur->next;
		}
	}
	return false;
}

//Deletes a Node at the specified index of a LinkedList
//===================================================================================
template <class T>
T LinkedList<T>::deleteIndex(int i){

	if(i < 0 || i >= this->size){
		try{
			throw "Out of range";
		}
		catch(...){
			cout<<"Index is out of range"<<endl;
			return T();
		}
	}
	else{
		if(i == 0){
			Node* temp = this->head;
			this->head = this->head->next;
			T data = *(temp->data);
			delete temp;
			this->size--;
			return data;
		}
		else{
			Node* cur = this->head;
			Node* prev = cur;
			for(int j = 0; j < i; j++){
				prev = cur;
				cur = cur->next;
			}
			prev->next = cur->next;
			T data = *(cur->data);
			delete cur;
			this->size--;
			return data;
		}
	}
}

//Deletes all instances of the specified data in a LinkedList
//===================================================================================
template <class T>
bool LinkedList<T>::deleteAll(T data){
	if(this->head == NULL){
		return false;
	}
	bool deleted = false;
	if(*(this->head->data) == data){
		while(this->head != NULL && *(this->head->data) == data){
			Node* temp = this->head;
			this->head = this->head->next;
			delete temp;
			this->size--;
			deleted = true;
		}
	}
	else{
		Node* cur = this->head;
		Node* prev = cur;
		while(cur != NULL){
			if(*(cur->data) == data){
				prev->next = cur->next;
				delete cur;
				this->size--;
				cur = prev;
				deleted = true;
			}
			prev = cur;
			cur = cur->next;
		}
	}
	return deleted;
}

//Overloaded equality operator for LinkedList
//===================================================================================
template <class T>
bool LinkedList<T>::operator==(const LinkedList<T>& rhs) const{
	if(this->size != rhs.size){
		return false;
	}
	if(this->head == NULL && rhs.head == NULL){
		return true;
	}
	
	Node* curThis = this->head;
	Node* curThat = rhs.head;
	
	while(curThis != NULL && curThat != NULL){
		if(*(curThis->data) != *(curThat->data)){
			return false;
		}
		curThis = curThis->next;
		curThat = curThat->next;
	}
	return true;
}

//Overloaded addition operator of a LinkedList(concatenates two LinkedLists into a new one)
//=================================================================================== 
template <class T>
LinkedList<T> LinkedList<T>::operator+(const LinkedList<T>& rhs) const{
	LinkedList temp;
	Node* cur = this->head;
	while(cur != NULL){
		temp.add(*(cur->data));
		cur = cur->next;
	}
	cur = rhs.head;
	while(cur != NULL){
		temp.add(*(cur->data));
		cur = cur->next;
	}
	return temp;
}

//Overloaded square bracket operators for LinkedList
//===================================================================================
template <class T>
const T& LinkedList<T>::operator [](int i) const{
	if(this->head == NULL){
		throw "empty list";
	}
	if(i < 0 || i >= this->size){
		throw "out of range";
	}
	
	Node* cur = this->head;
	
	for(int j = 0; j < i; j++){
		cur = cur->next;
	}
	return *(cur->data);
}
template <class T>
T& LinkedList<T>::operator [](int i){
	if(this->head == NULL){
		throw "empty list";
	}
	if(i < 0 || i >= this->size){
		throw "out of range";
	}
	
	Node* cur = this->head;
	
	for(int j = 0; j < i; j++){
		cur = cur->next;
	}
	return *(cur->data);
}

//Sorts a LinkedList
//===================================================================================
template <class T>
void LinkedList<T>::sort(){
	Node* cur = this->head;
	T temp;
	
	for(int i = 0; i < this->size; i++){
		while(cur->next != NULL){
			if(*(cur->data) > *(cur->next->data)){
				temp = *(cur->data);
				*(cur->data) = *(cur->next->data);
				*(cur->next->data) = temp;
			}
			else{
				cur = cur->next;
			}
		}
		cur = this->head;
	}
}

//Required for use of LinkedListIterator and new c++ for loop
//===================================================================================
template <class T>
typename LinkedList<T>::LinkedListIterator LinkedList<T>::begin() {
	Node* node = this->head;
	return LinkedListIterator(*this, node);
}

//Required for use of LinkedListIterator and new c++ for loop
//===================================================================================
template <class T>		
typename LinkedList<T>::LinkedListIterator LinkedList<T>::end() {
	Node* node = this->head;
	return LinkedListIterator(*this, NULL);
}

#endif

