//Brandon Fowler
//CSCD454
//Assignment 3

//Decorator to implement the tree interface, and provide
//availibility of access for decorations
public class Decorator implements Tree {

		private Tree decoratedTree;
		
		public Decorator(Tree tree){
			this.decoratedTree = tree;
		}
		
		public double cost(){
			return this.decoratedTree.cost();
		}
		
		public String description() {
	        return this.decoratedTree.description();
	    }
}
