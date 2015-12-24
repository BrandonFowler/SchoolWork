
//Team: Larfleeze
//Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
//Assignment 2

import java.util.*;

/**
BadGuy class:
Inherits Observer behaviors.
Contains data necessary to identify Observer and Observed.
**/
public class BadGuy implements Observer {
	
	private EyeOfSauron eye;
	private String name;
	
	//Construct BadGuy with identification and Observed EyeOfSauron
	public BadGuy(EyeOfSauron eye, String name){
		this.eye = eye;
		this.name = name;
      this.eye.addObserver(this);
	}
	
	//Prints updated info when BadGuy is notified of a change
	public void update(Observable eye, Object obj){
		
		//If currently an observer, then print updated information
		if(this.eye == eye){
			System.out.println(""+name+" knows about "+this.eye.getHobbits()+
							   " hobbits, "+this.eye.getElves()+" Elves, "+
							   this.eye.getDwarves()+" Dwarves, and "+
							   this.eye.getMen()+" men.");
		}
	}
	
	//Stop Observing current EyeOfSauron
	public void defeated(){
      System.out.println();
      System.out.println(""+this.name+" has been defeated.");
      this.eye.deleteObserver(this);
		this.eye = null;
	}
}
