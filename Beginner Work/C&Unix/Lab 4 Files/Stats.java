import java.util.Scanner;

public class Stats
{
	private final static int SIZE = 25;
	
	public static void main(String [] args)
	{
		int num, choice;
		double mean, median;
		int [] array = new int[SIZE];
		Scanner kb = new Scanner(System.in);
		
		num = readNum(kb);
		fillArray(array, num, kb);
		sort(array, num);
		
		do
		{	
			choice = menu(kb);
			
			if(choice == 1)
			{
				fillArray(array, num, kb);
				sort(array, num);
			}// end choice == 1
			
			else if(choice == 2)
			{
				num = readNum(kb);
				fillArray(array, num, kb);
				sort(array, num);
			}// end choice == 2
			
			else if(choice == 3)
			{
				mean = findMean(array, num);
				median = findMedian(num, array);
				printResults(mean, median);
			}// end choice == 3		
		
		}while(goAgain(kb));	
	
	}// end main
	
	public static int menu(Scanner kb)
	{
		// Ensures the menu choice is 1,2 or 3
		return 1;
	}// end menu
	
	
	public static boolean goAgain(Scanner kb)
	{
		// Asks the user to goAgain and ensure the answer is Y y N or N
		// returns true if the answer is Y or y false otherwise
		return false;
	}// end goAgain
	
	public static int readNum(Scanner kb)
	{
		// Asks the user for a number between 1 and 25 inclusive
		// Ensures the number is in range
		// returns it
		return 1;
	}// readNum
	
	public static void fillArray(int [] array, int num, Scanner kb)
	{
		// Fills the array with user prompted numbers
	
	}// end fillArray
	
	public static void sort(int [] array, int num)
	{
		// Sorts the array in ascending order
		
	}// end sort
	
	public static double findMean(int [] array, int num)
	{
		// Finds the mean of the numbers in the array
		return 1;
	}// end findMean
	
	public static double findMedian(int num, int [] array)
	{
		// Finds the median of the array
		// if even num elements the the mean of the middle 2
		return 1;
	}// end findMedian
	
	public static void printResults(double mean, double median)
	{
		// Prints the results of the mean and median on one line
		
	}// end printResults

}// end class