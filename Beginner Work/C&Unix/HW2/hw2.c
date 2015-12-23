#include "hw2.h"
#include "fixRedirect.h"
#include <time.h>
#include <stdio.h>
#include <stdlib.h>


/*
hw2.c
Part of Homework 2
Author: Brandon Fowler
Class: CSCD240-02
*/


int playerRow;//Stores what row the player is on
int playerCol;//Stores what column the player is on
int hagRow;//Stores what row the hag is on
int hagCol;//Stores what column the hag is on
int playerTurnCount;//Keeps track of how many actions taken


/*
createMaze function:
Initialize 2D maze, brings in chars from input into a char 2D array,
then populates an int 2D array(maze) based on chars in the 2D char array.
*/
void createMaze(int maze[20][25])
{
	srand(time(0));//Seed Random
	char inMaze[20][50];//Stores chars from input(50 cols because of white space chars)
	int i;
	int j;
	for(i=0;i<20;i++)
	{
		for(j=0;j<50;j++)
		{
			scanf("%c",&inMaze[i][j]);//Bring in chars
		}
		
	}

	//Initialize int array based on char array(skips white space chars)
	for(i=0;i<20;i++)
	{
		
		for(j=0;j<50;j=j+2)
		{
		
			if(inMaze[i][j] =='T')
			{
				maze[i][j/2] = 2;//Tree Marker
				
			}
			else if(inMaze[i][j] =='F')
			{
				maze[i][j/2] = 3;//End Marker
				
			}
			else
			{
				maze[i][j/2] = 1;//Path Marker
				
			}
			
			
			
		}
		

	}
	
}


/*
Integer Markers in int 2D array:
1 is path unseen
2 is tree unseen
3 is finish unseen
4 is hag unseen
5 is path seen
6 is tree seen
7 is finish seen
8 is hag seen
9 is start
*/


/*
placeHag function:
Randomly place int marker for hag into maze with error checking
for valid placement.
*/
void placeHag(int maze[20][25])
{
	hagRow = rand()%20;//Random Hag Start Row
	hagCol = rand()%25;//Random Hag Strat Col
	if(maze[hagRow][hagCol] == 2 || maze[hagRow][hagCol] == 3)//Bad Placement
	{
		placeHag(maze);//Start Over
	}
	else
	{
		maze[hagRow][hagCol] = 4;//Place Hag Marker
	}
}


/*
placeStart function:
Randomly place int marker for start into maze with error checking
for valid placement.
*/
void placeStart(int maze[20][25])
{
	playerRow = rand()%20;//Random Player Start Row
	playerCol = rand()%25;//Random Player Strat Col
	if(maze[playerRow][playerCol] == 2 || maze[playerRow][playerCol] == 3 || maze[playerRow][playerCol] == 4)
	{
		placeStart(maze);//Start Over
	}
	else
	{
		maze[playerRow][playerCol] = 9;//Place Start Marker
	}
}


/* 
readDirection function:
Gets char input from user signifying move north south
east or west, converts to upper, then returns input.
*/
char readDirection()
{
	char choice;//Stores input
	printf("What direction would you like to move?(N, S, E or W):");
	fflush(stdout);
	while(fgetc(stdin)!='\n');
	scanf("%c",&choice);
	choice = toupper(choice);
	if(choice == 'N' || choice == 'S' || choice == 'E' || choice == 'W')
	{
		return choice;
	}
	else//Bad input
	{
		printf("\n \n");
		printf("Invalid Choice!");
		choice = readDirection();//Start Over
	}
	return choice;
}


/*
move function:
Error checks for out of array bounds, then calls functions
to move the player.
*/
void move(int maze[20][25],char direction)
{
	if(direction == 'N')
	{
		if(playerRow-1 < 0)
		{
			printf("Moving N to [%d][%d] - Failed Out of Bounds\n",playerRow-1,playerCol);
		}
		else
		{
			moveNorth(maze);//Moves Player
		}
	}
	else if(direction == 'W')
	{
		if(playerCol-1 < 0)
		{
			printf("Moving W to [%d][%d] - Failed Out of Bounds\n",playerRow,playerCol-1);
		}
		else
		{
			moveWest(maze);//Moves Player
		}
	}
	else if(direction == 'E')
	{
		if(playerCol+1 > 24)
		{
			printf("Moving E to [%d][%d] - Failed Out of Bounds\n",playerRow,playerCol+1);
		}
		else
		{
			moveEast(maze);//Moves Player
		}
	}
	else if(direction == 'S')
	{
		if(playerRow+1 > 19)
		{
			printf("Moving S to [%d][%d] - Failed Out of Bounds\n",playerRow+1,playerCol);
		}
		else
		{
			moveSouth(maze);//Moves Player
		}
	}
}


