//Creator: Brandon Fowler
//LeperCannibal.java(Part of Assignment 3)
//Class:CSCD 211

import java.math.*;
import java.util.*;

/**
LeperCannibal Class. Contains all of the methods needed to create
and manipulate a object of type LeperCannibal.  
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


public class LeperCannibal extends Monster
{
	/**
	LeperCannibal Method. Creates an Object of type LeperCannibal. Calls base constructor. 
 	@param       None
 	@return		 None
	 */ 

	public LeperCannibal()
	{
		super();
		this.HP = 100;
		this.AttackSpeed = 2;
		this.HitChance = .6;
		this.MinDmg = 15;
		this.MaxDmg = 30;
		this.HealChance = .1;
		this.MinHeal = 30;
		this.MaxHeal = 60;
	} 
	
	/**
	attack Method. Overrides attack method inherited from parent. 
 	@param       DungeonCharacter
 	@return	void - Does not return a value
	 */ 

	@Override
	public void attack(DungeonCharacter Character)
	{
		if (Math.random() < this.SpecialChance)
		{
			if(Math.random() < Character.ChanceToBlock)
			{
				System.out.println("Your enemy tried to attack, but you managed to block it");
				System.out.println();
			}
			else
			{
				Random gen = new Random();
				int result = gen.nextInt(MaxDmg-MinDmg+1)+MinDmg+35;
				System.out.println("The Leper Cannibal bites you on the neck.");
				System.out.println("Your vision spins, as you lose some blood!");
				System.out.println("You take "+result+" critical damage to you health");
				Character.HP = Character.HP-result;
				System.out.println("Your HP left: "+Character.HP);
				System.out.println();
			}
		}
		else
		{
			if(Math.random() < Character.ChanceToBlock)
			{
				System.out.println("Your enemy tried to attack, but you managed to block it");
				System.out.println();
			}
			else
			{
				Random gen = new Random();
				int result = gen.nextInt(MaxDmg-MinDmg+1)+MinDmg;
				System.out.println("The Leper Cannibal punches you.");
				System.out.println("You take "+result+" damage to your health.");
				Character.HP = Character.HP-result;
				System.out.println("Your HP left: "+Character.HP);
				System.out.println();
			}
		}
	}

}