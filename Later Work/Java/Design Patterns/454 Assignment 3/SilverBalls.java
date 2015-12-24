//Brandon Fowler
//CSCD454
//Assignment 3

//Class used as a decorator, to add Silver balls cost and description to tree
public class SilverBalls extends Decorator{

	private double cost = 3;
	
	public SilverBalls(Tree tree){
		super(tree);
	}
	
	@Override
	public double cost(){
		return super.cost()+this.cost;
	}
	
	@Override
	public String description() {
        return super.description()+", Silver Balls";
    }
}
