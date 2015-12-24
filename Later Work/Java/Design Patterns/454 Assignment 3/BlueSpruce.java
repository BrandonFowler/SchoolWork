//Brandon Fowler
//CSCD454
//Assignment 3

//Class used by tree interface to add cost and description of Blue Spruce
public class BlueSpruce implements Tree{
	
	private double cost = 20;
	
	public BlueSpruce(){
		super();
		starTracker.starTrackerReset();
	}
	
	public double cost(){
		return this.cost;
	}
	
	public String description() {
        return "Blue Spruce tree decorated with";
    }
}
