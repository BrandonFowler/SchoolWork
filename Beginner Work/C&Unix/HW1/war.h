#ifndef HW1_H
#define HW1_H

/*
Author: Brandon Fowler
Description: Header file for HW1, has all includes enums,and prototypes.
*/

enum suits{Spades,Hearts,Diamonds,Clubs};
enum ranks{Two,Three,Four,Five,Six,Seven,Eight,Nine,Ten,Jack,Queen,King,Ace};
typedef enum ranks Ranks;
typedef enum suits Suits;

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

//convertRanks function
//Returns rank in String form
char * convertRank(Ranks r);

//convertSuit function
//Returns suit in String form
char * convertSuit(Suits s);

//getNames function
//Asks users for names then returns them
void getNames(char name1[],char name2[]);

//strip function
//Strips un-needed characters from names(\r \n)
void strip(char name[]);

//initialize function
//Fills the deck with integers representing cards
void initialize(int deck[]);

//Shuffle
//Asks user how many times to shuffle then shuffles
void shuffle(int deck[],char name1[]);

//playwar function
//Draws cards, finds rank and suit
//Compares players cards
//Displays and tracks win/losses/ties
void playWar(int deck[], char name1[], char name2[], int stats[]);

//goAgain function
//Ask user to go again and return int
int goAgain();

#endif
