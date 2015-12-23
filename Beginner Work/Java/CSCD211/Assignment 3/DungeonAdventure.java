//Creator: Brandon Fowler
//DungeonAdventure.java(Part of Assignment 3)
//Class:CSCD 211
//Multiple Extra Credit Attempted, See Java Doc Notes Below For Specifics

//NOTES:
//Cheats: Type Ultima for 1000 HP, Type View to see the entire Dungeon, Type End to quick Exit, Type Gold to give player 500 gold
//Both pits and health potions have a 20% chance to spawn
//Pits do 1 to 50 damage
//Health Potions raise HP by 50 to 60 points
//There are three normal monsters called ChildOfInsanity, EvilVapor, and LeperCannibal
//The game is very beatable, but is hard, and depends on some luck. If it's to hard
//for testing purposes just use the cheates listed above.
//END NOTES.....


import java.util.*;


/**
DungeonAdventer. Contains main method, and all other methods
necessary to call constructors, and generally guid the play
of the game. 
<p><b>
Extra Credit:
</b><pre> 
I added two extra monster classes called DemonOfViolence and Hydra.
These are much tougher than normal monsters, and act as boss monsters
to guard specifically the crown pieces. 
I have given all Monsters unique special attacks with a 30% chance to use them each turn.
I added a fourth hero class called Paladin.
I added a hidden objective item in the dungeon called GoldenClaw. If the hero find this item,
then wins while carrying it he gets an alternate ending to the game.
I added speed potions, extra lives, weapon upgrades, and gold.
I added a buy items menu to buy weapon upgrades, health potions, speed potions, and extra lives, with gold.
Every consecutive playthrough of the game the hero gets 100 gold at the end.
I added a thief special ability to steal gold, and potions off of monsters.
I added a second thief ability called Gore that kills instantly, but has a low(10%) chance of success.
I added a Sorceress special ability called summon lighting. It does 
high damage, and is gauarenteed hit, but deals 25% of the damage to the Hero too.
I added a Sorceress perk, giving the Sorceress a 50% chance to levitate over pits, and take no damage.
I added a Warrior special ability called Blood lust,it deals high damage, 
and restores a small amount of health to the Warrior, but misses alot.
I added a Paladin Special ability called Smite that deals high damage, but misses.
I added a second Paladin special ability to heal instead of using a potion.
Note:
Special abilitys are not copies of one another. They all have unique descriptions, chance to hit
and damage.
End Note....
</pre><b>  
History: 
</b><pre> 
Created 4/26/2012, No Changes made since.
</pre>  
@author	Brandon Fowler 
*/

public class DungeonAdventure
{	

	//Keeps track of total Gold Player had between playthroughs
	public static int Gold = 0;
	
	
	
	/**
	Main for DungeonAdventure. Calls methods from all associated class file to run game.
	Also, guids the functions of the game, game menu, and combat.   
	<p><b>
	Extra Credit: 
	</b><pre> 
	N/A
	</pre><b>  
	History: 
	</b><pre> 
	Created 4/26/2012, No Changes made since.
	</pre>  
	@author	Brandon Fowler 
	*/
	
