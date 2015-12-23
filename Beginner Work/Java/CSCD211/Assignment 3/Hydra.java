//Creator: Brandon Fowler
//Hydra.java(Part of Assignment 3)
//Class:CSCD 211

import java.math.*;
import java.util.*;

/**
Hydra Class. Contains all of the methods needed to create
and manipulate a object of type Hydra.  
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


public class Hydra extends Monster
{
	/**
	Hydra Method. Constructs an object of Type Hydra. Calls Base Constructor.
 	@param       None
 	@return	 	 None
	 */ 

	public Hydra()
	{
		super();
		this.HP = 350;
		this.AttackSpeed = 3;
		this.HitChance = .8;
		this.MinDmg = 50;
		this.MaxDmg = 80;
		this.HealChance = .2;
		this.MinHeal = 20;
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
				int result = gen.nextInt(MaxDmg-MinDmg+1)+MinDmg+60;
				System.out.println("The Six Headed Hydra grabs you in it's claws, and tears at you with hundreds of teeth.");
				System.out.println("Your vision spins, as you lose blood, and struggle till you escape!");
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
				System.out.println("The Hyrda swipes at you with it's claws, tearing a new wound in your flesh.");
				System.out.println("You take "+result+" damage to your health.");
				Character.HP = Character.HP-result;
				System.out.println("Your HP left: "+Character.HP);
				System.out.println();
			}
		}
	}

}