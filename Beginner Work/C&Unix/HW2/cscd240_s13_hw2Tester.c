#include "hw2.h"
#include "fixRedirect.h"
#include <time.h>
#include <stdio.h>
#include <stdlib.h>


/*
cscd240_s13_hw2Tester.c
Tester given for this homework
Modifier: Brandon Fowler
Class: CSCD240-02
*/


const int ROWS = 20;
const int COLS = 25;
extern int playerRow;//Track player Row Location
extern int playerCol;//Track player Col Location

int main()
{
	int choice;
	char direction;
	int maze[ROWS][COLS];
	int end = 1;//Stores game continue true/false
	
	createMaze(maze);
	placeHag(maze);
	placeStart(maze);
	// don't remove the next line
	afterRedirect();
	// don't remove the above line

	// NOTE: stdout has been mangled from the redirection
	// so after you do a printf without a \n execute the command fflush(stdout);
	
	printf("\n \n");
	printf("Maze initialized, and ready to play!\n");
	printf("Starting position [%d][%d]\n",playerRow, playerCol);
	
	do
	{
		choice = menu();

		if(choice == 1)
		{
			direction = readDirection();
			move(maze, direction);
		}// end choice

		else if(choice ==2)
			displayMaze(maze);

		else if(choice ==3)
			peek(maze);
		else
		{
			printf("Game exit\n");
			end = 0;//Game end
		}
		moveHag(maze);//Moves hag every third turn

	}while(endGame(maze) && end);

	return 0;

}// end main
