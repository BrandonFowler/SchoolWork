//Creator: Brandon Fowler
//RationalDriver.java(Part of assignment 5)
//Class:CSCD 210


import java.util.*;

public class RationalDriver
{ 
	//main
	//Initialize array of Rationals, then calls the user menu
	//________________________________________________________________________________
	public static void main(String[]args)
	{
		Rational[] ara = {new Rational(2,3),new Rational(2,18),new Rational(3,12),new Rational(9,3),new Rational(2,5),new Rational(22,7)};
	
		choiceMenu(ara);
	}
	//choiceMenu
	//Displays menu, and calls functions to execute the users choice
	//_______________________________________________________________________________
	private static void choiceMenu(Rational[] ara)
	{
		//Prints menu
		System.out.println("Welcome! What would you like to do?");
		System.out.println();
		System.out.println("Choice # 1: Display the value of a rational object in the array.");
		System.out.println("Choice # 2: Change the value of a rational object in the array.");
		System.out.println("Choice # 3: Add two rationals of the array.");
		System.out.println("Choice # 4: Subtract two rationals of the array.");
		System.out.println("Choice # 5: Sort the array of rationals.");
		System.out.println("Choice # 6: Print all of the rational objects in the array.");
		System.out.println("Choice # 7: Quit this programm.");
		System.out.println();
		
		//Calls method to get user choice
		int choice = getChoice();
		
		//Executes user choice
		while(choice != 7)
		{		
			switch (choice)
			{
				//Displays a Rational from the array
				case 1:
					int indexChoice = getIndex();
					System.out.println();
					System.out.println("The rational you have chosen to display is: "+ara[indexChoice]);
					break;
				//Replaces a rational in the array with one given by the user
				case 2:
					System.out.println();
					System.out.println("Which one would you like to change?");
					int index = getIndex();
					int num = getNum();
					int den = getDen();
					Rational r = new Rational(num,den);
					ara[index] = r;
					break;
				//Adds two rationals in the array
				case 3:
					int I1 = getIndex();
					int I2 = getIndexB();
					Rational r1 = ara[I1];
					Rational r2 = ara[I2];
					Rational frac = Rational.add(r1,r2);
					System.out.println();
					System.out.println("You chose to add "+r1+" and "+r2+" the solution is "+frac);
					break;
				//Subtracts one rational in the array from another
				case 4:
					int I3 = getSI();
					int I4 = getSIV();
					Rational r3 = ara[I3];
					Rational r4 = ara[I4];
					Rational frac2 = Rational.sub(r3,r4);
					System.out.println();
					System.out.println("You chose to subtract "+r4+" from "+r3+" the solution is "+frac2);
					break;
				//Sorts the array, smallest to biggest
				case 5:
					ara = insertionSort(ara);
					System.out.println();
					System.out.println("The array has been sorted");
					
					break;
				//Prints out all of the rationals in the array
				case 6:
					System.out.println("Rationals in the array are: "+ara[0]+","+ara[1]+","+ara[2]+","+ara[3]+","+ara[4]+","+ara[5]);
					break;
			}
			
			//prints out menu
			System.out.println();
			System.out.println("Welcome! What would you like to do?");
			System.out.println("Choice # 1: Display the value of a rational object in the array.");
			System.out.println("Choice # 2: Change the value of a rational object in the array.");
			System.out.println("Choice # 3: Add two rationals of the array.");
			System.out.println("Choice # 4: Subtract two rationals of the array.");
			System.out.println("Choice # 5: Sort the array of rationals.");
			System.out.println("Choice # 6: Print all of the rational objects in the array.");
			System.out.println("Choice # 7: Quit this programm.");
			System.out.println();
			
			//Calls method to get user choice
			choice = getChoice();
		}

	}
	
	//Get choice method
	//Get the users choice then returns it
	//Error checking included
	//____________________________________________________________________________
	private static int getChoice()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please choose your desired option 1-7: ");
		
