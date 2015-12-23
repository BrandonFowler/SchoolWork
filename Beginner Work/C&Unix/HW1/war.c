#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "war.h"

/*
Author: Brandon Fowler
Description: This file contains the main program.
Initializes variables and calls all functions needed.
*/

const int deckL = 52;//Deck  size

int main()
{	
	int deck[deckL];//Stores Deck of cards(integers 0-51)
	char name1[100];//Stores a name given by player 1
	char name2[100];//Stores a name given by player 2
	int stats[3];//Stores wins losses and ties for both players
	getNames(name1,name2);//Gets names from users
	strip(name1);//Strips un-needed characters from names(\r \n)
	strip(name2);//Strips un-needed characters from names(\r \n)
	initialize(deck);//Puts integer values into the deck
	srand(time(0));//Seeds random
	shuffle(deck,name1);//Shuffles the deck amount of times the user says
	playWar(deck,name1,name2,stats);//Runs the main game
	while(goAgain())//Keeps program running as long as users want
	{
		shuffle(deck,name1);
		playWar(deck,name1,name2,stats);
	}
	return 0;
}