/*
displayerMaze function:
Uses integer markers in maze 2D array to print out a char
representation of the maze.
*/
void displayMaze(int maze[20][25])
{
	int i;
	int j;
	for(i=0;i<20;i++)
	{
		for(j=0;j<25;j++)
		{
			if(maze[i][j] == 1)//Unseen Path
			{
				printf("U ");
			}
			else if(maze[i][j] == 2)//Unseen Tree
			{
				printf("U ");
			}
			else if(maze[i][j] == 3)//Unseen Finish
			{
				printf("U ");
			}
			else if(maze[i][j] == 4)//Unseen Hag
			{
				printf("U ");
			}
			else if(maze[i][j] == 5)//Visible Path
			{
				printf(". ");
			}
			else if(maze[i][j] == 6)//Visible Tree
			{
				printf("T ");
			}
			else if(maze[i][j] == 7)//Visible Finish
			{
				printf("F ");
			}
			else if(maze[i][j] == 8)//Visible Hag
			{
				printf("H ");
			}
			else//Start Point
			{
				printf("S ");
			}
		}
		printf("\n");
	}
}


/*
peek function:
Make areas directly connected to the players 
position visible, and displays the maze.
*/
void peek(int maze[20][25])
{
	if(playerCol+1 < 25)//Check Bounds
	{
		if(maze[playerRow][playerCol+1] == 1)
		{
			maze[playerRow][playerCol+1] = 5;		
		}
		if(maze[playerRow][playerCol+1] == 2)
		{
			maze[playerRow][playerCol+1] = 6;
		}
		if(maze[playerRow][playerCol+1] == 3)
		{
			maze[playerRow][playerCol+1] = 7;
		}
		if(maze[playerRow][playerCol+1] == 4)
		{
			maze[playerRow][playerCol+1] = 8;
		}				
	}
	if(playerCol-1 >= 0)//Check Bounds
	{
		if(maze[playerRow][playerCol-1] == 1)
		{
			maze[playerRow][playerCol-1] = 5;		
		}
		if(maze[playerRow][playerCol-1] == 2)
		{
			maze[playerRow][playerCol-1] = 6;
		}
		if(maze[playerRow][playerCol-1] == 3)
		{
			maze[playerRow][playerCol-1] = 7;
		}
		if(maze[playerRow][playerCol-1] == 4)
		{
			maze[playerRow][playerCol-1] = 8;
		}				
	}
	if(playerRow+1 < 20)//Check Bounds
	{
		if(maze[playerRow+1][playerCol] == 1)
		{
			maze[playerRow+1][playerCol] = 5;		
		}
		if(maze[playerRow+1][playerCol] == 2)
		{
			maze[playerRow+1][playerCol] = 6;
		}
		if(maze[playerRow+1][playerCol] == 3)
		{
			maze[playerRow+1][playerCol] = 7;
		}
		if(maze[playerRow+1][playerCol] == 4)
		{
			maze[playerRow+1][playerCol] = 8;
		}				
	}
	if(playerRow-1 >= 0)//Check Bounds
	{
		if(maze[playerRow-1][playerCol] == 1)
		{
			maze[playerRow-1][playerCol] = 5;		
		}
		if(maze[playerRow-1][playerCol] == 2)
		{
			maze[playerRow-1][playerCol] = 6;
		}
		if(maze[playerRow-1][playerCol] == 3)
		{
			maze[playerRow-1][playerCol] = 7;
		}
		if(maze[playerRow-1][playerCol] == 4)
		{
			maze[playerRow-1][playerCol] = 8;
		}				
	}
	displayMaze(maze);//Print Maze
}


/*
menu function:
Print menu and get choice from user.
*/
int menu()
{
	int choice;
	printf("What would you like to do?\n");
	printf("1) Move\n");
	printf("2) Display the maze\n");
	printf("3) Peek ahead\n");
	printf("4) Quit Game\n");
	printf("Please Choose:");
	fflush(stdout);
	scanf("%d",&choice);
	printf("\n\n");
	if(choice < 1 || choice > 4)//Bad Input
	{
		printf("Invalid Choice\n");
		choice = menu();//Start Over
	}
	fflush(stdout);
	playerTurnCount++;
	return choice;
}


