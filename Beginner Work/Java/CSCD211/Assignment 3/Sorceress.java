//Creator: Brandon Fowler
//Sorceress.java(Part of Assignment 3)
//Class:CSCD 211


import java.math.*;
import java.util.*;

/**
Sorceress Class. Contains all of the methods needed to create
and manipulate a object of type Sorceress.  
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


public class Sorceress extends Hero
{
	/**
	Sorceress Method. Creates an Object of type Sorceress. Calls base Constructor. 
 	@param       None
 	@return		 None
	 */ 

	public Sorceress(String name)
	{
		super();
		this.Name = name;
		this.HP = 500;
		this.AttackSpeed = 5;
		this.HitChance = .7;
		this.MinDmg = 40;
		this.MaxDmg = 65;
		this.ChanceToBlock = .1;
		this.S = 1;
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
		System.out.println("2. Summon Lightning(High damage, guarenteed hit, but deal 25% damage to you too)");
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
				System.out.println("You cast a fire ball out of your staff, and deal "+result+" damage.");
				Character.HP = Character.HP-result;
			}
			else
			{
				System.out.println("You cast a fire ball out of your staff and miss!");
			}
		}
		else if (choice.compareTo("2")==0)
		{
		
			Random gen = new Random();
			int result = gen.nextInt(101)+125;
			Character.HP = Character.HP-result;
			int result2 = result/4;
			this.HP = this.HP-result2;
			System.out.println("You summon a bolt of lightning and deal "+result+" damage to your enemy.");
			System.out.println("Lightning does "+result/4+" damage to you.");
		
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