	public static void main(String []args)
	{
		//Loop keeps main going, through as many play throughs as the player wants
		String Ending = "Yes";
		while(Ending.compareTo("Yes")==0)
		{
			//Menu
			System.out.println("Welcome adventurer, to the Temple of Violence!");
			System.out.println("Home to insane cultists, cannibals, and unspeakably evil monsters"); 
			System.out.println("The rules are simple. You can choose to move North, South,");
			System.out.println("East or West, to explore. Your hero will automatically pick up");
			System.out.println("items. You will start at the entrance of the Dungeon.");
			System.out.println("Every time you make an action, a diplay of the room will show on the screen.");
			System.out.println("*'s are walls you can't go through. Doors look like | or - .");
			System.out.println("Your goal is to find the exit of the dungeon, but first you must");
			System.out.println("find both of the pieces of the Crown of Coding before you can");
			System.out.println("leave. Remeber the commands are Caps sensetive. Good luck hero!");
		
			//Gets hero name
			System.out.println();
			System.out.print("Enter the name of your hero to begin: ");
			Scanner kb = new Scanner(System.in);
			String Name = kb.next();
			
			//Makes a generic hero to initialize Adventurer
			String Class = "";
			Hero Adventurer = new Warrior("Generic");
		
			//Gets Hero class choice and initializes it
			while (Class.compareTo("1")!=0 && Class.compareTo("2")!=0 && Class.compareTo("3")!=0 && Class.compareTo("4")!=0)
			{
				kb = new Scanner(System.in);
				System.out.println();
				System.out.println("What type of hero would you like to be?(type the number)");
				System.out.println();
				System.out.println("1. Warrior (High health, Medium sword damage, Medium Speed, Medium defense)");
				System.out.println("2. Sorceress( Low health, High spell damage, Medium Speed, low defense)");
				System.out.println("3. Thief ( Medium health, Low dagger damage, High speed, High Defense)");
				System.out.println("4. Paladin ( Medium health, Medium hammer damage, Medium speed, Healing, High Defense)");
				System.out.println();
				System.out.print("Make your choice: ");
				Class = kb.next();
			
				if (Class.compareTo("1")==0)
				{
					Adventurer = new Warrior(Name);
				}
				else if (Class.compareTo("2")==0)
				{
					Adventurer = new Sorceress(Name);
				}
				else if (Class.compareTo("3")==0)
				{
					Adventurer = new Thief(Name);
				}
				else if (Class.compareTo("4")==0)
				{
					Adventurer = new Paladin(Name);
				}
				else
				{
					System.out.println();
					System.out.println("Not an option try again!");
				}

	
			}
			
			//Calls methods to set up entire dungeon
			Adventurer.Gold = Gold;
			Dungeon TheLabrynth = new Dungeon();
			TheLabrynth.makeRooms();
			TheLabrynth.makeDoors();
			TheLabrynth.setMonsters();
			TheLabrynth.setEntranceAndHero();
			TheLabrynth.setExit();
			TheLabrynth.setCrown1();
			TheLabrynth.setCrown2();
			TheLabrynth.setGoldenClaw();
			
			//Calls main game menu
			choiceMenu(TheLabrynth,Adventurer);
			
			//After game end, gets user decision to play another or not
			System.out.println("Would you like to play again?");
			System.out.print("Type Yes or No: ");
			Scanner kb2 = new Scanner(System.in);
			Ending = kb.next();
			while (Ending.compareTo("Yes")!=0 && Ending.compareTo("No")!=0)
			{	
				System.out.println();
				System.out.print("Bad Entery. Please enter only Yes or No: ");
				kb2 = new Scanner(System.in);
				Ending = kb2.next();
			}
			
			//Keeps gold current through consecutive play throughs, and adds some 
			Gold = Adventurer.Gold+100;
		}
	}
	
