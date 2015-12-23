//Creator: Brandon Fowler
//DungeonCharacter.java(Part of Assignment 3)
//Class:CSCD 211



import java.math.*;
import java.util.*;

/**
DungeonCharacter Class. Contains all methods required for any generic 
DungeonCharater object.Abstract  
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

public abstract class DungeonCharacter
{
	protected String Name;
	protected int HP;
	protected int AttackSpeed;
	protected int MaxDmg;
	protected int MinDmg;
	protected double HitChance;
	protected int Item;
	protected double ChanceToBlock;
	protected int S;
	
	/**
	DungeonCharcter Method. Constructs a Object of type DungeonCharacter. 
 	@param       None
 	@return		 None
	 */ 

	public DungeonCharacter()
	{
		this.Name = ("Dungeon Charcter");
		this.HP = 0;
		this.AttackSpeed = 0;
		this.MaxDmg = 0;
		this.MinDmg = 0;
		this.HitChance = 0.0;
		this.Item = 2;
		this.ChanceToBlock = 0.0;
		this.S = 0;
	}
	
	/**
	attack Method. Simulates a DungeonCharacter attacking another DungeonCharacter. 
 	@param       DungeonCharacter
 	@return	void - Does not return a value
	 */ 

	public void attack(DungeonCharacter Character)
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
	
}