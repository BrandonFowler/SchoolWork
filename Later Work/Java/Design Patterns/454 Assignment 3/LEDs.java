//Brandon Fowler
//CSCD454
//Assignment 3

//Class used as a decorator, to add LEDs cost and description to tree
public class LEDs extends Decorator{

	private double cost = 10;
	
	public LEDs(Tree tree){
		super(tree);
	}
	
	@Override
	public double cost(){
		return super.cost()+this.cost;
	}
	
	@Override
	public String description() {
        return super.description()+", LEDs";
    }
}
