//Brandon Fowler
//CSCD454
//Assignment 3

//Class used as a decorator, to add Ribbons cost and description to tree
public class Ribbons extends Decorator{

	private double cost = 2;
	
	public Ribbons(Tree tree){
		super(tree);
	}
	
	@Override
	public double cost(){
		return super.cost()+this.cost;
	}
	
	@Override
	public String description() {
        return super.description()+", Ribbons";
    }
}
