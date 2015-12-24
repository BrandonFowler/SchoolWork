//Brandon Fowler
//CSCD454
//Assignment 3

//Class used as a decorator, to add Ruffles cost and description to tree
public class Ruffles extends Decorator{
	
	private double cost = 1;
	
	public Ruffles(Tree tree){
		super(tree);
	}
	
	@Override
	public double cost(){
		return super.cost()+this.cost;
	}
	
	@Override
	public String description() {
        return super.description()+", Ruffles";
    }
}
