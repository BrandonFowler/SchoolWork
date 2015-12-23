//Creator: Brandon Fowler
//Paladin.java(Part of Assignment 3)
//Class:CSCD 211


import java.math.*;
import java.util.*;

/**
Paladin Class. Contains all of the methods needed to create
and manipulate a object of type Paladin.  
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


public class Paladin extends Hero
{
	/**
	Paladin Method. Creates an object of type Paladin. Calls base constructor. 
 	@param       None
 	@return	 	 None
	 */ 

	public Paladin(String name)
	{
		super();
		this.Name = name;
		this.HP = 550;
		this.AttackSpeed = 5;
		this.HitChance = .8;
		this.MinDmg = 30;
		this.MaxDmg = 55;
		this.ChanceToBlock = .4;
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
		System.out.println("2. Smite(High damage, target can resist)");
		System.out.println("3. Holy Heal(Heal with holy powers)");
		System.out.print("Make your choice: ");
		Scanner kb = new Scanner(System.in);
		String choice = kb.next();
		if (choice.compareTo("1")==0)
		{
			if (Math.random() < HitChance)
			{
				Random gen = new Random();
				int result = gen.nextInt(MaxDmg-MinDmg+1)+MinDmg;
				System.out.println("You swing your mighty hammer, and deal "+result+" damage.");
				Character.HP = Character.HP-result;
			}
			else
			{
				System.out.println("You swing your hammer and miss!");
			}
		}
		else if (choice.compareTo("2")==0)
		{
			if (Math.random() <= .5)
			{
				Random gen = new Random();
				int result = gen.nextInt(101)+80;
				Character.HP = Character.HP-result;
				System.out.println("You channel a holy beam of searing light into your enemy, and deal "+result+" damage.");
			}
			else
			{
				System.out.println("You channel a holy beam of searing light at your enemy, but it resists the damage!");
			}
		}
		else if (choice.compareTo("3")==0)
		{
			System.out.println("HP Raised Some");
			healthPotion();
		}
		else
		{
			System.out.println("You didn't choose a valid option. Try again!");
		}
		System.out.println();
	}

}