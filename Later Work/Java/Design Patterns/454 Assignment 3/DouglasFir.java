//Brandon Fowler
//CSCD454
//Assignment 3

//Class used by tree interface to add cost and description of Douglas Fir
public class DouglasFir implements Tree {

	private double cost = 15;
	
	public DouglasFir(){
		super();
		starTracker.starTrackerReset();
	}
	
	public double cost(){
		return this.cost;
	}
	
	public String description() {
        return "Douglas Fir tree decorated with";
    }
}
