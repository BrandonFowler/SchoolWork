//Brandon Fowler
//CSCD454
//Assignment 3

//Class used as a decorator, to add Red balls cost and description to tree
public class RedBalls extends Decorator{
	
	private double cost = 1;
	
	public RedBalls(Tree tree){
		super(tree);
	}
	
	@Override
	public double cost(){
		return super.cost()+this.cost;
	}
	
	@Override
	public String description() {
        return super.description()+", Red Balls";
    }
}
