//ShapeTester.java(Part of Assignment 6)
//Creator: Brandon Fowler
//Class: CSCD 211

import java.io.*;
import java.util.*;

public class ShapeTester
{
	//Main
	public static void main(String[] args)throws ShapeException,IOException
	{
		//Read file and create an int array with the points-----------------------------------
		
		int count = 0;
		int count2 = 0;
		try
		{
  
  		//read file
  		FileInputStream fstream = new FileInputStream("shapes.in");
  		DataInputStream in = new DataInputStream(fstream);
 	   BufferedReader br = new BufferedReader(new InputStreamReader(in));
  		String strLine;
		
		//Count lines in file
  		while ((strLine = br.readLine()) != null)   
		{
			count++;
 		}		
 		in.close();
    	}
	 	catch (Exception e)
	 	{
		System.out.println();
  		System.err.println("Error reading the file; something went wrong! Please Run Pragram again!");
  	 	}
		//Create array based on lines in file
		double[] ara = new double[count];
		try
		{
  		
		//Open file again
  		FileInputStream fstream = new FileInputStream("shapes.in");
  		DataInputStream in = new DataInputStream(fstream);
 	   BufferedReader br = new BufferedReader(new InputStreamReader(in));
  		String strLine;
		
		//Put values of each line into an array line by line
  		while ((strLine = br.readLine()) != null)   
		{
  			ara[count2] = Double.parseDouble(strLine);
			count2++;
		
 		}		
 		in.close();
    	}
	 	catch (Exception e)
	 	{
  		System.err.println("!");
  	 	}
		//End file read------------------------------------------------------------------
		
		
		
		//Sends array of points to method to be split into Parallelograms
		Parallelogram[] pArray = shapeDataSplitter(ara);
		
		//Sends array of Parallelograms to be tested, and changed into other shapes
		pArray = checkShapes(pArray);
		
		//Sends array to method to be converted from and array to a linked ShapeList
		ShapeList LinkedShapes = ShapeList.arrayToLinked(pArray);
		
		//Sorts LinkedShapes ShapeList
		LinkedShapes.selectionSort();
		
		//Send LinkedShapes list to Menu to be manipulate by user
		shapeMenu(LinkedShapes);
						
	}
	
	//shapeMenu Method
	//Displays choices, gets user choice, then exicutes choice
	public static void shapeMenu(ShapeList LinkedShapes)throws IOException
	{
		
		//Menu
		System.out.println("Welcome! What would you like to do?");
		System.out.println();
		System.out.println("Choice # 1: Print all of the shape info to the screen, and file.");
		System.out.println("Choice # 2: Build a sublist of a certain shape, and print it to screen.");
		System.out.println("Choice # 3: Print all shapes to screen in reverse order.");
		System.out.println("Choice # 4: Remove all shapes of a given type from the list.");
		System.out.println("Choice # 5:Quit this program.");
		System.out.println();
		
		//Get choice
		int choice = getMainMenuChoice();
		int choice2;
		
		//While loop to keep program running
		while(choice != 5)
		{	
			//Exicute choice	
			switch (choice)
			{
				case 1:
					ShapeList.printData(LinkedShapes);
					break;
				case 2:
					System.out.println("What shape would you like to build a sub list with to print?");
					System.out.println("Choice # 1: Parallelogram");
					System.out.println("Choice # 2: Rectangle");
					System.out.println("Choice # 3: Square");
					choice2 = getSecondMenuChoice();
					switch (choice2)
					{
						case 1:
							LinkedShapes.buildNPrint("Parallelogram");
							break;
						case 2:
							LinkedShapes.buildNPrint("Rectangle");
							break;
						case 3:
							LinkedShapes.buildNPrint("Square");
							break;
					}
					break;
				case 3:
					LinkedShapes.printReverse();
					break;
				case 4:
					System.out.println("What type of shape would you like to remove?");
					System.out.println("Choice # 1: Parallelogram");
					System.out.println("Choice # 2: Rectangle");
					System.out.println("Choice # 3: Square");
					choice2 = getSecondMenuChoice();
					switch (choice2)
					{
						case 1:
							LinkedShapes = ShapeList.removeType(LinkedShapes,"Parallelogram");
							break;
						case 2:
							LinkedShapes = ShapeList.removeType(LinkedShapes,"Rectangle");
							break;
						case 3:
							LinkedShapes = ShapeList.removeType(LinkedShapes,"Square");
							break;
					}
					break;
			}
			
			//Re-display Menu
			System.out.println();
			System.out.println("Welcome! What would you like to do?");
			System.out.println();
			System.out.println("Choice # 1: Print all of the shape info to the screen, and file.");
			System.out.println("Choice # 2: Build a sublist of a certain shape, and print it to screen.");
			System.out.println("Choice # 3: Print all shapes to screen in reverse order.");
			System.out.println("Choice # 4: Remove all shapes of a given type from the list.");
			System.out.println("Choice # 5:Quit this program.");
			choice = getMainMenuChoice();
		}
   
	} 
	
