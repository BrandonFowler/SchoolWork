//Created by Brandon Fowler
//Class: CSCD 210
//Lab: #4 Triangle Sides Lab


import java.util.Scanner;

public class TriangleSides{


 	public static void main(String[] args)
	{

	//Gets Values For Sides of Triangle
	double[] ara = getValues();
	
	
	//Write Function
	displaySides(ara);

	}
	private static double[] getValues()
	{
	
		Scanner kb = new Scanner(System.in);
		
		double a;
		
		System.out.print("Enter a value for base: ");
		
		a = kb.nextDouble();
		
		double[] myArray = new double[3];
		
		myArray[0] = a;
		
		myArray[1] = 2*a;
		
		myArray[2] = a*1.732050807568877;
		
		return myArray;
	
	}
	
	
	private static void displaySides (double[] ara)
	
	{
		double base = ara[0];
		double side = ara[1];
		double height= ara[2];
		
		System.out.printf("The base of the triangle is %3.2f units long",base);
		System.out.println();
		System.out.printf("The side of the triangle is %3.2f units long",side);
		System.out.println();
		System.out.printf("The height of the triangle is %3.2f units long",height);
	}





}
