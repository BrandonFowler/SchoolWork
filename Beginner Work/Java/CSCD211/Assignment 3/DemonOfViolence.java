//Creator: Brandon Fowler
//DemonOfViolence.java(Part of Assignment 3)
//Class:CSCD 211

import java.math.*;
import java.util.*;

/**
DemonOfViolence Class. Contains all of the methods needed to create
and manipulate a object of type DemonOfViolence.  
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


public class DemonOfViolence extends Monster
{
	/**
	DemonOfViolence Method. Constructs an object of Type DemonOfViolence. Calls base consturctor. 
 	@param       None
 	@return		 None
	 */ 

	public DemonOfViolence()
	{
		super();
		this.HP = 400;
		this.AttackSpeed = 3;
		this.HitChance = .7;
		this.MinDmg = 40;
		this.MaxDmg = 65;
		this.HealChance = .3;
		this.MinHeal = 30;
		this.MaxHeal = 50;
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
				int result = gen.nextInt(MaxDmg-MinDmg+1)+MinDmg+55;
				System.out.println("The Demon of Violence howls with cruel laughter");
				System.out.println("as it summons a wall of flames that completely ingulfs you.");
				System.out.println("You struggle, and finally, escape the flames with some third degree burns!");
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
				System.out.println("The Demon of Violence lashes out with his whip, and strikes you.");
				System.out.println("You take "+result+" damage to your health.");
				Character.HP = Character.HP-result;
				System.out.println("Your HP left: "+Character.HP);
				System.out.println();
			}
		}
	}

}