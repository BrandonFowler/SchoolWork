//Created by Brandon Fowler
//Lab Assignment: Euclid's GCD
//Class: CSCD 210



import java.util.*;

public class Euclid
{	
	
	//Main
	//Get user input, and calls functions.
	public static void main(String[]args)
	{
		int num1 = getInt();
		int num2 = getInt();
		gcdCalculator(num1, num2);
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
	
	//Calculates, and prints gcd.
	private static void gcdCalculator(int num1, int num2)
	{
		int remainder = 0;
		
		while(num2 != 0)
		{
			remainder = num1 % num2;
			num1 = num2;
			num2 = remainder;
		}
		System.out.println();
		System.out.println("The greatest common divisor is "+num1);
	
	}
	


}