//Brandon Fowler
//CSCD454
//Assignment 3

//Class used as a decorator, to add Blue balls cost and description to tree
public class BlueBalls extends Decorator{

	private double cost = 2;
	
	public BlueBalls(Tree tree){
		super(tree);
	}
	
	@Override
	public double cost(){
		return super.cost()+this.cost;
	}
	
	@Override
	public String description() {
        return super.description()+", Blue Balls";
    }
}
