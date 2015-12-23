//Creator: Brandon Fowler
//Monster.java(Part of Assignment 3)
//Class:CSCD 211

import java.math.*;
import java.util.*;

/**
Monster Class. Contains all methods needed to create a generic
object of type monster.Abstract  
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


public abstract class Monster extends DungeonCharacter
{
	protected int AttackSpeed;
	protected int MaxHeal;
	protected int MinHeal;
	protected double HealChance;
	protected double HitChance;
	protected double SpecialChance;
	
	/**
	Monster Method. Constructs an Object of type Monster. Calls base constructor. 
 	@param       None
 	@return		 None
	 */ 

	public Monster()
	{
		super();
		this.MaxHeal = 0;
		this.MinHeal = 0;
		this.HealChance = 0.0;
		this.AttackSpeed = 0;
		this.HitChance = 0.0;
		this.SpecialChance = .3;
	}
	
	/**
	heal Method. Simulates monster healing. 
 	@param       None
 	@return	void - Does not return a value
	 */ 

	public void heal()
	{
		Random gen = new Random();
		int result = gen.nextInt(MaxHeal-MinHeal+1)+10;
		this.HP = this.HP+result;
	}

}