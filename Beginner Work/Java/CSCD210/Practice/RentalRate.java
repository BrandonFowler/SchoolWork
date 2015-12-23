

//Created by: Brandon Fowler
//Class:CSCD 210
//Assignment: #2


import java.text.SimpleDateFormat;
import java.util.*;

public class RentalRate 
{  //Start of Pre-Made Code
	public static void main(String[] args)
	{    
   int curMonth = 0;
   int curDay = 0;
   int curYear = 0;
   int birthMonth = 0;
   int birthDay = 0;
   int birthYear = 0;
   String gender;
	int age = 0;
   String rateResult;         
         
  // Testing mode...   
   if (args.length > 0)
  	{
  // Establish a 'current' date for testing...
	curMonth = 2;
	curDay = 1;
	curYear = 2012;
            
   System.out.println("First test case: Renter is not old enough to rent...");
   birthMonth = 2;
	birthDay = 2;
	birthYear = 1987;
	gender = "m";
   age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
   rateResult = calcRateClass(age, gender);
	displayResults(gender, age, rateResult);
        
   System.out.println("\nSecond test case: Renter is old enough (57/285)...");
   birthMonth = 2;
	birthDay = 1;
	birthYear = 1987;
	gender = "m";
   age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
	rateResult = calcRateClass(age, gender);
	displayResults(gender, age, rateResult);
            
   System.out.println("\nThird test case: Renter is 35 and male (40/200)...");
   birthMonth = 1;
	birthDay = 1;
	birthYear = 1977;
	gender = "m";
   age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
	rateResult = calcRateClass(age, gender);
	displayResults(gender, age, rateResult);
         
   System.out.println("\nFourth test case: Renter is 35 and female (40/200)...");
   birthMonth = 1;
	birthDay = 1;
	birthYear = 1977;      gender = "f";
   age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
	rateResult = calcRateClass(age, gender);
	displayResults(gender, age, rateResult);
            
   System.out.println("\nFifth test case: Renter is 30 and male (57/285)...");
   birthMonth = 1;
	birthDay = 1;
	birthYear = 1982;
	gender = "m";
   age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
	rateResult = calcRateClass(age, gender);
	displayResults(gender, age, rateResult);
            
   System.out.println("\nSixth test case: Renter is 30 and female (40/200)...");
   birthMonth = 1;
	birthDay = 1;
	birthYear = 1982;
	gender = "f";
   age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
   rateResult = calcRateClass(age, gender);
	displayResults(gender, age, rateResult);
         
   System.out.println("\nSeventh test case: Renter is 76 and male (62/255)...");
   birthMonth = 1;
	birthDay = 1;
	birthYear = 1936;
	gender = "m";
   age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
   rateResult = calcRateClass(age, gender);
   displayResults(gender, age, rateResult);        
         
   System.out.println("\nEighth test case: Renter is 76 and female (66/265)...");
   birthMonth = 1;
	birthDay = 1;
	birthYear = 1936;
	gender = "f";
   age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
	rateResult = calcRateClass(age, gender);
	displayResults(gender, age, rateResult);         
   }//End of Pre-Made Code
	else
	{//Start of My Code
   Scanner kb = new Scanner(System.in);
   System.out.println("Welcome to the car renter's rate finder.");
	
	
	//Get Renter Gender From User
	System.out.println();
	System.out.print("Please Enter the Renters Gender. Type m For Male or f For Female: ");
	gender = kb.nextLine();          
	System.out.println();
	
	
	//Get Current Date From User
	System.out.print("Please Enter Todays Date in the Format (mm dd yyyy): ");
   String stringDate = kb.nextLine();
   String[] parts1 = stringDate.split(" ");
   	 
   curDay = Integer.parseInt(parts1[1]);
   curMonth = Integer.parseInt(parts1[0]);
   curYear = Integer.parseInt(parts1[2]);
	  
	
	//Get Renter Date of Birth From User         
	System.out.println();
	System.out.print("Please Enter Date of Birth in the Format (mm dd yyyy): ");
   String stringBirth = kb.nextLine();
   String[] parts2 = stringBirth.split(" ");
   	 
   birthDay = Integer.parseInt(parts2[1]);
   birthMonth = Integer.parseInt(parts2[0]);
   birthYear = Integer.parseInt(parts2[2]);
   		 
         
  // Get age
	age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
            
  // Get the rental rate  
   rateResult = calcRateClass(age, gender);
            
  // Display the results  
   displayResults(gender, age, rateResult);
   	}
   }	
	
	
	
	//Calculates Age
	private static int calcAge(int curMonth,int curDay,int curYear,int birthMonth,int birthDay,int birthYear)
	{
		int age;
		age = curYear - birthYear;
		if(birthMonth-curMonth <0)
			{
				age--;	
			
			}
			else if(curMonth==birthMonth)
			{
				if(birthDay-curDay <0)
				{
					age--;
				}
				else
				{
				age=age+0;
				}
			}
			else
			{
				age--;
			}
		return age;
	}
	
	
	
	//Calculates Risk Rate
	private static String calcRateClass(int age, String gender)
	{
		String rateResult;
		if (age<25)
		{
		rateResult="Sorry the renter is not 25 years of age or older!";	
		}
		else if (gender.equals("m")&&age>32&&age<66||gender.equals("f")&&age>29&&age<63)
		{
		rateResult="Best rate - $40.00 per day or $200.00 per week."; 
		}
		else if (gender.equals("f")&&age>24&&age<30)
		{
		rateResult="Risk Rate 1 - $50.00 per day or $255.00 per week.";
		}
		else if (gender.equals("m")&&age>24&&age<33)
		{
		rateResult="Risk Rate 2 - $57.00 per day or $285.00 per week.";
		}
		else if (gender.equals("f")&&age>62)
		{
		int fExtra=age-62;
		int fExtraD=fExtra*2+40;
		int fExtraW=fExtra*5+200;
	
		rateResult="Risk Rate 3 - $"+fExtraD+" per day or $"+fExtraW+" per week.";
		}
		else
		{
		int mExtra=age-65;
		int mExtraD=mExtra*2+40;
		int mExtraW=mExtra*5+200;
		
		rateResult="Risk Rate 3 - $"+mExtraD+" per day or $"+mExtraW+" per week.";

		}
		return rateResult;
	}
	
	
	
	
	//Prints Car Rental Rate Results for User
	private static void displayResults(String gender, int age, String rateResult)
	{
	if(gender.equals("f"))
	{
		gender="female";
	}
	else
	{
		gender="male";
	}
	System.out.println();
	System.out.println("Thank You");
	System.out.println();
	System.out.println("The "+gender+" renter is "+age+" years old");
	System.out.println();
	System.out.println("The rate class is: "+rateResult);
	
	}
	
	
	
	
		
}//End of My Code
	
