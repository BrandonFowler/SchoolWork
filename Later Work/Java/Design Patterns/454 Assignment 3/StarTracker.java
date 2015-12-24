//Brandon Fowler
//CSCD454
//Assignment 3

//Used by the tree interface as a static tracker
//to make sure only one star is added to a tree
public class StarTracker {
	
	private boolean starCreated;
	
   //Initialize to show star has not been added yet
	public StarTracker(){
		this.starCreated = false;
	}
	
   //Set value to show that a star has been added(used in star class)
	public void setStarCreated(){
		this.starCreated = true;
	}
	
   //Check if a star has been added(used in star class)
	public boolean checkStarCreated(){
		return this.starCreated;
	}
	
   //Used by tree classes to reset tracker
	public void starTrackerReset(){
		this.starCreated = false;
	}
}
