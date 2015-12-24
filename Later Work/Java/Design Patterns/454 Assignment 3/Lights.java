//Brandon Fowler
//CSCD454
//Assignment 3

//Class used as a decorator, to add Lights cost and description to tree
public class Lights extends Decorator{

	private double cost = 5;
	
	public Lights(Tree tree){
		super(tree);
	}
	
	@Override
	public double cost(){
		return super.cost()+this.cost;
	}
	
	@Override
	public String description() {
        return super.description()+", Lights";
    }
}
