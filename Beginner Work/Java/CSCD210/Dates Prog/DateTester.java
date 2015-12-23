//Creator: Brandon Fowler
//DateTester.java(Part of Final Assignment)
//Class:CSCD 210 


//Import Statments____________________________________________________
import java.util.*;
import java.io.*;

//Class Start_________________________________________________________
public class DateTester
{
	//Main_____________________________________________________________
	//Calls methods to read file into an array, with validation.
	//Calls method to sort array.
	//Calls menu method.
	public static void main(String[]args)throws IOException
	{
		Date[] myDates = getArray();
		if (myDates[0] != null);
		{
			insertionSort(myDates);
			choiceMenu(myDates);
		}
	}
	
	//Menu________________________________________________________________
	//Displays Menu and executes choices.
	public static void choiceMenu(Date[] Dates)throws IOException
	{
		//Menu
		System.out.println();
		System.out.println("Array of dates successfully Loaded from File");
		System.out.println("What would you like to do now?");
		System.out.println("Option 1: Print array to screen.");
		System.out.println("Option 2: Print array to file of your choice.");
		System.out.println("Option 3: Search for date by index.");
		System.out.println("Option 4: Search for date by content.");
		System.out.println("Option 5: Add a date.");
		System.out.println("Option 6: Delete a date by index.");
		System.out.println("Option 7: Delete a date by content.");
		System.out.println("Option 8: Quit program.");
		
		//Gets Choice
		int choice = getInt();
		
		//Keeps menu looping
		while (choice != 8)
		{  
			
			switch(choice)
			{  
				//Exicutes Choice 1
				case 1:
					for (int i = 0; i < Dates.length; i++)
						{
							System.out.println();
							System.out.println(""+Dates[i]);
						}
					break;
				//Exicutes Choice 2
				case 2:
					String FileN = getName();
					PrintWriter out = new PrintWriter(FileN);
					for (int i = 0; i < Dates.length; i++)
					{
						out.println(Dates[i]);					
					}
					out.close();
					break;
				//Exicutes Choice 3
				case 3:
					int Index = getIndex();
					System.out.println();
					System.out.println(Dates[Index]);
					break;
				//Exicutes Choice 4
				case 4:
					Scanner kb;
					kb = new Scanner(System.in);
					
					System.out.println();
					System.out.print("Please enter the month in form MM: ");
					int M = kb.nextInt();
					System.out.println();
					System.out.print("Please enter the day in form DD: ");
					int D = kb.nextInt();
					System.out.println();
					System.out.print("Please enter the year in form YYYY: ");
					int Y = kb.nextInt();
				
					Date DateA = new Date(M,D,Y);
					
					int IndexA = linearSearch(Dates, DateA);
					
					if (IndexA > -1)
					{
						System.out.println();
						System.out.println("Date found at index: "+IndexA);
					}
					else
					{	
						System.out.println();
						System.out.println("Sorry, Date not found.");
					}
					break;
				//Exicutes Choice 5
				case 5:
					Scanner kb2;
					kb2 = new Scanner(System.in);
					System.out.println();
					System.out.print("Please enter the month in form MM: ");
					int M2 = kb2.nextInt();
					System.out.println();
					System.out.print("Please enter the day in form DD: ");
					int D2 = kb2.nextInt();
					System.out.println();
					System.out.print("Please enter the year in form YYYY: ");
					int Y2 = kb2.nextInt();
					while (Date.isValid(M2,D2,Y2) == false)
					{
						System.out.println();
						System.out.println("Sorry that is  not a valid date, try again.");
						System.out.println();
						System.out.print("Please enter the month in form MM: ");
						M2 = kb2.nextInt();
						System.out.println();
						System.out.print("Please enter the day in form DD: ");
						D2 = kb2.nextInt();
						System.out.println();
						System.out.print("Please enter the year in form YYYY: ");
						Y2 = kb2.nextInt();
					}
					Date T = new Date(M2,D2,Y2);
					Date[] Temp = new Date[Dates.length+1];
					int i;
					for (i = 0; i < Dates.length; i++)
					{
						Temp[i] = Dates[i];
					}
					Temp[i] = T;
					Dates = Temp;
					insertionSort(Dates);
					System.out.println();
					System.out.println("Your new date has been added, and the array has been re-sorted.");
					break;
				//Exicutes Choice 6
				case 6:
					Scanner kb3;
					kb3 = new Scanner(System.in);
					System.out.println();
					System.out.print("Please enter the index of the Date you wish to delete: ");
					int IndexB = kb3.nextInt();
					int c;
					Date[] Temp2 = new Date[Dates.length - 1];
					for( c = 0; c < IndexB; c++)
					{
						Temp2[c] = Dates[c];
					}
					for (c = IndexB+1; c < Dates.length; c++)
					{
						Temp2[c-1] = Dates[c];
					}
					Dates = Temp2;
					break;
				//Exicutes Choice 7
				case 7:
					Scanner kb4;
					kb4 = new Scanner(System.in);
					
					System.out.println();
					System.out.print("Please enter the month in form MM: ");
					int M3 = kb4.nextInt();
					System.out.println();
					System.out.print("Please enter the day in form DD: ");
					int D3 = kb4.nextInt();
					System.out.println();
					System.out.print("Please enter the year in form YYYY: ");
					int Y3 = kb4.nextInt();
				
					Date DateD = new Date(M3,D3,Y3);
					
					int IndexD = linearSearch(Dates, DateD);
					
					if (IndexD > -1)
					{
						int d;
						Date[] Temp3 = new Date[Dates.length - 1];
						for( d = 0; d < IndexD; d++)
						{
							Temp3[d] = Dates[d];
						}
						for (d = IndexD+1; d < Dates.length; d++)
						{
							Temp3[d-1] = Dates[d];
						}
						Dates = Temp3;
						System.out.println();
						System.out.println("Date Deleted.");
					}
					else
					{	
						System.out.println();
						System.out.println("Sorry, Date not found.");
					}

					break;
			}						
			//Prints Menu again		
			System.out.println();
			System.out.println("What would you like to do now?");
			System.out.println("Option 1: Print array to screen.");
			System.out.println("Option 2: Print array to file of your choice.");
			System.out.println("Option 3: Search for date by index.");
			System.out.println("Option 4: Search for date by content.");
			System.out.println("Option 5: Add a date.");
			System.out.println("Option 6: Delete a date by index.");
			System.out.println("Option 7: Delete a date by content.");
			System.out.println("Option 8: Quit program.");
			
			//Gets choice again
			choice = getInt();
		}			
		
	}		
	
