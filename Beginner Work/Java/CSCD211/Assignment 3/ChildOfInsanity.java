//Creator: Brandon Fowler
//ChildOfInsanity.java(Part of Assignment 3)
//Class:CSCD 211


import java.math.*;
import java.util.*;

/**
ChildOfInsanity Class. Contains all methodes needed
To Create a Monster object of ChildOfInsanity   
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



public class ChildOfInsanity extends Monster
{
	public ChildOfInsanity()
	{
		super();
		this.HP = 50;
		this.AttackSpeed = 4;
		this.HitChance = .8;
		this.MinDmg = 15;
		this.MaxDmg = 30;
		this.HealChance = .4;
		this.MinHeal = 20;
		this.MaxHeal = 40;
	} 
	
	/**
	attack Method. Overrides attack method inherited from parent class. 
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
				int result = gen.nextInt(MaxDmg-MinDmg+1)+MinDmg+15;
				System.out.println("The Child of Insanity laughs maniacally, then stabs");
				System.out.println("his dagger between your ribs, critically wounding you.");
				System.out.println("You take "+result+" damage to you health");
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
				System.out.println("The Child Of Insanity stabs you with his dagger.");
				System.out.println("You take "+result+" damage to your health.");
				Character.HP = Character.HP-result;
				System.out.println("Your HP left: "+Character.HP);
				System.out.println();
			}
		}
	}
}
