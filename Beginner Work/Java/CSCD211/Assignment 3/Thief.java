//Creator: Brandon Fowler
//Thief.java(Part of Assignment 3)
//Class:CSCD 211


import java.math.*;
import java.util.*;

/**
Thief Class. Contains all of the methods needed to create
and manipulate a object of type Thief.  
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


public class Thief extends Hero
{
	/**
	Thief Method. Constructs a Object of type Thief. Calls Base Consructor. 
 	@param       None
 	@return		 None
	 */ 

	public Thief(String name)
	{
		super();
		this.Name = name;
		this.HP = 550;
		this.AttackSpeed = 6;
		this.HitChance = .9;
		this.MinDmg = 25;
		this.MaxDmg = 50;
		this.ChanceToBlock = .5;
	}
	
	/**
	attack Method. Overrides attack method inherited from parent. 
 	@param       DungeonCharacter
 	@return	void - Does not return a value
	 */ 

	@Override
	public void attack(DungeonCharacter Character)
	{
		System.out.println("What would you like to do(type the option number)?");
		System.out.println("1. Normal Attack(Medium damage)");
		System.out.println("2. Steal(Steal loot from your enemy!)");
		System.out.println("3. Gore(Instantly kills opponent, very low chance of success)");
		System.out.println("4. Use a Health Potion");
		System.out.print("Make your choice: ");
		Scanner kb = new Scanner(System.in);
		String choice = kb.next();
		if (choice.compareTo("1")==0)
		{
			if (Math.random() < HitChance)
			{
				Random gen = new Random();
				int result = gen.nextInt(MaxDmg-MinDmg+1)+MinDmg;
				System.out.println("You thrust your dagger, and deal "+result+" damage.");
				Character.HP = Character.HP-result;
			}
			else
			{
				System.out.println("You thrust your dagger and miss!");
			}
		}
		else if (choice.compareTo("2")==0)
		{
			if (Character.Item > 0)
			{
				if(Math.random() < .3)
				{
					this.HPotions++;
					Character.Item--;
					System.out.println("You stole a health potion!");
				}
				else if(Math.random() < .6)
				{
					this.SpeedPotion++;
					Character.Item--;
					System.out.println("You stole a Speed Potion!");
				}
				else if(Math.random() < .9)
				{
					Random gen = new Random();
					int result = gen.nextInt(31)+10;
					this.Gold = this.Gold+result;
					Character.Item--;
					System.out.println("You stole "+result+" gold!");
				}
				else
				{
					System.out.println("You were unable to steal anything!");
				}		
			}
			else
			{
				System.out.println("Enemy has nothing to steal!");
			}
		}
		else if (choice.compareTo("3")==0)
		{
			if(Math.random() < .1)
			{
				System.out.println("You shove your daggers into vital areas of your enemy causing mass damage");
				Character.HP = Character.HP-1000;
			}
			else
			{
				System.out.println("Gore failed!");
			}
		}
		else if (choice.compareTo("4")==0)
		{
			if (this.HPotions > 0)
			{	
				System.out.println("HP Raised Some");
				healthPotion();
				this.HPotions--;
			}
			else
			{
				System.out.println("You Have No Health Potions To Use!");
			}
		}
		else
		{
			System.out.println("You didn't choose a valid option. Try again!");
		}
		System.out.println();
	}
}