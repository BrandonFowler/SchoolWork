
//Team: Larfleeze
//Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
//Assignment 2

import java.util.Observable;

/**
EyeOfSauron class:
Inherits Observable behaviors.
Contains Observable information about hobbits, elves, dwarves, and men.
Allows for change of observable information and notifies Observers.
**/
public class EyeOfSauron extends Observable{
	
	private int hobbits;
	private int elves;
	private int dwarves;
	private int men;
	
	//Default Constructor
	public EyeOfSauron(){
		this.hobbits = 0;
		this.elves = 0;
		this.dwarves = 0;
		this.men = 0;
	}
	
	//Changes Observable info, and notifies Observers
	public void setEnemies(int hobbits, int elves, int dwarves, int men){
		this.hobbits = hobbits;
		this.elves = elves;
		this.dwarves = dwarves;
		this.men = men;
      System.out.println();
      System.out.println("Sauron has spotted "+hobbits+" Hobbits, "+elves+" elves, "
                         +dwarves+" dwarves, and "+men+" men.");
		setChanged();
		notifyObservers();
	}
	
	//Get methods for use by Observers
	public int getHobbits(){
		return this.hobbits;
	}
	public int getElves(){
		return this.elves;
	}
	public int getDwarves(){
		return this.dwarves;
	}
	public int getMen(){
		return this.men;
	}
}