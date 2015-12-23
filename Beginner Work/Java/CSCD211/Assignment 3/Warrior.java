//Creator: Brandon Fowler
//Warrior.java(Part of Assignment 3)
//Class:CSCD 211


import java.math.*;
import java.util.*;

/**
Warrior Class. Contains all of the methods needed to create and
manipulate a object of type Warrior  
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

public class Warrior extends Hero
{
	/**
	Warrior Method. Constructs a object of type Warrior. Calls Base Constructor. 
 	@param       None
 	@return		 None
	 */ 

	public Warrior(String name)
	{
		super();
		this.Name = name;
		this.HP = 650;
		this.AttackSpeed = 4;
		this.HitChance = .8;
		this.MinDmg = 35;
		this.MaxDmg = 60;
		this.ChanceToBlock = .2;
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
		System.out.println("2. Blood Rage(High damage, heals hero for 10% of damage dealt to enemy, but more likley to miss)");
		System.out.println("3. Use a Health Potion");
		System.out.print("Make your choice: ");
		Scanner kb = new Scanner(System.in);
		String choice = kb.next();
		if (choice.compareTo("1")==0)
		{
			if (Math.random() < HitChance)
			{
				Random gen = new Random();
				int result = gen.nextInt(MaxDmg-MinDmg+1)+MinDmg;
				System.out.println("You thrust your sword, and deal "+result+" damage.");
				Character.HP = Character.HP-result;
			}
			else
			{
				System.out.println("You thrust your sword and miss!");
			}
		}
		else if (choice.compareTo("2")==0)
		{
			if (Math.random() <= .4)
			{
				Random gen = new Random();
				int result = gen.nextInt(101)+75;
				Character.HP = Character.HP-result;
				int result2 = result/10;
				this.HP = this.HP+result2;
				System.out.println("You swing your mighty sword, and rip into your opponent, and deal "+result+" damage.");
				System.out.println("Your blood lust invigorates you. Gain "+result2+"HP");
			}
			else
			{
				System.out.println("You swing your mighty sword, and miss!");
			}
		}
		else if (choice.compareTo("3")==0)
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