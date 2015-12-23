//Creator: Brandon Fowler
//Hero.java(Part of Assignment 2)
//Class:CSCD 211




import java.util.*;
import java.math.*;

//Hero Class
//Contains methods to create, and manipulate a hero
public class Hero
{
	private String Name;
	protected int HP;
	protected int HPotions;
	private int VPotions;
	protected int CrownP;
	
	//Constructs a Hero
	public Hero(String Name)
	{
		this.Name = Name;
		this.HP = healthGen();
		this.HPotions = 0;
		this.VPotions = 0;
		this.CrownP = 0;
	}
	
	//HealthGen Method
	//Generates amount of HP for hero
	public int healthGen()
	{
		Random gen = new Random();
		int result = gen.nextInt(26)+75;
		return result;
	}
	
	//PitDecrease Method
	//Decreases players health
	public void pitDecrease()
	{
		Random gen = new Random();
		int result = gen.nextInt(50)+1;
		this.HP = this.HP-result;
	}
	
	//HealthIncrease Method
	//Increases players health
	public void healthIncrease()
	{
		Random gen = new Random();
		int result = gen.nextInt(16)+15;
		this.HP = this.HP+result;
	}
	
	//ToString Method
	//Overrides toString Method
	@Override
	public String toString()
	{
		String result;
		result = ""+this.Name+"\n"+"Your Hit Points: "+this.HP+"\n";
		result = result+"Your Health Potions: "+this.HPotions+"\n";
		result = result+"Your Vision Potions: "+this.VPotions+"\n";
		result = result+"Your Crown Pieces: "+this.CrownP;
		return result;
	}
}