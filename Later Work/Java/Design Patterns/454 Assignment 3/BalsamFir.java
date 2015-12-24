//Brandon Fowler
//CSCD454
//Assignment 3

//Class used by tree interface to add cost and description of Balsam Fir
public class BalsamFir implements Tree{
	
	private double cost = 5;

	public BalsamFir(){
		super();
		starTracker.starTrackerReset();
	}
	
	public double cost(){
		return this.cost;
	}
	
	public String description() {
        return "Balsam Fir tree decorated with";
    }
}
