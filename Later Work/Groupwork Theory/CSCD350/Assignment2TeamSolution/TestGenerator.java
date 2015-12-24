
 /**
 Author: Brandon Fowler
 Name: Tester.java
 Description: Generates test cases for mine mine sweeper program
 */
public class TestGenerator {
	
	//main
	public static void main(String[] args) {

		//Initialize Variables
		char[][] testArray = new char[100][100];
		int i;
		int j;
		int t;
		int random;
		int row;
		int col;
		
		// Print number of rows and columns then generate a
		//2D array with randomized mines(100 by 100 edge case)
		System.out.println( "100 100");
		for(i = 0; i < 100; i++){
			for(j = 0; j < 100; j++){
				random = (int) Math.floor(Math.random()*100+1);
				if (random < 21)
					testArray[i][j] = '*';
				else
					testArray[i][j] = '.';
				System.out.print(testArray[i][j]);
			}
			System.out.println();
		}
		
		// Print number of rows and columns then generate a
		//2D array with randomized mines(1 by 100 edge case)
		System.out.println( "1 100");
		for(i = 0; i < 1; i++){
			for(j = 0; j < 100; j++){
				random = (int) Math.floor(Math.random()*100+1);
				if (random < 21)
					testArray[i][j] = '*';
				else
					testArray[i][j] = '.';
				System.out.print(testArray[i][j]);
			}
			System.out.println();
		}
		
		
		// Print number of rows and columns then generate a
		//2D array with randomized mines(100 by 1 edge case)
		System.out.println( "100 1");
		for(i = 0; i < 100; i++){
			for(j = 0; j < 1; j++){
				random = (int) Math.floor(Math.random()*100+1);
				if (random < 21)
					testArray[i][j] = '*';
				else
					testArray[i][j] = '.';
				System.out.print(testArray[i][j]);
			}
			System.out.println();
		}
		
		//Print out 1 by 1 with no mine edge case
		System.out.println("1 1");
		System.out.println(".");
		
		//Print out 1 by 1 with mine edge case
		System.out.println("1 1");
		System.out.println("*");
		
		//Generate and print 50 test cases with randomized
		//size and mines
		for(t = 0; t < 50; t++){
			row = (int) Math.floor(Math.random()*100+1);
			col = (int) Math.floor(Math.random()*100+1);
			testArray = new char[row][col];
			System.out.print( row+" "+col);
			System.out.println();
			for(i = 0; i < row; i++){
				for(j = 0; j < col; j++){
					random = (int) Math.floor(Math.random()*100+1);
					if (random < 21)
						testArray[i][j] = '*';
					else
						testArray[i][j] = '.';
					System.out.print(testArray[i][j]);
				}
				System.out.println();
			}
		}
      System.out.print("0 0");
	}
   

}
