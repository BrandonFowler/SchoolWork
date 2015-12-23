//Creator: Brandon Fowler
//Hero.java(Part of Assignment 3)
//Class:CSCD 211

import java.util.*;

/**
Hero Class. Contains all methods needed to create a 
generic object of type hero.Abstract 
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

public abstract class Hero extends DungeonCharacter
{
	
	protected int HPotions;
	protected int VPotions;
	protected int CrownP;
	protected int GoldenClaw;
	protected int SpecialDmg;
	protected int Gold;
	protected int SpeedPotion;
	protected int ExtraLife;
	
	/**
	Hero Method. Constructs a Object of type Hero.  Calls Base Constructor
 	@param       None
 	@return		 None
	 */ 

	public Hero()
	{
		super();	
		this.HPotions = 0;
		this.VPotions = 0;
		this.CrownP = 0;
		this.GoldenClaw = 0;
		this.SpecialDmg = 0;
		this.Gold = 0;
		this.SpeedPotion = 0;
		this.ExtraLife = 0;
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
		System.out.println("1. Normal Attack");
		System.out.println("2. Special");
		System.out.println("3. Use a Health Potion");
		Scanner kb = new Scanner(System.in);
		String choice = kb.next();
		if (choice.compareTo("1")==0)
		{
			if (Math.random() < HitChance)
			{
				Random gen = new Random();
				int result = gen.nextInt(MaxDmg-MinDmg+1)+MinDmg;
				System.out.println("Hit dealing "+result+" damage");
				Character.HP = Character.HP-result;
			}
			else
			{
				System.out.println("Miss");
			}
		}
		else if (choice.compareTo("2")==0)
		{
			Character.HP = Character.HP-SpecialDmg;
			System.out.println(""+SpecialDmg+" Damage Dealth");
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
	}
	
	/**
	healthPotion Method. Simulates use of health potion. 
 	@param       None
 	@return	void - Does not return a value
	 */ 

	public void healthPotion()
	{
		Random gen = new Random();
		int result = gen.nextInt(11)+50;
		this.HP = this.HP+result;
	}
	
	/**
	pitDecrease Method. Simulates damage from falling into a pit. 
 	@param       None
 	@return	void - Does not return a value
	 */ 

	public void pitDecrease()
	{
		Random gen = new Random();
		int result = gen.nextInt(50)+1;
		this.HP = this.HP-result;
	}
	
	/**
	toString Method. Overrides toString method inherited from parent. 
 	@param       DungeonCharacter
 	@return	void - Does not return a value
	 */ 

	@Override
	public String toString()
	{
		String result;
		result = ""+this.Name+"\n"+"Your Hit Points: "+this.HP+"\n";
		result = result+"Your Extra Lives: "+this.ExtraLife+"\n";
		result = result+"Your Health Potions: "+this.HPotions+"\n";
		result = result+"Your Vision Potions: "+this.VPotions+"\n";
		result = result+"Your Speed Potions: "+this.SpeedPotion+"\n";
		result = result+"Your Crown Pieces: "+this.CrownP+"\n";
		result = result+"Other Items: "+this.GoldenClaw+"\n";
		result = result+"Gold: "+this.Gold+"\n";
		return result;
	}


}