/*
moveNorth function
Moves player location North if possible, and places proper markers
*/
void moveNorth(int maze[20][25])
{
	if(maze[playerRow-1][playerCol] == 1)
	{
		maze[playerRow-1][playerCol] = 5;
		printf("Moving N to [%d][%d] - Success\n",playerRow-1,playerCol);
		playerRow--;
		
	}
	else if(maze[playerRow-1][playerCol] == 2)
	{
		maze[playerRow-1][playerCol] = 6;
		printf("Moving N to [%d][%d] - Failed Tree In The Way\n",playerRow-1,playerCol);
	}
	else if(maze[playerRow-1][playerCol] == 3)
	{
		maze[playerRow-1][playerCol] = 7;
		printf("Moving N to [%d][%d] - Success Found The Exit!\n",playerRow-1,playerCol);
		playerRow--;
		
	}
	else if(maze[playerRow-1][playerCol] == 4)
	{
		maze[playerRow-1][playerCol] = 8;
		printf("Moving N to [%d][%d] - Failed You Got Eaten By The Hag\n",playerRow-1,playerCol);
		playerRow--;
		
	}
	else if(maze[playerRow-1][playerCol] == 5)
	{
		printf("Moving N to [%d][%d] - Success\n",playerRow-1,playerCol);
		playerRow--;
		
	}
	else if(maze[playerRow-1][playerCol] == 6)
	{
		printf("Moving N to [%d][%d] - Failed Tree In The Way\n",playerRow-1,playerCol);
	}
	else if(maze[playerRow-1][playerCol] == 7)
	{
		printf("Moving N to [%d][%d] - Success You Found The Exit!\n",playerRow-1,playerCol);
		playerRow--;
		
	}
	else if(maze[playerRow-1][playerCol] == 8)
	{
		printf("Moving N to [%d][%d] - Failed You Got Eaten By The Hag\n",playerRow-1,playerCol);
		playerRow--;
		
	}
	else
	{
		printf("Moving N to [%d][%d] - Success You Are Back At The Start\n",playerRow-1,playerCol);
		playerRow--;
		
	}
}


/*
moveSouth function
Moves player location South if possible, and places proper markers
*/
void moveSouth(int maze[20][25])
{
	if(maze[playerRow+1][playerCol] == 1)
	{
		maze[playerRow+1][playerCol] = 5;
		printf("Moving S to [%d][%d] - Success\n",playerRow+1,playerCol);
		playerRow++;
		
	}
	else if(maze[playerRow+1][playerCol] == 2)
	{
		maze[playerRow+1][playerCol] = 6;
		printf("Moving S to [%d][%d] - Failed Tree In The Way\n",playerRow+1,playerCol);
	}
	else if(maze[playerRow+1][playerCol] == 3)
	{
		maze[playerRow+1][playerCol] = 7;
		printf("Moving S to [%d][%d] - Success Found The Exit!\n",playerRow+1,playerCol);
		playerRow++;
		
	}
	else if(maze[playerRow+1][playerCol] == 4)
	{
		maze[playerRow+1][playerCol] = 8;
		printf("Moving S to [%d][%d] - Failed You Got Eaten By The Hag\n",playerRow+1,playerCol);
		playerRow++;
		
	}
	else if(maze[playerRow+1][playerCol] == 5)
	{
		printf("Moving S to [%d][%d] - Success\n",playerRow+1,playerCol);
		playerRow++;
		
	}
	else if(maze[playerRow+1][playerCol] == 6)
	{
		printf("Moving S to [%d][%d] - Failed Tree In The Way\n",playerRow+1,playerCol);
	}
	else if(maze[playerRow+1][playerCol] == 7)
	{
		printf("Moving S to [%d][%d] - Success You Found The Exit!\n",playerRow+1,playerCol);
		playerRow++;
		
	}
	else if(maze[playerRow+1][playerCol] == 8)
	{
		printf("Moving S to [%d][%d] - Failed You Got Eaten By The Hag\n",playerRow+1,playerCol);
		playerRow++;
		
	}
	else
	{
		printf("Moving S to [%d][%d] - Success You Are Back At The Start\n",playerRow+1,playerCol);
		playerRow++;
		
	}
}