	//Method for getting a string from the user__________________________________________
	private static String getName()
	{
		Scanner kb;
		String Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please enter the name of a file that you want to read dates from:  ");
		
		try
		{
			Val = kb.nextLine();
		}
		catch(Exception e)
		{
			System.out.println();
			System.out.println("The directions are clearly stated. Your failure is disapointing!");
			Val = getName();
		}
		return Val;	
	}
	
	//Method for loading an array full of valid dates from a file_______________________
	private static Date[] getArray()
	{
		String name = getName();
		int count = 0;
		int count2 = 0;
		Date[] ara;
		try
		{
  
  		FileInputStream fstream = new FileInputStream(name);
  		DataInputStream in = new DataInputStream(fstream);
 	   BufferedReader br = new BufferedReader(new InputStreamReader(in));
  		String strLine;
		
		
  		while ((strLine = br.readLine()) != null)   
		{
  			String A = strLine;
			String[] S = A.split("/");
			String m = S[0];
			String d = S[1];
			String y = S[2];
			int m2 = Integer.parseInt(m);
			int d2 = Integer.parseInt(d);
			int y2 = Integer.parseInt(y);
			if (Date.isValid(m2,d2,y2))
			{
				count++;
			}
			else
			{
				count = count;
			}
 		}		
 		in.close();
    	}
	 	catch (Exception e)
	 	{
		System.out.println();
  		System.err.println("Error reading the file; something went wrong! Please Run Pragram again!");
  	 	}
		ara = new Date[count];
		try
		{
  
  		FileInputStream fstream = new FileInputStream(name);
  		DataInputStream in = new DataInputStream(fstream);
 	   BufferedReader br = new BufferedReader(new InputStreamReader(in));
  		String strLine;
		
		
  		while ((strLine = br.readLine()) != null)   
		{
  			String A2 = strLine;
			String[] S2 = A2.split("/");
			String m3 = S2[0];
			String d3 = S2[1];
			String y3 = S2[2];
			int m4 = Integer.parseInt(m3);
			int d4 = Integer.parseInt(d3);
			int y4 = Integer.parseInt(y3);
			if (Date.isValid(m4,d4,y4))
			{
				ara[count2] = new Date(m4,d4,y4);
				count2++;
			}
			else
			{
				count2 = count2;
			}
 		}		
 		in.close();
    	}
	 	catch (Exception e)
	 	{
  		System.err.println("");
  	 	}
		return ara;
	}
	
	//Method for getting an integer from the user____________________________________
	private static int getInt()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please enter a VALID option: ");
		
		while (!kb.hasNextInt())
		{
			System.out.println();
			System.out.println("Your options are clearly stated. Your failure is disapointing!");
			System.out.println();
			System.out.print("Please enter a VALID option this time: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		while (Val < 1 || Val > 8)
		{
			System.out.println();
			System.out.println("Fail. Try harder!");
			System.out.println();
			System.out.print("Please enter a VALID option: ");
			
			while (!kb.hasNextInt())
			{
				System.out.println();
				System.out.println("Your options are clearly stated. Your failure is disapointing!");
				System.out.println();
				System.out.print("Please enter a VALID option: ");
				kb = new Scanner(System.in);
			}
			Val = kb.nextInt();
		}
		return Val;
	}
	
	private static int getIndex()
	{
		Scanner kb;
		int Val;
		kb = new Scanner(System.in);
		System.out.println();
		System.out.print("Please enter a VALID index: ");
		
		while (!kb.hasNextInt())
		{
			System.out.println();
			System.out.println("Your options are clearly stated. Your failure is disapointing!");
			System.out.println();
			System.out.print("Please enter a VALID index this time: ");
			kb = new Scanner(System.in);
		}
		
		Val = kb.nextInt();
		
		while (Val < 1)
		{
			System.out.println();
			System.out.println("Fail. Try harder!");
			System.out.println();
			System.out.print("Please enter a VALID index: ");
			
			while (!kb.hasNextInt())
			{
				System.out.println();
				System.out.println("Your options are clearly stated. Your failure is disapointing!");
				System.out.println();
				System.out.print("Please enter a VALID index: ");
				kb = new Scanner(System.in);
			}
			Val = kb.nextInt();
		}
		return Val;
	}
	

	//Sorts an array of dates_____________________________________________________
	public static void insertionSort(Date[]array)
	{
		Date key;
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
	}
	
	//Searches for a specific date in an array of dates_____________________________
	public static int linearSearch(Date[]ara,Date target)
	{
		for (int i = 0; i < ara.length; i++)
		{
			if (ara[i].equals(target))
			{
				return i;
			}
		}
		return -1;
	}

}