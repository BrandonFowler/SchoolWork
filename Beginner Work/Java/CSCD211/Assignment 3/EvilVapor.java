//Creator: Brandon Fowler
//EvilVapor.java(Part of Assignment 3)
//Class:CSCD 211




import java.math.*;
import java.util.*;

/**
EvilVapor Class. Contains all methods need to
Create a Monster object of EvilVapor   
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

public class EvilVapor extends Monster
{
	public EvilVapor()
	{
		super();
		this.HP = 75;
		this.AttackSpeed = 3;
		this.HitChance = .8;
		this.MinDmg = 30;
		this.MaxDmg = 50;
		this.HealChance = .3;
		this.MinHeal = 30;
		this.MaxHeal = 50;
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
				int result = gen.nextInt(MaxDmg-MinDmg+1)+MinDmg+25;
				System.out.println("The Evil Vapor begins glowing green.");
				System.out.println("Your skin feels like it's on fire!");
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
				System.out.println("The Evil Vapor pokes you in a place ought not be poked.");
				System.out.println("You take "+result+" damage to your health.");
				Character.HP = Character.HP-result;
				System.out.println("Your HP left: "+Character.HP);
				System.out.println();
			}
		}
	}
}
