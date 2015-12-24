//Brandon Fowler
//CSCD454
//Assignment 3

//Tester for Assignment 3
//Contains main, and tests decorator functionality
public class TreeDecoratorTest {

	public static void main(String[] args){
		
		System.out.println("Test Senario given in assignmnet instructions:");
		Tree mytree = new BlueSpruce();
		mytree = new Star(mytree);
		mytree = new Ruffles(mytree);
		mytree = new Star(mytree);
		mytree = new Ruffles(mytree);
		printtree(mytree);
		
		System.out.println();
		System.out.println("Test Balsam Fir with one of everything:");
	   mytree = new BalsamFir();
		mytree = new Ruffles(mytree);
		mytree = new BlueBalls(mytree);
		mytree = new SilverBalls(mytree);
		mytree = new RedBalls(mytree);
		mytree = new Lights(mytree);
		mytree = new LEDs(mytree);
		mytree = new Ribbons(mytree);
		mytree = new Star(mytree);
      printtree(mytree);
		
		System.out.println();
		System.out.println("Test Douglas Fir:");
		mytree = new DouglasFir();
		mytree = new Ruffles(mytree);
		mytree = new Lights(mytree);
		mytree = new Star(mytree);
		mytree = new SilverBalls(mytree);
		printtree(mytree);
		
		System.out.println();
		System.out.println("Test Fraser Fir And Show Only One Star Allowed:");
		mytree = new FraserFir();
		mytree = new Star(mytree);
		mytree = new BlueBalls(mytree);
		mytree = new Lights(mytree);
		mytree = new Star(mytree);
		mytree = new Ribbons(mytree);
		mytree = new Star(mytree);
		printtree(mytree);
	}
	
	public static void printtree(Tree tree){
		System.out.println(tree.description()+" costs $"+String.format("%.2f",tree.cost()));
	}
}