		while (!kb.hasNextInt())
		{
			System.out.println();
			System.out.println("You options are clearly stated. Try again silly!");
			System.out.println();
			System.out.print("Please choose a VALID option 1-7: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		while (Val < 1 || Val > 7)
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
				System.out.print("Please choose a VALID option 1-7: ");
				kb = new Scanner(System.in);
			}
			Val = kb.nextInt();
		}
		return Val; 
	}
	
	//Get integer method
	//Gets any integer and returns it
	//Error checking included
	//____________________________________________________________________________
	private static int getInt()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
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
		
		return Val;
	}
	
	//Get index method
	//Gets an integer specified by the user then returns it to be used as an index value
	//Error checking included
	//___________________________________________________________________________________
	private static int getIndex()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.print("Please enter the index number of a rational in the array: ");
		
		while (!kb.hasNextInt())
		{
			System.out.println();
			System.out.println("Your options are clearly stated. Your failure is disapointing!");
			System.out.println();
			System.out.print("Please enter a valid index value this time: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		while (Val < 0 || Val > 5)
		{
			System.out.println();
			System.out.println("Fail. Try harder!");
			System.out.println();
			System.out.print("Enter a valid index value: ");
			
			while (!kb.hasNextInt())
			{
				System.out.println();
				System.out.println("Your options are clearly stated. Your failure is disapointing!");
				System.out.println();
				System.out.print("Please enter a valid index value this time: ");
				kb = new Scanner(System.in);
			}
			Val = kb.nextInt();
		}
		return Val;
	}
	
	//Get indexB method
	//Gets a secondary integer specified by the user then returns it to be used as an index value
	//Error checking included
	//__________________________________________________________________________________
	private static int getIndexB()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.print("Please enter the index number of the second rational in the array to be added: ");
		
		while (!kb.hasNextInt())
		{
			System.out.println();
			System.out.println("Your options are clearly stated. Your failure is disapointing!");
			System.out.println();
			System.out.print("Please enter a valid index value this time: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		while (Val < 0 || Val > 5)
		{
			System.out.println();
			System.out.println("Fail. Try harder!");
			System.out.println();
			System.out.print("Enter a valid index value: ");
			
			while (!kb.hasNextInt())
			{
				System.out.println();
				System.out.println("Your options are clearly stated. Your failure is disapointing!");
				System.out.println();
				System.out.print("Please enter a valid index value this time: ");
				kb = new Scanner(System.in);
			}
			Val = kb.nextInt();
		}
		return Val;
	}

	
	//Get SI method
	//Gets an integer to be used as an index value for a rational in the array to be subtracted from
	//Error checking included
	//_________________________________________________________________________________________________
	private static int getSI()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please enter the index value of the rational you wish to subtract from: ");
		
		while (!kb.hasNextInt())
		{
			System.out.println();
			System.out.println("Your options are clearly stated. Your failure is disapointing!");
			System.out.println();
			System.out.print("Please enter a valid index value this time: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		while (Val < 0 || Val > 5)
		{
			System.out.println();
			System.out.println("Fail. Try harder!");
			System.out.println();
			System.out.print("Enter a valid index value: ");
			
			while (!kb.hasNextInt())
			{
				System.out.println();
				System.out.println("Your options are clearly stated. Your failure is disapointing!");
				System.out.println();
				System.out.print("Please enter a valid index value this time: ");
				kb = new Scanner(System.in);
			}
			Val = kb.nextInt();
		}
		return Val;
	}
	
	//Get SIV method
	//Get an integer to be used as an index value for a rational in the array to subtract with
	//Error checking included
	//___________________________________________________________________________________________
	private static int getSIV()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please enter the index value of the rational you wish to subtract: ");
		
		while (!kb.hasNextInt())
		{
			System.out.println();
			System.out.println("Your options are clearly stated. Your failure is disapointing!");
			System.out.println();
			System.out.print("Please enter a valid index value this time: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		while (Val < 0 || Val > 5)
		{
			System.out.println();
			System.out.println("Fail. Try harder!");
			System.out.println();
			System.out.print("Enter a valid index value: ");
			
			while (!kb.hasNextInt())
			{
				System.out.println();
				System.out.println("Your options are clearly stated. Your failure is disapointing!");
				System.out.println();
				System.out.print("Please enter a valid index value this time: ");
				kb = new Scanner(System.in);
			}
			Val = kb.nextInt();
		}
		return Val;
	}
	
	//Get num method
	//Gets an integer to be used as a numerator in a rational object
	//Error checking included
	//____________________________________________________________________
	private static int getNum()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please enter a value for the numerator: ");
		
		while (!kb.hasNextInt())
		{
			System.out.println();
			System.out.println("Your options are clearly stated. Your failure is disapointing!");
			System.out.println();
			System.out.print("Please enter a valid number this time: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		return Val;
	}
	
	//Get Den method
	//Gets an integer to be used as a denominator in a rational object
	//Error checking included
	//______________________________________________________________________
	private static int getDen()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please enter a value for the denominator: ");
		
		while (!kb.hasNextInt())
		{
			System.out.println();
			System.out.println("Your options are clearly stated. Your failure is disapointing!");
			System.out.println();
			System.out.print("Please enter a valid number this time: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		return Val;
	}
	
	//insertion sort
	//Sorts an array of rationals
	//___________________________________________________________________
	public static Rational[] insertionSort(Rational[] array)
	{
		Rational key;
		int index;
		int position;
		for (index = 1; index < array.length; index++)
		{
			key = array[index];
			position = index;
			while (position > 0 && key.compareTo(array[position-1]) < 0)
			{
				array[position] = array[position-1];
				position--;
			}
			array[position] = key;
		}
		return array;	
	}

	
}	