	//shapeDataSplitterMethod	
	//Take array filled with points, and turns it into a array of valid Parallelograms
	public static Parallelogram[] shapeDataSplitter(double[] ara)throws ShapeException,IOException
	{
		//Keep track of array index
		int index = 0;
		//Keeps track of index in new array created below
		int count = 0;
		//Keeps track of amount of invalid shape exceptions
		int count2 = 0;
		
		//Array to be filled with Parallelograms
		Parallelogram[] array = new Parallelogram[ara.length/8];
		
		//Open file
		BufferedWriter out = new BufferedWriter(new FileWriter("shape_errors.txt"));
					
		//Separates points in array, and uses the data to make parallelograms
		while(index<ara.length-1)
		{	
			//If shape is valid, add to new array
			if (Parallelogram.isParallelogram(ara[index],ara[index+2],ara[index+4],ara[index+6],ara[index+1],ara[index+3],ara[index+5],ara[index+7]))
			{
				array[count] = new Parallelogram(ara[index],ara[index+2],ara[index+4],ara[index+6],ara[index+1],ara[index+3],ara[index+5],ara[index+7]);
				count++;
			}
			//If shape is not valid, throw ShapeException, and write data to file
			else
			{
			
				try 
				{
					out.write("Shape not valid:"+"\r\n");
					out.write("Point1: ("+ara[index]+","+ara[index+1]+")");
					out.write(" Point2: ("+ara[index+2]+","+ara[index+3]+")");
					out.write(" Point3: ("+ara[index+4]+","+ara[index+5]+")");
					out.write(" Point4: ("+ara[index+6]+","+ara[index+7]+")");
					out.write("\r\n");	    				
				} 				
				catch (IOException e)
				{
					System.out.println("Failed to read file");
				}
				
				try
				{
					count2++;
					throw new ShapeException("Invalid Shapes found: "+count2);
				}
				catch(ShapeException e)
				{
				}
				
			}
			//advance index to next set of coordinates
			index = index+8; 
		}
		out.close();
		return array;
	} 
	
	
	//checkShapes Method
	//Checks if parallelograms are Rectandgles or Squars, and converts them accordingly
	public static Parallelogram[] checkShapes(Parallelogram[] ara)
	{
		for(int i = 0; i < ara.length && ara[i] != null; i++)
		{
			if(Rectangle.isRectangle(ara[i]))
			{
				ara[i] = new Rectangle(ara[i]);
			}
			if(Square.isSquare(ara[i]))
			{
				ara[i] = new Square(ara[i]);
			}
		}
		return ara;
	}
	
	//getMainMenuChoice method
	//Gets integer choice for main menu
	//Checks for errors, and re-displays if a bad number is entered
	private static int getMainMenuChoice()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please choose your desired option 1-5: ");
		
		while (!kb.hasNextInt())
		{
			System.out.println();
			System.out.println("You options are clearly stated. Try again silly!");
			System.out.println();
			System.out.print("Please choose a VALID option 1-5: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		while (Val < 1 || Val > 5)
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
	
	//getSecondaryMenuChoice method
	//Gets user choice for secondary menus
	//Checks for errors, and re-displays if a bad number is entered
	private static int getSecondMenuChoice()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please choose your desired option 1-3: ");
		
		while (!kb.hasNextInt())
		{
			System.out.println();
			System.out.println("You options are clearly stated. Try again silly!");
			System.out.println();
			System.out.print("Please choose a VALID option 1-3: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		while (Val < 1 || Val > 3)
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
				System.out.print("Please choose a VALID option 1-3: ");
				kb = new Scanner(System.in);
			}
			Val = kb.nextInt();
		}
		return Val; 
	}



}