#include "linkedlist.h"


/*
hw6.h
Header file for hw6
Author: Brandon Fowler
Class: CSCD240-02
*/


/*
readNumber function
Reads a number from the user, parses the digits,
and builds a linked list.
*/
Node * readNumber();


/*
printNumber function
Prints the name of the number, then calls
recurse print.
*/
void printNumber(char number[], Node * nList);


/*
printList function
Needed to comply with hw6 directions.
Simply calls recursePrint function.
*/
void printList(Node * nList);


/*
recursePrint
Prints the linked list recursivley, from the
end of the linked list to the begining.
*/
void recursePrint(Node * cur);


/*
excecuteChoice function
Takes in both linkedlists(Numbers), and a
function pointer; calls the function passed in,
then calls printNumber to print results.
*/
void executeChoice(Node * lTwo,Node * lOne, Node*(*funcPtr)(Node * lTwo,Node * lOne));


/*
plus function
Adds two linkedlists(Numbers) together, may call
the minus function if situation is appropriate.
*/
Node * plus(Node * lTwo,Node * lOne);


/*
minus function
Subtracts second linkedlist(number) from the first.
May call the pluss function if appropriate.
*/
Node * minus(Node * lTwo,Node * lOne);


/*
stripzeros function
Strips the leading zeros from a linkedlist(number)
*/
void stripzeros(Node ** head);


/*
stripzeros function
Strips the leading zeros from a linkedlist(number)
*/
int menu();
