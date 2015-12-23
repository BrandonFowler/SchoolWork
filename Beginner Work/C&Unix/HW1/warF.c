#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "war.h"

/*
Author: Brandon Fowler
Description: This file contains all of the functions used in HW1
*/

extern const int deckL;

//getNames function
//Asks users for names then returns them
void getNames(char name1[],char name2[])
{
	printf("\n \n");
	printf("Please enter name for player 1: ");
	fgets(name1,100,stdin);
	printf("\n \n");
	printf("Please enter name for player 2: ");
	fgets(name2,100,stdin);
	printf("\n \n");	
}

//strip function
//Strips un-needed characters from names(\r \n)
void strip(char name[])
{
	int len = strlen(name);
	if(name[len-2]=='\r')
	{
		name[len-2]='\0';
	}
	else if(name[len-1]=='\n')
	{
		name[len-1]='\0';
	}
}

//initialize function
//Fills the deck with integers representing cards
void initialize(int deck[])
{
	int i;
	for(i=0;i<deckL;i++)
	{
		deck[i] = i;
	}
}

//Shuffle
//Asks user how many times to shuffle then shuffles
void shuffle(int deck[],char name1[])
{
	int shuffleTimes;//Stores user input
	printf("Welcome %s, How many times would you like the deck shuffled?:",name1);
	scanf("%d",&shuffleTimes);//Get user input
	printf("\n \n");
	while(fgetc(stdin)!='\n');//Clears buffer
	int j;
	for(j=0;j<shuffleTimes;j++)
	{
		int i;
		for(i=0;i<deckL;i++)
		{
			int index = rand()%52;//Produce random index
			int temp = deck[i];
      			deck[i] = deck[index]; 
      			deck[index] = temp;
		}
	}
}

//playwar function
//Draws cards, finds rank and suit
//Compares players cards
//Displays and tracks win/losses/ties
void playWar(int deck[], char name1[], char name2[], int stats[])
{
	Ranks p1Rank;//Stores number symbolizing rank
	Suits p1Suit;//Stores number symbolizing suit
	Ranks p2Rank;//Stores number symbolizing rank
	Suits p2Suit;//Stores number symbolizing suit
	char * Rank1;//Stores string symbolizing rank 
	char * Suit1;//Stores string symbolizing suit
	char * Rank2;//Stores string symbolizing rank
	char * Suit2;//Stores string symbolizing suit
	stats[0]=0;//Initialize player 1 wins and player 2 losses to zero 
	stats[1]=0;//Initialize player 2 wins and player 1 losses to zero
	stats[2]=0;//Initialize players ties to zero
	int i;
	for(i=0;i<deckL;i=i+2)//Draw 2 cards each time till done
	{
		p1Rank = deck[i]%13;//Rank Number
		p1Suit = deck[i]/13;//Suit Number
		p2Rank = deck[i+1]%13;//Rank Number
		p2Suit = deck[i+1]/13;//Suit Number
		Rank1 = convertRank(p1Rank);//Rank String
		Suit1 = convertSuit(p1Suit);//Suit String
		Rank2 = convertRank(p2Rank);//Rank String
		Suit2 = convertSuit(p2Suit);//Suit String
		if(deck[i]%13 == deck[i+1]%13)//Test for players tie
		{
			stats[2]=stats[2]+1;//Record tie
			printf("%s has drawn a %s of %s\n",name1,Rank1,Suit1);
			printf("%s has drawn a %s of %s\n",name2,Rank2,Suit2);
			printf("We have a tie!\n");
			printf("\n \n");	
		}
		else if(deck[i]%13 > deck[i+1]%13)//Test for player 1 win
		{
			stats[0]=stats[0]+1;//Record win/loss
			printf("%s has drawn a %s of %s\n",name1,Rank1,Suit1);
			printf("%s has drawn a %s of %s\n",name2,Rank2,Suit2);
			printf("%s wins this round!",name1);
			printf("\n \n");
		}
		else//Player 2 win
		{
			stats[1]=stats[1]+1;//Record win/loss
			printf("%s has drawn a %s of %s\n",name1,Rank1,Suit1);
			printf("%s has drawn a %s of %s\n",name2,Rank2,Suit2);
			printf("%s wins this round!",name2);
			printf("\n \n");
		}		
	}
	if(stats[0]==stats[1])//Over all tie
	{
		printf("All cards have been drawn!\n");
		printf("%s has %d wins, %d losses, and %d ties.\n",name1,stats[0],stats[1],stats[2]);
		printf("%s has %d wins, %d losses, and %d ties.\n",name2,stats[1],stats[0],stats[2]);
		printf("We have an even tie!");
		printf("\n \n");
	}
	else if(stats[0] > stats[1])//Player 1 win over all
	{
		printf("All cards have been drawn!\n");
		printf("%s has %d wins, %d losses, and %d ties.\n",name1,stats[0],stats[1],stats[2]);
		printf("%s has %d wins, %d losses, and %d ties.\n",name2,stats[1],stats[0],stats[2]);
		printf("%s has won!",name1);
		printf("\n \n");	
	}
	else//Player 2 win over all
	{
		printf("All cards have been drawn!\n");
		printf("%s has %d wins, %d losses, and %d ties.\n",name1,stats[0],stats[1],stats[2]);
		printf("%s has %d wins, %d losses, and %d ties.\n",name2,stats[1],stats[0],stats[2]);
		printf("%s has won!",name2);
		printf("\n \n");
	}
}

//convertRanks function
//Returns rank in String form
char * convertRank(Ranks r)
{
	switch(r)
	{
		case 0:return "Two";
		case 1:return "Three";
		case 2:return "Four";
		case 3:return "Five";
		case 4:return "Six";
		case 5:return "Seven";
		case 6:return "Eight";
		case 7:return "Nine";
		case 8:return "Ten";
		case 9:return "Jack";
		case 10:return "Queen";
		case 11:return "King";
		case 12:return "Ace";
	}
}

//convertSuit function
//Returns suit in String form
char * convertSuit(Suits s)
{
	switch(s)
	{
		case 0:return "Spades";
		case 1:return "Hearts";
		case 2:return "Diamonds";
		case 3:return "Clubs";
	}
}

//goAgain function
//Ask user to go again and return int
int goAgain()
{
	char again[100];//Store user input
	printf("Go again(yes/no)?: ");
	scanf("%s",again);//Get user input
	while(fgetc(stdin)!='\n');//Clear buffer
	printf("\n \n");
	int x;
	for(x=0;x<strlen(again);x++)
	{
		again[x] = tolower(again[x]);//Convert to lower case
	}
	if(strcmp(again,"yes")==0)//Input is yes
	{
		return 1; //Return true
	}
	else if(strcmp(again,"no")==0)//Input is no
	{
		return 0;//Return false
	}
	else//Bad input
	{
		return goAgain();//Ask again
	}
}


