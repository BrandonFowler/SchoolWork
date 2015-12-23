//Programmer: Brandon Fowler
//Class: CSCD-210
//Assignment: #1

import java.util.*;

public class Track

{	
	//Main Module.
	//Gets User Input, Calls Other Modules to Calculate, Then Prints Results.
	public static void main(String[] args)
	
	{
		double time;
		
		Scanner kb;
		
		kb = new Scanner(System.in);
		
		System.out.print("Please enter the winning time of the race: ");
		
		time = kb.nextDouble();
		
		System.out.println("The Person was traveling at a rate of:");
		System.out.println();
		metersPerSecond(time);
		System.out.println();
		feetPerSecond(time);
		System.out.println();
		kilometersPerHour(time);
		System.out.println();
		milesPerHour(time);
		System.out.println();
		System.out.println();
		timeForMile(time);
		System.out.println();
		timeForYards(time);	
	}
	//metersPerSecond Module.
	//Calculates Speed in Meters Per Second, Then Prints Result.
	private static void metersPerSecond(double time)
	
	{
		double MPS;
		
		MPS = 100.0/time;
		
		System.out.printf("%3.2f meters per second",MPS);
	}
	//feetPerSecond Module.
	//Calculates Speed in Feet Per Second, Then Prints Result.
	private static void feetPerSecond(double time)
	
	{
		double FPS;
		
		FPS = 328.083/time;
		
		System.out.printf("%3.2f feet per second",FPS);
	}
	//kilometersPerHour Module.
	//Calculates Speed in kilometers Per Hour, Then Prints Result.
	private static void kilometersPerHour(double time)
	
	{
		double KPH;
		
		KPH = 0.1/(time/3600);
		
		System.out.printf("%3.2f kilometers per hour",KPH);
	}
	//milesPerHour Module.
	//Calculates Speed in Miles Per Hour, Then Prints Result.
	private static void milesPerHour(double time)
	
	{
		double MPH;
		
		MPH = 0.062137/(time/3600);
		
		System.out.printf("%3.2f miles per hour",MPH);
	}	
	//timeForMile Module.
	//Calculates the Time it Takes to Run One Mile, Then Prints Result.
	private static void timeForMile(double time)
	
	{
		double TFM;
		
		int JustM;
		
		double JustS;
		
		TFM = 1/(0.062137/(time/60));
		
		JustM = (int)TFM;
		
		JustS = (TFM-JustM)*60; 
		
		System.out.printf("It would take "+JustM+" minutes and %3.2f seconds for the person to run a mile.",JustS);
	}
	//timeForYards Module.
	//Calculates the Time it Takes to Run 100 Yards, Then Prints Result.
	private static void timeForYards(double time)
	
	{
		double TFY;
		
		TFY = 100/(109.361/time);
		
		System.out.printf("It would take %3.2f seconds for the person to run 100 yards.",TFY); 
	}
}