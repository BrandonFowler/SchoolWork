//Brandon Fowler
//CSCD454
//Assignment 3

//Class used as a decorator, to add a Star cost and description to tree
public class Star extends Decorator{

	private double cost;
	private String description;
	
	public Star(Tree tree){
		super(tree);
		if(starTracker.checkStarCreated()){                //Check if tree already has a star
			System.out.println("Tree already has a star!");
			cost = 0;                                       //No addition to cost if tree already has a star
			description = "";                               //No addition to description if tree already has a star
		}
		else{                                              //If tree does not already have a star
			starTracker.setStarCreated();
			cost = 4;                                       //Add cost of star to tree
			description = ", a Star";                       //Add star description to tree
		}
	}
	
	@Override
	public double cost(){
		return super.cost()+this.cost;
	}
	
	@Override
	public String description() {
		return super.description()+this.description;
    }
}