//Creater: Brandon Fowler
//Assignment: # 4
//Class: CSCD 210
//Extra credit attempted using hasNextInt function

import java.util.*;

public class fowlerbhw4
{
//-----------------------------------------------------------------------
//Start of main function.
//Calls menu function.
//-----------------------------------------------------------------------
	public static void main(String[]args)
	{
		choiceMenu();
	}
//-----------------------------------------------------------------------
//Start of menu function.
//Displays menu, initializes variables and objects, calls functions.
//-----------------------------------------------------------------------
	public static void choiceMenu()
	{
		int size = 0;
		int [] array = new int [1000];
		
		System.out.println("Welcome! What would you like to do?");
		System.out.println();
		System.out.println("Choice # 1: Add a new number to the array.");
		System.out.println("Choice # 2: Display the mean of the array.");
		System.out.println("Choice # 3: Display the median of the array.");
		System.out.println("Choice # 4:Quit this program.");
		System.out.println();
		
		int choice = getChoice();
		
		while(choice != 4)
		{		
			switch (choice)
			{
				case 1:
					array[size] = getInt();
					size++;
					break;
				case 2:
					displayMean(array,size);
					break;
				case 3:
					displayMedian(array,size);
					break;
			}
			
			System.out.println();
			System.out.println("Welcome! What would you like to do?");
			System.out.println();
			System.out.println("Choice # 1: Add a new number to the array.");
			System.out.println("Choice # 2: Display the mean of the array.");
			System.out.println("Choice # 3: Display the median of the array.");
			System.out.println("Choice # 4:Quit this program.");
			choice = getChoice();
		}
	
	}
//--------------------------------------------------------------------
//Start of get choice function.
//Gets valid choice from user.
//--------------------------------------------------------------------
	private static int getChoice()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please choose your desired option 1-5: ");
		
		while (!kb.hasNextInt())//Extra Credit Attempt
		{
			System.out.println();
			System.out.println("You options are clearly stated. Try again silly!");
			System.out.println();
			System.out.print("Please choose a VALID option 1-5: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		while (Val < 1 || Val > 4)
		{
			System.out.println();
			System.out.println("Fail. Try harder!");
			System.out.println();
			System.out.print("Please enter a VALID option: ");

			while (!kb.hasNextInt())
			{
				System.out.println();
				System.out.println("You options are clearly stated. Try again silly!");
				System.out.println();
				System.out.print("Please choose a VALID option 1-5: ");
				kb = new Scanner(System.in);
			}
			Val = kb.nextInt();
		}
		return Val; 
	}
//--------------------------------------------------------------------------
//Start of get int function.
//Gets a valid positive integer from the user.
//--------------------------------------------------------------------------
	private static int getInt()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please enter a VALID POSITIVE INTEGER: ");
		
		while (!kb.hasNextInt())//Extra Credit Attempt
		{
			System.out.println();
			System.out.println("Your options are clearly stated. Your failure is disapointing!");
			System.out.println();
			System.out.print("Please enter a VALID POSITIVE INTEGER this time: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		while (Val < 0)
		{
			System.out.println();
			System.out.println("Fail. Try harder!");
			System.out.println();
			System.out.print("Please enter a VALID POSITIVE INTEGER: ");
			
			while (!kb.hasNextInt())
			{
				System.out.println();
				System.out.println("Your options are clearly stated. Your failure is disapointing!");
				System.out.println();
				System.out.print("Please enter a VALID POSITIVE INTEGER this time: ");
				kb = new Scanner(System.in);
			}
			Val = kb.nextInt();
		}
		return Val;
	}
//---------------------------------------------------------------------------
//Start of display mean function.
//Calculates the mean of an array passed into the function
//then displays the result.
//---------------------------------------------------------------------------
	private static void displayMean(int [] array, int size)
	{
		double mean;
		double total = 0.0;
		for (int i = 0; i < size; i++)
		{
			total = total + array[i];	
		}
		mean = total/size;
		System.out.println("The mean of the array is: "+mean); 
	}
//--------------------------------------------------------------------------
//Start of display median function.
//Calculates the median of an array passed into the function
//then displays the result.
//--------------------------------------------------------------------------
	private static void displayMedian(int [] array, int size)
	{
		int [] arrayb = new int[size];
		for (int i = 0; i < size; i++)
		{
			arrayb[i] = array[i];
		}
		int[] sortedArray = selectionSort(arrayb);
		
		if (sortedArray.length%2==0)
		{
			int median1 = (sortedArray.length)/2;
			int median2 = (sortedArray.length/2)-1;
			double tMedian = (sortedArray[median1]+sortedArray[median2])/2.0;
			System.out.println();
			System.out.println("The median is: "+tMedian);
			System.out.println();
		}
		else
		{
			int median = (sortedArray.length/2);
			System.out.println();
			System.out.println("The median is: "+sortedArray[median]);
			System.out.println();
		}
	}
//--------------------------------------------------------------------------
//Start of selection sort function.
//Sorts an array passed into the function, then returns it.
//--------------------------------------------------------------------------
	public static int[] selectionSort(int[]array)
	{
		int position = 0;
		int indexSmallest = 0;
		int cur = 0;
		int temp;
		
		for (position = 0; position < array.length; position++)
		{
			indexSmallest = position;
			for (cur = position + 1; cur < array.length; cur++)
			{
				if (array[cur] < array[indexSmallest])
				{
					indexSmallest = cur;
				}
			}
			temp = array[position];
			array[position] = array[indexSmallest];
			array[indexSmallest] = temp;
		}
		return array;
	}
//__________________________________________________________________________
//End
//__________________________________________________________________________
}