	/**
	choiceMenu Method. Prints out player choices, gets user input, then
	exicutes players choice by calling methods, or calculating directly when simple. 
 	@param       Dungeon,Hero
 	@return	void - Does not return a value
	 */ 
	public static void choiceMenu(Dungeon Labrynth,Hero Adventurer)
	{
		//Gets start position
		int row = Labrynth.StartRow;
		int col = Labrynth.StartCol;
		
		//Gets user action decision
		String choice;
		System.out.println();
		System.out.println("------------------------");
		System.out.println();
		System.out.println("You have entered the Temple!");
		System.out.println();
		System.out.println("Room you have entered");
		System.out.print(Labrynth.HeroPosition);
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("You can move in a direction(type the direction you want to move. Example: North)");
		System.out.println("You can use a health potion if you have one(type Health)");
		System.out.println("You can use a speed potion if you have one(type Speed)");
		System.out.println("You can view your hero's stats, and inventory(type Hero)");
		System.out.println("You can buy stuff(type Buy)");
		Scanner kb = new Scanner(System.in);
		System.out.print("Make your choice: ");
		choice = kb.next();
	

		//Exicutes Choice
		while(choice.compareTo("End")!= 0)
		{
			int count = 0;
			
			if(count < 1)
			{
				System.out.println();
				System.out.println("------------------------");
				System.out.println();
			}

			
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
			else if(choice.compareTo("Gold")==0)
			{
				Adventurer.Gold = Adventurer.Gold+500;
			}
			else if(choice.compareTo("Health")==0)
			{
				if (Adventurer.HPotions > 0)
				{
					System.out.println("Gulp, some HP restored");
					Adventurer.healthPotion();
					Adventurer.HPotions--;
				}
				else
				{
					System.out.println("You have no potions sorry.");
				}
			}
			else if(choice.compareTo("Speed")==0)
			{
				if(Adventurer.SpeedPotion > 0)
				{
					Adventurer.AttackSpeed = Adventurer.AttackSpeed+1;
					Adventurer.SpeedPotion = Adventurer.SpeedPotion-1;
					System.out.println("You feel faster!(Attack speed raised)");
				}
				else
				{
					System.out.println("You don't have any speed potions.");
				}
			}
			else if (choice.compareTo("Hero")==0)
			{
				System.out.print(Adventurer);
				System.out.println();
			}
			else if(choice.compareTo("Buy")==0)
			{
				System.out.println("You gold: "+Adventurer.Gold);
				System.out.println("What would you like to buy(type the number)?");
				System.out.println("1.Weapon upgrade, 50 gold");
				System.out.println("2.Health potion, 50 gold");
				System.out.println("3.Speed potion, 50 gold");
				System.out.println("4.Extra life, 100 gold(on death come back to life with 300 HP");
				System.out.print("Make your choice: ");
				
				Scanner kb3 = new Scanner(System.in);
				String BuyChoice = kb.next();
				if(BuyChoice.compareTo("1")==0)
				{
					if(Adventurer.Gold >= 50)
					{
						Adventurer.MaxDmg = Adventurer.MaxDmg+10;
						Adventurer.MinDmg = Adventurer.MinDmg+10;
						Adventurer.Gold = Adventurer.Gold-50;
						Gold = Gold-50;
						System.out.println("You have upgraded you weapon. Now you will do more damage!");
					}
					else
					{
						System.out.println("You don't have enough gold for that!");
					}
				}
				else if(BuyChoice.compareTo("2")==0)
				{
					if(Adventurer.Gold >= 50)
					{
						Adventurer.HPotions = Adventurer.HPotions+1;
						Adventurer.Gold = Adventurer.Gold-50;
						Gold = Gold-50;
						System.out.println("You have bought a health potion.");
					}
					else
					{
						System.out.println("You don't have enough gold for that!");
					}

				}
				else if(BuyChoice.compareTo("3")==0)
				{
					if(Adventurer.Gold >= 50)
					{
						Adventurer.SpeedPotion = Adventurer.SpeedPotion+1;
						Adventurer.Gold = Adventurer.Gold-50;
						Gold = Gold-50;
						System.out.println("You have bought a Speed potion.");
					}
					else
					{
						System.out.println("You don't have enough gold for that!");
					}

				}
				else if(BuyChoice.compareTo("4")==0)
				{
					if(Adventurer.Gold >= 50)
					{
						Adventurer.ExtraLife = Adventurer.ExtraLife+1;
						Adventurer.Gold = Adventurer.Gold-50;
						Gold = Gold-50;
						System.out.println("You have bought an extra life");
					}
					else
					{
						System.out.println("You don't have enough gold for that!");
					}

				}
				else
				{
					System.out.println("Not an option. Try again.");
				}
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
			
			
			//Checks Room For items, pits, bosses, and game win
			if (Labrynth.Dungeon[row][col].HealthP == 1 && Labrynth.Dungeon[row][col].Pit == 1)
			{
				System.out.println("YOU FOUND A HEALTH POTION!!!!!!");
				Adventurer.HPotions++;
				Labrynth.Dungeon[row][col].HealthP--;
				if(Adventurer.S == 1)
				{
					if(Math.random() < .5)
					{
						System.out.println("There is a pit in this room, but you quickly cast a spell to levitate over it");
					}
					else
					{
						System.out.println("Ouch you fell in a pit!");
						Adventurer.pitDecrease();
					}
				}
				else
				{
					System.out.println("Ouch you fell in a pit!");
					Adventurer.pitDecrease();
				}

			}
			else if (Labrynth.Dungeon[row][col].HealthP == 1)
			{
				System.out.println("YOU FOUND A HEALTH POTION!!!!!!");
				Adventurer.HPotions++;
				Labrynth.Dungeon[row][col].HealthP--;
			}
			else if(Labrynth.Dungeon[row][col].Pit == 1)
			{
				if(Adventurer.S == 1)
				{
					if(Math.random() < .5)
					{
						System.out.println("There is a pit in this room, but you quickly cast a spell to levitate over it");
					}
					else
					{
						System.out.println("Ouch you fell in a pit!");
						Adventurer.pitDecrease();
					}
				}
				else
				{
					System.out.println("Ouch you fell in a pit!");
					Adventurer.pitDecrease();
				}

			}			
			else if(Labrynth.Dungeon[row][col].GoldenClaw == 1)
			{
				System.out.println("You Found A Strange Golden Claw. Woots Extra Treasure!");
				Adventurer.GoldenClaw++;
				Labrynth.Dungeon[row][col].GoldenClaw--;
			} 
			else if(Labrynth.Dungeon[row][col].CrownP1 == 1)
			{
				System.out.println("YOU FOUND A CROWN PIECE BUT THERE IS A HYDRA GUARDING IT!!!!!!!!");
				Monster Hydra = new Hydra();
				
				int Turns = Adventurer.AttackSpeed/Hydra.AttackSpeed;
				
				while (Hydra.HP > 0 && Adventurer.HP > 0)
				{
					for (int Tcount = 0; Tcount < Turns; Tcount++)
					{
						Adventurer.attack(Hydra);
						
						if (Hydra.HP > 0 && Math.random() < Hydra.HealChance)
						{
							System.out.println("The Hydra heals itself some.");
							Hydra.heal();
						}
						
					}
					
					if (Hydra.HP <= 0)
					{
						System.out.println("Hydra dies");
					}
					else
					{
						Hydra.attack(Adventurer);
					}
				}
				
				if (Adventurer.HP > 0)
				{
					Adventurer.CrownP++;
					Labrynth.Dungeon[row][col].CrownP1--;
				}
				
				if (Hydra.HP <=0)
				{
					Labrynth.Dungeon[row][col].Hydra = 0;
				}
			}
			else if(Labrynth.Dungeon[row][col].CrownP2 == 1)
			{
				System.out.println("YOU FOUND A CROWN PIECE BUT THERE IS A DEMON OF VIOLENCE GUARDING IT!!!!!!!!");
				Monster Demon = new DemonOfViolence();
				
				int Turns = Adventurer.AttackSpeed/Demon.AttackSpeed;
				
				while (Demon.HP > 0 && Adventurer.HP > 0)
				{
					for (int Tcount = 0; Tcount < Turns; Tcount++)
					{
						Adventurer.attack(Demon);
						
						if (Demon.HP > 0 && Math.random() < Demon.HealChance)
						{
							System.out.println("The Demon of Violence heals itself some.");
							Demon.heal();
						}
						
					}
					
					if (Demon.HP <= 0)
					{
						System.out.println("Demon of Violence dies");
					}
					else
					{
						Demon.attack(Adventurer);
					}
				}
				
				if (Adventurer.HP > 0)
				{
					Adventurer.CrownP++;
					Labrynth.Dungeon[row][col].CrownP2--;
				}
				
				if(Demon.HP <= 0)
				{
					Labrynth.Dungeon[row][col].DemonOfViolence = 0;
				}
			}
			else if(Labrynth.Dungeon[row][col].Exit == 1 && Adventurer.CrownP == 2 && Adventurer.GoldenClaw == 1)
			{
				System.out.print(Labrynth);
				System.out.println();
				System.out.println("You Have Survived And Claimed Your Prize.");
				System.out.println("You Have Many Scars, But Fame And Fortune Are Yours!");
				System.out.println("You Return Home With Your Prize, Planning To Live");
				System.out.println("A Long And Luxerious Life.");
				System.out.println("The Golden Claw You Found Turns Out To Be A Charm");
				System.out.println("That Forces People To Obey Your Commands, No Matter What!");
				System.out.println("You Use It To Sucure World Domination, and Under");
				System.out.println("Your Leadership, Scientists Direct Reaserch Towards Long Life.");
				System.out.println("After A Couple Years Of Research, You Develop The Cure For Old Age.");
				System.out.println("Gratz! Now You Get To Rule The World Forever!"); 
				System.out.println();
				System.out.println("Awarded 100 gold");				
				choice = "End";
			}
			else if(Labrynth.Dungeon[row][col].Exit == 1 && Adventurer.CrownP == 2)
			{
				System.out.print(Labrynth);
				System.out.println();
				System.out.println("You Have Survived And Claimed Your Prize.");
				System.out.println("You Have Many Scars, But Fame And Fortune Are Yours!");
				System.out.println("You Return Home With Your Prize Planning To Live");
				System.out.println("A Long And Luxerious Life.");
				System.out.print("Yet Unrest Follws You Like A Shadow. Maybe One Day");
				System.out.println("You Will Return To The Temple Of Violence To Seek");
				System.out.println("Out What Other Treasures It May Have.");
				System.out.println();
				System.out.println("Awarded 100 gold");
				choice = "End";
			}
			else if(Labrynth.Dungeon[row][col].Exit == 1)
			{
				System.out.println("You found the exit, but you need both crown pieces first!");
			}
			else
			{
				System.out.println("The room has no items");
			}
			
			
			
			//Checks Room For Monsters and controls fights
			if(Labrynth.Dungeon[row][col].ChildOfInsanity == 1)
			{
				System.out.println("There's a Child Of Insanity in here! Fight for your life!");
				
				Monster Child = new ChildOfInsanity();
				
				int Turns = Adventurer.AttackSpeed/Child.AttackSpeed;
				
				while (Child.HP > 0 && Adventurer.HP > 0)
				{
					for (int Tcount = 0; Tcount < Turns; Tcount++)
					{
						Adventurer.attack(Child);
						
						if (Child.HP > 0 && Math.random() < Child.HealChance)
						{
							System.out.println("The Child of Insanity heals iteslf some");
							Child.heal();
						}
						
					}
					
					if (Child.HP <= 0)
					{
						System.out.println("Child of Insanity dies");
					}
					else
					{
						Child.attack(Adventurer);
					}
				}
				if(Child.HP <=0)
				{
					Labrynth.Dungeon[row][col].ChildOfInsanity = 0;
				}
			}
			if(Labrynth.Dungeon[row][col].LeperCannibal == 1)
			{
				System.out.println("There's a Leper Cannibal in here! Fight for you life!");
				
				Monster Leper = new LeperCannibal();
				
				int Turns = Adventurer.AttackSpeed/Leper.AttackSpeed;
				
				while (Leper.HP > 0 && Adventurer.HP > 0)
				{
					for (int Tcount = 0; Tcount < Turns; Tcount++)
					{
						Adventurer.attack(Leper);
						
						if (Leper.HP > 0 && Math.random() < Leper.HealChance)
						{
							System.out.println("The Leper Cannibal heals itself some.");
							Leper.heal();
						}
						
					}
					
					if (Leper.HP <= 0)
					{
						System.out.println("Leper Cannibal dies");
					}
					else
					{
						Leper.attack(Adventurer);
					}
				}
				if(Leper.HP <=0)
				{
					Labrynth.Dungeon[row][col].LeperCannibal = 0;
				}
			}
			if(Labrynth.Dungeon[row][col].EvilVapor == 1)
			{
				System.out.println("There's an Evil Vapor in here! Fight for your life!");
				
				Monster Vapor = new EvilVapor();
				
				int Turns = Adventurer.AttackSpeed/Vapor.AttackSpeed;
				
				while (Vapor.HP > 0 && Adventurer.HP > 0)
				{
					for (int Tcount = 0; Tcount < Turns; Tcount++)
					{
						Adventurer.attack(Vapor);
						
						if (Vapor.HP > 0 && Math.random() < Vapor.HealChance)
						{
							System.out.println("The Evil Vapor heals itself some.");
							Vapor.heal();
						}
						
					}
					
					if (Vapor.HP <= 0)
					{
						System.out.println("Evil Vapor dies");
					}
					else
					{
						Vapor.attack(Adventurer);
					}
				}
				if(Vapor.HP <=0)
				{
					Labrynth.Dungeon[row][col].EvilVapor = 0;
				}
			}
			
			
			//Checks Hero's HP
			if(Adventurer.HP <= 0)
			{
				if(Adventurer.ExtraLife > 0)
				{
					Adventurer.HP = 300;
					Adventurer.ExtraLife = Adventurer.ExtraLife-1;
					System.out.println("You have died! Extra life used.");
					row = Labrynth.StartRow;
					col = Labrynth.StartCol;
					Labrynth.HeroPosition = Labrynth.Dungeon[row][col];
				}
				else
				{
					System.out.println();
					System.out.println("---------------------");
					System.out.println();
					System.out.print(Labrynth);
					System.out.println();
					System.out.println("Game over your dead!");
					System.out.println("Guess you aren't cut out for this stuff.");
					System.out.println("Here's 100 gold. Maybe you should try again.");
					choice = "End";
				}
			}
			
						
			
			//Checks To See If Game Is Over
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
				System.out.println("You can use a speed potion if you have one(type Speed)");
				System.out.println("You can view your hero's stats, and inventory(type Hero)");
				System.out.println("You can buy stuff(type Buy)");
				kb = new Scanner(System.in);
				System.out.print("Make your choice: ");
				choice = kb.next();
				System.out.println();
				System.out.println("------------------------");
				System.out.println();
			}
			count++;
		
		}
			
	}
	
}