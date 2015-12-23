//Created by: Brandon Fowler
//Class: CSCD 210
//Assignment: #3
//EXTRA CREDIT IS ATTEMPTED!


import java.util.*;


public class IntFun
{


	//Main, just calls the menu and choice input method.
	public static void main(String[]args)
	{
		choiceMenu();
	}
	
	//Displays the menu, calls method to get choice, and calls methods to execute choice.
	private static void choiceMenu()
	{
		
		int choice;
		int userInteger;
		
		System.out.println("Welcome Master!");
		userInteger = getInt();
		System.out.println();
		System.out.println("Here are your options:");
		System.out.println();
		System.out.println("Option # 1. You may enter a new integer!");
		System.out.println("Option # 2. You may print the number of odd digits, even digits, and zeros in the integer. >.>");
		System.out.println("Option # 3. You may Print the prime numbers between two, and the integer. ^.^");
		System.out.println("Option # 4. You have authorization to print the sum of the digits of the integer. O.O");
		System.out.println("Option # 5. You are able to quit this program, leaving me cold, and alone in here. (>@.@)>");
		
		choice = getChoice();
		
				
		
		while (choice != 5)
		{
			switch (choice)
			{
				case 1:	userInteger = getInt();
							break;
				case 2:	displayTypes(userInteger);
							break;
				case 3:	primeCalc(userInteger);
							break;
				case 4: 	digitSum(userInteger);
							break;
			}
			System.out.println();
			System.out.println("Your orders have been carried through master!");
			System.out.println("Here are your options:");
			System.out.println();
			System.out.println("Option # 1. You may enter a new integer!");
			System.out.println("Option # 2. You may print the number of odd digits, even digits, and zeros in the integer. >.>");
			System.out.println("Option # 3. You may Print the prime numbers between two, and the integer. ^.^");
			System.out.println("Option # 4. You have authorization to print the sum of the digits of the integer. O.O");
			System.out.println("Option # 5. You are able to quit this program, leaving me cold, and alone in here. (>@.@)>");
			choice = getChoice();		
		}
		
		System.out.println("Thank you, and Farewell Master!");
	}
		
	//Gets user choice.
	private static int getChoice()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please choose your desired option 1-5: ");
		
		while (!kb.hasNextInt())
		{
			System.out.println();
			System.out.println("You options are clearly stated. Try again master!");
			System.out.println();
			System.out.print("Please choose a VALID option 1-5: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		while (Val < 0 || Val > 5)
		{
			System.out.println();
			System.out.println("Fail. Try harder!");
			System.out.println();
			System.out.print("Please enter a VALID option: ");

			while (!kb.hasNextInt())
			{
				System.out.println();
				System.out.println("You options are clearly stated. Try again master!");
				System.out.println();
				System.out.print("Please choose a VALID option 1-5: ");
				kb = new Scanner(System.in);
			}
			
			Val = kb.nextInt();

			
		}
		
		
		return Val;
		
	}
	
	//Gets positive integer from the user.
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
	
	//Displays how many even, odd, and zero digits are in an integer.
	private static void displayTypes(int UI)
	{
		int Evens = 0;
		int Odds = 0;
		int Zeros = 0;
		int ValHold;
		
		while (UI > 0)
		{
			ValHold = UI%10;
			if (ValHold != 0 && ValHold%2 == 0)
			{
				Evens++;
			}
			else if (ValHold == 0)
			{
				Zeros++;
			}
			else
			{
				Odds++;
			}
			UI = UI/10;
		}
		System.out.println();
		System.out.println("The number of odd digits is "+Odds+", and the number of even numbers is "+Evens+", and the number of zeros is "+Zeros);
	}
	
	//Prints prime numbers up to the specified integer.(EXTRA CREDIT!)
	private static void primeCalc(int UI)
	{
		System.out.print("The Prime Numbers are: ");
		System.out.println();
		
		int P = 0;
        
		while (++P <= UI) 
		{

      	int P2 = (int) Math.ceil(Math.sqrt(P));

         boolean Decision = false;

         while (P2 > 1) 
			{

         	if ((P != P2) && (P % P2 == 0)) 
				{
            	Decision = false;
               break;
            }
				else if (!Decision) 
				{
            	Decision = true;
            }

            --P2;
			}

         if (Decision) 
			{
         	System.out.println(P);
                
         }

	
		}


	}
	
	//Prints the sum of an integers digits.
	private static void digitSum(int UI)
	{
		int Sum = 0;
		int ValHold;
		
		while (UI > 0)
		{
			ValHold = UI%10;
			Sum = Sum+ValHold;
			UI = UI/10;
		}
		System.out.println();
		System.out.print("The sum of the digits is: "+Sum);
		System.out.println();
	}




}
// PLEASE NOTE: EXTRA CREDIT IS ATTEMPTED IN THIS PROGRAM!