//Creator: Brandon Fowler
//DungeonAdventure.java(Part of Assignment 2)
//Class:CSCD 211

//Cheats: Type Ultima for 1000 HP, Type View to see the entire Dungeon, Type End to quick Exit
//Both pits and health potions have a 20% chance to spawn
//Pits do 1 to 50 damage
//Health Potions raise HP by 15 to 30 points

import java.util.*;

//DungeonAdventure Class
//Contains calls to constructors, and methods that manipulate the hero, and dungeon
//Also displays menu to get users choice, and exicutes that choice
public class DungeonAdventure
{	
	//Main Method
	//Calls constructors to create the dungeon, and hero, then calls the menu method
	public static void main(String []args)
	{
		System.out.println("Welcome to Dungeon Adventure!");
		System.out.println("The rules are simple. You can choose to move North, South,");
		System.out.println("East or West, to explore. Your hero will automatically pick up");
		System.out.println("items. You will start at the entrance of the Dungeon. Your");
		System.out.println("goal is to find the exit of the dungeon, but first you must");
		System.out.println("find both of the pieces of the Crown of Coding before you can");
		System.out.println("leave. Remeber the commands are Caps sensetive. Good luck hero!");
		
		
		System.out.println();
		System.out.print("Enter the name of your hero to begin: ");
		Scanner kb = new Scanner(System.in);
		String Name = kb.next(); 
		
		Hero Adventurer = new Hero(Name);
		Dungeon TheLabrynth = new Dungeon();
		TheLabrynth.makeRooms();
		TheLabrynth.makeDoors();
		TheLabrynth.setEntranceAndHero();
		TheLabrynth.setExit();
		TheLabrynth.setCrown1();
		TheLabrynth.setCrown2();
		
		choiceMenu(TheLabrynth,Adventurer);
	}
	
	//ChoiceMenu Method
	//Contains repeating menu loop to display current dungeon room,
	//give options, and get user choice/input. It then exicutes that choice
	//by calling methods, or taking action directly.
	public static void choiceMenu(Dungeon Labrynth,Hero Adventurer)
	{
		int row = Labrynth.StartRow;
		int col = Labrynth.StartCol;
		String choice;
		System.out.println("You have entered the Labrynth!");
		System.out.println();
		System.out.println("Room you have entered");
		System.out.print(Labrynth.HeroPosition);
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("You can move in a direction(type the direction you want to move. Example: North)");
		System.out.println("You can use a health potion if you have one(type Health)");
		System.out.println("You can view your hero's stats, and inventory(type Hero)");
		Scanner kb = new Scanner(System.in);
		System.out.print("Make your choice: ");
		choice = kb.next();
		
		while(choice.compareTo("End")!= 0)
		{
			if (choice.compareTo("North")==0)
			{
				if (row == 0)
				{
					System.out.println("Sorry there isn't a door that way, try again");
				}
				else
				{
					row--;
					Labrynth.HeroPosition = Labrynth.Dungeon[row][col];
				}
			}
			else if (choice.compareTo("South")==0)
			{
				if (row == 4)
				{
					System.out.println("Sorry there isn't a door that way, try again");
				}
				else
				{
					row++;
					Labrynth.HeroPosition = Labrynth.Dungeon[row][col];
				}
			}
			else if (choice.compareTo("West")==0)
			{
				if (col == 0)
				{
					System.out.println("Sorry there isn't a door that way, try again");
				}
				else
				{
					col--;
					Labrynth.HeroPosition = Labrynth.Dungeon[row][col];
				}
			}
			else if (choice.compareTo("East")==0)
			{
				if (col == 4)
				{
					System.out.println("Sorry there isn't a door that way, try again");
				}
				else
				{
					col++;
					Labrynth.HeroPosition = Labrynth.Dungeon[row][col];
				}
			}
			else if(choice.compareTo("Health")==0)
			{
				if (Adventurer.HPotions > 0)
				{
					System.out.println("Gulp, some HP restored");
					Adventurer.healthIncrease();
					Adventurer.HPotions--;
				}
				else
				{
					System.out.println("You have no potions sorry.");
				}
			}
			else if (choice.compareTo("Hero")==0)
			{
				System.out.print(Adventurer);
				System.out.println();
			}
			else if(choice.compareTo("View")==0)
			{
				System.out.print(Labrynth);
				System.out.println();
			}
			else if(choice.compareTo("Ultima")==0)
			{
				Adventurer.HP = 1000;
			}
			else
			{
				System.out.println("Not a valid choice, please try again.");
			}
			
			
			if (Labrynth.Dungeon[row][col].HealthP == 1 && Labrynth.Dungeon[row][col].Pit == 1)
			{
				System.out.println("YOU FOUND A HEALTH POTION!!!!!!");
				Adventurer.HPotions++;
				Labrynth.Dungeon[row][col].HealthP--;
				System.out.println("Ouch you fell in a pit!");
				Adventurer.pitDecrease();
			}
			else if (Labrynth.Dungeon[row][col].HealthP == 1)
			{
				System.out.println("YOU FOUND A HEALTH POTION!!!!!!");
				Adventurer.HPotions++;
				Labrynth.Dungeon[row][col].HealthP--;
			}
			else if(Labrynth.Dungeon[row][col].Pit == 1)
			{
				System.out.println("Ouch you fell in a pit!");
				Adventurer.pitDecrease();
			} 
			else if(Labrynth.Dungeon[row][col].CrownP1 == 1)
			{
				System.out.println("YOU FOUND A CROWN PIECE!!!!!!!!");
				Adventurer.CrownP++;
				Labrynth.Dungeon[row][col].CrownP1--;
			}
			else if(Labrynth.Dungeon[row][col].CrownP2 == 1)
			{
				System.out.println("YOU FOUND A CROWN PIECE!!!!!!!!");
				Adventurer.CrownP++;
				Labrynth.Dungeon[row][col].CrownP2--;
			}
			else if(Labrynth.Dungeon[row][col].Exit == 1 && Adventurer.CrownP == 2)
			{
				System.out.print(Labrynth);
				System.out.println();
				System.out.println("Gratz you win");
				choice = "End";
			}
			else if(Labrynth.Dungeon[row][col].Exit == 1)
			{
				System.out.println("You found the exit, but you need both crown pieces first!");
			}
			else
			{
				System.out.println("The room is empty");
			}
			
			
			if(Adventurer.HP < 0)
			{
				System.out.println("Game over your dead!");
				System.out.println("Guess you aren't cut out for this stuff.");
				choice = "End";
			}
			
			
			if (choice.compareTo("End")==0)
			{
				choice = choice;
			}
			else
			{
				System.out.println("Room you have entered");
				System.out.print(Labrynth.HeroPosition);
				System.out.println();
				System.out.println("What would you like to do?");
				System.out.println("You can move in a direction(type the direction you want to move. Example: North)");
				System.out.println("You can use a health potion if you have one(type Health)");
				System.out.println("You can view your hero's stats, and inventory(type Hero)");
				kb = new Scanner(System.in);
				System.out.print("Make your choice: ");
				choice = kb.next();
			}
		}
	}
}