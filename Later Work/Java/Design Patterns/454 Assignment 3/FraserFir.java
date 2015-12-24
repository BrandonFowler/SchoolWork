//Brandon Fowler
//CSCD454
//Assignment 3

//Class used by tree interface to add cost and description of Fraser Fir
public class FraserFir implements Tree{
	
	private double cost = 12;
	
	public FraserFir(){
		super();
		starTracker.starTrackerReset();
	}
	
	public double cost(){
		return this.cost;
	}
	
	public String description() {
        return "Fraser Fir tree decorated with";
    }
}