/*
moveEast function
Moves player location East if possible, and places proper markers
*/
void moveEast(int maze[20][25])
{
	if(maze[playerRow][playerCol+1] == 1)
	{
		maze[playerRow][playerCol+1] = 5;
		printf("Moving E to [%d][%d] - Success\n",playerRow,playerCol+1);
		playerCol++;
		
	}
	else if(maze[playerRow][playerCol+1] == 2)
	{
		maze[playerRow][playerCol+1] = 6;
		printf("Moving E to [%d][%d] - Failed Tree In The Way\n",playerRow,playerCol+1);
	}
	else if(maze[playerRow][playerCol+1] == 3)
	{
		maze[playerRow][playerCol+1] = 7;
		printf("Moving E to [%d][%d] - Success Found The Exit!\n",playerRow,playerCol+1);
		playerCol++;
		
	}
	else if(maze[playerRow][playerCol+1] == 4)
	{
		maze[playerRow][playerCol+1] = 8;
		printf("Moving E to [%d][%d] - Failed You Got Eaten By The Hag\n",playerRow,playerCol+1);
		playerCol++;
		
	}
	else if(maze[playerRow][playerCol+1] == 5)
	{
		printf("Moving E to [%d][%d] - Success\n",playerRow,playerCol+1);
		playerCol++;
		
	}
	else if(maze[playerRow][playerCol+1] == 6)
	{
		printf("Moving E to [%d][%d] - Failed Tree In The Way\n",playerRow,playerCol+1);
	}
	else if(maze[playerRow][playerCol+1] == 7)
	{
		printf("Moving E to [%d][%d] - Success You Found The Exit!\n",playerRow,playerCol+1);
		playerCol++;
		
	}
	else if(maze[playerRow][playerCol+1] == 8)
	{
		printf("Moving E to [%d][%d] - Failed You Got Eaten By The Hag\n",playerRow,playerCol+1);
		playerCol++;
		
	}
	else
	{
		printf("Moving E to [%d][%d] - Success You Are Back At The Start\n",playerRow,playerCol+1);
		playerCol++;
		
	}
}


/*
moveWest function
Moves player location West if possible, and places proper markers
*/
void moveWest(int maze[20][25])
{
	if(maze[playerRow][playerCol-1] == 1)
	{
		maze[playerRow][playerCol-1] = 5;
		printf("Moving W to [%d][%d] - Success\n",playerRow,playerCol-1);
		playerCol--;
		
	}
	else if(maze[playerRow][playerCol-1] == 2)
	{
		maze[playerRow][playerCol-1] = 6;
		printf("Moving W to [%d][%d] - Failed Tree In The Way\n",playerRow,playerCol-1);
	}
	else if(maze[playerRow][playerCol-1] == 3)
	{
		maze[playerRow][playerCol-1] = 7;
		printf("Moving W to [%d][%d] - Success Found The Exit!\n",playerRow,playerCol-1);
		playerCol--;
		
	}
	else if(maze[playerRow][playerCol-1] == 4)
	{
		maze[playerRow][playerCol-1] = 8;
		printf("Moving W to [%d][%d] - Failed You Got Eaten By The Hag\n",playerRow,playerCol-1);
		playerCol--;
		
	}
	else if(maze[playerRow][playerCol-1] == 5)
	{
		printf("Moving W to [%d][%d] - Success\n",playerRow,playerCol-1);
		playerCol--;
		
	}
	else if(maze[playerRow][playerCol-1] == 6)
	{
		printf("Moving W to [%d][%d] - Failed Tree In The Way\n",playerRow,playerCol-1);
	}
	else if(maze[playerRow][playerCol-1] == 7)
	{
		printf("Moving W to [%d][%d] - Success You Found The Exit!\n",playerRow,playerCol-1);
		playerCol--;
		
	}
	else if(maze[playerRow][playerCol-1] == 8)
	{
		printf("Moving W to [%d][%d] - Failed You Got Eaten By The Hag\n",playerRow,playerCol-1);
		playerCol--;
		
	}
	else
	{
		printf("Moving W to [%d][%d] - Success You Are Back At The Start\n",playerRow,playerCol-1);
		playerCol--;
		
	}
}


