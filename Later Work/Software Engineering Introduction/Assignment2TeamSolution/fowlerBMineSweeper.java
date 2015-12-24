
//Brandon Fowler
//4/8/14
//CSCD 350



import java.io.*;
import java.util.*;

public class fowlerBMineSweeper{
	
	
	//Initializes variables and calls methods===============================================================
	public static void main(String[] args) throws FileNotFoundException{
		  
		Scanner read = new Scanner(System.in);      //Open file and store scanner
		char[][] mineField = null;                  //Stores file input
		int[][] result = null;                      //Stores result of input calculations
		int row = read.nextInt();                   //Stores row amount of each mine field
		int col = read.nextInt();                   //Stores column amount of each mine field
		int countPlayed = 1;                        //Stores how many mine fields have been generated
		
		//Calls functions until end of input file
		while(row > 0 && col > 0){
			
			System.out.println("Field #"+countPlayed+":");
			mineField = loadMineField(mineField, row, col, read);
			result = calcResult(result, mineField, row, col);
			printResult(result, row, col);
			row = read.nextInt();
			col = read.nextInt();
			countPlayed++;
		}
		
		read.close(); // Close file
	}
	
		
	
	
	//Takes input from file to make a 2D array filled with the data========================================
	private static char[][] loadMineField(char[][] mineField, int row, int col, Scanner read) {
		
      String line;
		mineField = new char[row][col];
		for(int i = 0; i < row; i++){
         line = read.next();
			for(int j = 0; j < col; j++){
            mineField[i][j] = line.charAt(j); 
			}
		}
		return mineField;
	}
	
	
	//Calculates the results of the mine field=============================================================
	private static int[][] calcResult(int[][] result, char[][] mineField,
			int row, int col) {
		
		result = new int[row][col];                //Stores results
		
		//Build initial result array only accounting for mines
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				if(mineField[i][j]=='*'){
					result[i][j] = 9;
            }
				else{
					result[i][j] = 0;
            }
			}
		}
		
		//Find mines in result array and increment array values around it
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				if(result[i][j] > 8){
            
					if((i-1) >= 0 && (j-1) >= 0){
						result[i-1][j-1]++;
               }
					if((i-1) >= 0){
						result[i-1][j]++;
               }
					if((i-1) >= 0 && (j+1) < col){
						result[i-1][j+1]++;
               }
					if((j-1) >= 0){
						result[i][j-1]++;
               }
					if((j+1) < col){
						result[i][j+1]++;
               }
					if((i+1) < row && (j-1) >= 0 ){
						result[i+1][j-1]++;
               }
					if((i+1) < row){
						result[i+1][j]++;
               }
					if((i+1) < row && (j+1) < col){
						result[i+1][j+1]++;
               }
				}
			}
		}
		return result;
	}
	
	
	//Prints the result array=============================================================
	private static void printResult(int[][] result, int row, int col) {
		
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				if(result[i][j] > 8){
					System.out.print("*");
            }
				else{
					System.out.print(result[i][j]);
            }
			}
			System.out.println();
		}
	}
}