/*
moveHag function
Moves the hag randomly in a direction, if possible.
*/
void moveHag(int maze[20][25])
{
	if(playerTurnCount%3 == 0)//Every third player turn
	{
		int moveD = rand()%3;//Random number from 0-3
		if(moveD == 0)//North
		{
			if(hagRow-1 > 0)//Within bounds
			{
				if(maze[hagRow-1][hagCol] == 1)//Moving to unseen Path
				{
					if(maze[hagRow][hagCol] == 4)//From unseen Path
					{
						maze[hagRow][hagCol] = 1;
						maze[hagRow-1][hagCol] = 4;
						hagRow--;
					}
					if(maze[hagRow][hagCol] == 8)//From Visible Path
					{
						maze[hagRow][hagCol] = 5;
						maze[hagRow-1][hagCol] = 4;
						hagRow--;
					}
				}
				if(maze[hagRow-1][hagCol] == 3)//Moving to visible Path
				{
					if(maze[hagRow][hagCol] ==4)//From unseen Path
					{
						maze[hagRow][hagCol] = 1;
						maze[hagRow-1][hagCol] = 8;
						hagRow--;
					}
					if(maze[hagRow][hagCol] ==8)//From Visible Path
					{
						maze[hagRow][hagCol] = 3;
						maze[hagRow-1][hagCol] = 8;
						hagRow--;
					}
				}
			}
		}
		else if(moveD == 1)//South
		{
			if(hagRow+1 < 20)//Within Bounds
			{
				if(maze[hagRow+1][hagCol] == 1)
				{
					if(maze[hagRow][hagCol] == 4)
					{
						maze[hagRow][hagCol] = 1;
						maze[hagRow+1][hagCol] = 4;
						hagRow++;
					}
					if(maze[hagRow][hagCol] == 8)
					{
						maze[hagRow][hagCol] = 5;
						maze[hagRow+1][hagCol] = 4;
						hagRow++;
					}
				}
				if(maze[hagRow+1][hagCol] == 3)
				{
					if(maze[hagRow][hagCol] ==4)
					{
						maze[hagRow][hagCol] = 1;
						maze[hagRow+1][hagCol] = 8;
						hagRow++;
					}
					if(maze[hagRow][hagCol] ==8)
					{
						maze[hagRow][hagCol] = 3;
						maze[hagRow+1][hagCol] = 8;
						hagRow++;
					}
				}
			}	
		}
		else if(moveD == 2)//West
		{
			if(hagCol-1 > 0)//Within Bounds
			{
				if(maze[hagRow][hagCol-1] == 1)
				{
					if(maze[hagRow][hagCol] == 4)
					{
						maze[hagRow][hagCol] = 1;
						maze[hagRow][hagCol-1] = 4;
						hagCol--;
					}
					if(maze[hagRow][hagCol] == 8)
					{
						maze[hagRow][hagCol] = 5;
						maze[hagRow][hagCol-1] = 4;
						hagCol--;
					}
				}
				if(maze[hagRow][hagCol-1] == 3)
				{
					if(maze[hagRow][hagCol] ==4)
					{
						maze[hagRow][hagCol] = 1;
						maze[hagRow][hagCol-1] = 8;
						hagCol--;
					}
					if(maze[hagRow][hagCol] ==8)
					{
						maze[hagRow][hagCol] = 3;
						maze[hagRow][hagCol-1] = 8;
						hagCol--;
					}
				}
			}
		}
		else//East
		{
			if(hagCol+1 < 25)//Within Bounds
			{
				if(maze[hagRow][hagCol+1] == 1)
				{
					if(maze[hagRow][hagCol] == 4)
					{
						maze[hagRow][hagCol] = 1;
						maze[hagRow][hagCol+1] = 4;
						hagCol++;
					}
					if(maze[hagRow][hagCol] == 8)
					{
						maze[hagRow][hagCol] = 5;
						maze[hagRow][hagCol+1] = 4;
						hagCol++;
					}
				}
				if(maze[hagRow][hagCol+1] == 3)
				{
					if(maze[hagRow][hagCol] ==4)
					{
						maze[hagRow][hagCol] = 1;
						maze[hagRow][hagCol+1] = 8;
						hagCol++;
					}
					if(maze[hagRow][hagCol] ==8)
					{
						maze[hagRow][hagCol] = 3;
						maze[hagRow][hagCol+1] = 8;
						hagCol++;
					}
				}
			}
		}
	}
}


/*
endGame function:
Checks for player at finish or caught by hag.
*/
int endGame(int maze[20][25])
{
	if(maze[playerRow][playerCol] == 7)//Player on finish
	{
		printf("You found the end! Congratz you win!\n");
		return 0;
	}
	if(maze[playerRow][playerCol] == 8)//player on Hag
	{
		printf("You got killed by the hag! Game over man.....\n");
		return 0;
	}
	return 1;
}
