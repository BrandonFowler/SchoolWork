import java.io.*;
import java.util.*;
import java.math.*;

//__________________________________________________________________
//Truck class
//-----------
//Constructs Truck objects and contains methods to manipulate Truck Objects
//-----------
public class Truck
{
	protected String driver;//Drivers Name
	protected int max;//Max amount of packages truck can carry
	protected File fi;//Holds input file
	protected Scanner filein;//Scans input file
	protected LinkedList loadedPackages;//Holds packages that are loaded onto truck 
	protected LinkedList loadingDock;//Contains all packages created from input file
	protected String totalWeight;//Holds total weight of packages on truck
	protected PrintWriter outPrint;//Writes to file
	protected int count;//Keeps track of number of loaded packages
	
	//_______________________________________________________________
	//Truck Constructor
	//-----------------
	//Constructs a Truck Object, opens and reads input file
	//-----------------
	public Truck(String file)
	{
	
		this.loadedPackages = new LinkedList();//Initialize
		this.loadingDock = new LinkedList();//Initialize
		this.count = 0;//Initialize
	
		this.fi = new File(file);
		
		try//try to read file
		{	
			this.filein = new Scanner(this.fi);
		}
		catch(Exception e)
		{
			System.out.println("Error! No file found!");
		}
		this.driver = filein.nextLine();//Gets driver name
		this.max = filein.nextInt();//Gets truck capacity
		filein.nextLine();//Close Buffer
	}
	//End Truck Constructor
	//_______________________________________________________________
	
	
	//_______________________________________________________________
	//Load Method
	//-----------
	//Opens input file, reads data, fills loadingDock list with input data, checks for valid packages,
	//loads valid packages onto loadedPackages list, calls sort method
	//-----------  
	public void load()//Start load------------------------------------
	{	
		Scanner inFile = null;//Initialize
		
		try//Try to read file
		{
			inFile = new Scanner(this.fi);
		}
		catch(Exception e)
		{
			System.out.println("File Handling Error!");
		}
		
		inFile.nextLine();//Skip driver name
		inFile.nextLine();//Skip truck capacity
		
	   String Tn = null;//Initialize to hold tracking number
		int We = 0;//Initialize to hold weight
		int Le = 0;//Initialize to hold Length
		int Wi = 0;//Initialize to hold Width
		int He = 0;//Initialize to hold Height
		int Tp = 0;//Initialize to hold Package type
		Box tempB = null;//Initialize to temporarily hold a Box object
		Letter tempL = null;//Initialize to temporarily hold a Letter object
		
		while(inFile.hasNextLine())//Checks for end of file
		{
			Tn = inFile.nextLine();//Gets tracking number
			
			if(Integer.parseInt(Tn)%10==1||Integer.parseInt(Tn)%10!=1 && Integer.parseInt(Tn)%10!=0)//Checks package type, allows for invalid packages to be made into box type for now
			{	
				Tp = 1;//Sets package type
				try
				{
					We = Integer.parseInt(inFile.nextLine());//Gets Weight
				}
				catch(Exception PackageException)
				{
					We = -1;//Marks invalid non int input
				}
				try
				{
					Le = Integer.parseInt(inFile.nextLine());//Gets Length
				}
				catch(Exception PackageException)
				{
					Le = -1;//Marks invalid non int input
				}
				try
				{
					Wi = Integer.parseInt(inFile.nextLine());//Gets Width
				}
				catch(Exception PackageException)
				{
					Wi = -1;//Marks invalid non int input
				}
				try
				{
					He = Integer.parseInt(inFile.nextLine());//Gets Height
				}
				catch(Exception PackageException)
				{
					He = -1;//Marks invalid non int input
				}
				
				tempB = new Box(We,He,Le,Wi,Tn,Tp);//Creates new Box Object with input data

				loadingDock.add(tempB);//Adds new Box to loadingDock list

			}
			else//Not a box, and not invalid(Must be Letter)
			{
				Tp = 0;//Set package type
				try
				{
					We = Integer.parseInt(inFile.nextLine());//Gets Weight
				}
				catch(Exception PackageException)
				{
					We = -1;//Marks invalid non int input
				}
				try
				{
					Le = Integer.parseInt(inFile.nextLine());//Gets Length
				}
				catch(Exception PackageException)
				{
					Le = -1;//Marks invalid non int input
				}
				try
				{
					Wi = Integer.parseInt(inFile.nextLine());//Gets Width
				}
				catch(Exception PackageException)
				{
					Wi = -1;//Marks invalid non int input
				}
				
				tempL = new Letter(We,Le,Wi,Tn,Tp);//Creates new Letter object with input data
				
				loadingDock.add(tempL);//Adds new Letter to LoadingDock list

			}
			
		}
		
		validate();//Checks loadingDock list and marks packages as valid or invalid
		
		Node start = new Node();//Initialize for use in for loop
		
		
		for(start = loadingDock.head; start!=null; start=start.next)//Moves through loadingDock list, package by package
		{
			if(start.data.trackingNumber%10!=1 && start.data.trackingNumber%10!=0 || start.data.trackingNumber < 0)//Checks for bad tracking numbers
			{
				start.data.valid = 0;//Marks package invalid
				start.data.loaded = 0;//Marks as package unloaded
				start.data.type = -1;//Marks as Unknown package
				start.data.status = "Not Loaded: Unknown Package type";//Package status description
			}
			
			if(count == max)//Checks if truck is full
			{
				start.data.valid = 0;//Marks package invalid
				start.data.status = "Not Loaded: Truck Full";//Package status description
			}
			else
			{
				if (start.data.valid == 1)//Package is valid
				{
					loadedPackages.add(start.data);//Adds package to loadedPackages list
					start.data.loaded = 1;//Marks package as loaded
					start.data.status = "Loaded";//Package status description
					this.count++;//Keeps track of number of packages on truck
				}
			}
		}
		
		int totalPounds = 0;//Box weight on truck
		int totalOunces = 0;//Letter Weight on truck
		
		for(start = loadedPackages.head; start!=null; start = start.next)//Moves through LoadedPackages, package by package
		{
			if(start.data.type==1)//Check if Box
			{
				totalPounds+=start.data.weight;//Calulates total Box weight
			}
			else//Letter
			{
				totalOunces+=start.data.weight;//Calculates total Letter weight
			}
		}
		
		totalWeight = ""+totalPounds+" pounds "+totalOunces+" Ounces";//Total Weight on truck recorded
		
		sort();//Sorts Packages in loadedPackages list(On truck)
		
	}
	//End load method
	//_________________________________________________________________________
	
	
	//_________________________________________________________________________
	//Validate method
	//---------------
	//Moves through loadingDock list Node by Node, checks validity factors of Package objects,
	//marks Packages as valid or invalid, records status descriptions
	//--------------- 
	public void validate()
	{
		Node cur = new Node();//Initialize for use in for loop
		
		for(cur = loadingDock.head; cur!=null; cur=cur.next)//Moves through LoadingDock, package by package
		{
			if(cur.data.type == 1)//Check if Box
			{
				if(cur.data.weight < 0 || cur.data.length < 0 || cur.data.height < 0 || cur.data.width < 0)//Check for packages marked invalid data
				{
					cur.data.valid = 0;//Set as not valid
					cur.data.status = "Not Loaded: Invalid information";//Status descrition
				}
				if(cur.data.weight > 100)//Check if Box is too heavy
				{
					cur.data.valid = 0;//Set as not valid
					cur.data.status = "Not Loaded: To Heavy";//Status description
				}
				if(cur.data.length > 36 || cur.data.width > 36 || cur.data.height > 36)//Checks if Box is too big
				{
					cur.data.valid = 0;//Set as not valid
					cur.data.status = "Not Loaded: To Big";//Status description
				}
			}
			else//(Letter)
			{
				if(cur.data.weight < 0 || cur.data.length < 0 || cur.data.width < 0)//Checks is Package is marked as invalid input
				{
					cur.data.valid = 0;//Mark invalid
					cur.data.status = "Not Loaded: Invalid information";//Status
				}
				if(cur.data.weight > 24)//Check if too heavy
				{
					cur.data.valid = 0;//Mark invalid
					cur.data.status = "Not Loaded: To Heavy";//Status
				}
				if(cur.data.length > 18 || cur.data.width > 18)//Check if to big
				{
					cur.data.valid = 0;//Mark invalid
					cur.data.status = "Not Loaded: To Big";//Status
				}
			}
		} 
	}
	//End Validate method
	//___________________________________________________________________________________
	
	
	//___________________________________________________________________________________
	//Sort method
	//-----------
	//Moves through loadedPackages list, and sorts Packages objects by type then by weight
	//Letters before Boxes
	//-----------
	public void sort()
	{
		Node cur;//Used in for loop
		Node prev = null;//Initialize for use in for loop
		Node prev2 = null;//Initialize for use in for loop
		Node smallest = null;//Initialize for use in for loop
		Node temp;//Temporarily points to nodes to keep data from being lost
		Node temp2;//Temporarily points to nodes to keep data from being lost
		Node start;//Used in for loop
		
		for(start = loadedPackages.head; start.next!= null; start = start.next)//Moves through LoadedPackages, package by package
		{
			for(cur = start; cur.next != null; cur = cur.next)
			{
				if(cur.data.type > cur.next.data.type)//Checks type of two packages
				{
					prev = cur;//Advance prev
					smallest = cur.next;//Advance smallest
				}
				else if(cur.data.type == cur.next.data.type && cur.data.weight > cur.next.data.weight)//Checks weight of two packages
				{
					prev = cur;//Advance prev
					smallest = cur.next;//Advance smallest
				}
				else//(Next package in list isn't smaller)
				{
					smallest = smallest;//Nothing happens
				}
			}
			
			if(smallest.data.type == loadedPackages.head.data.type && smallest.data.weight == loadedPackages.head.data.weight)//Checks to see is smallest is head of list
			{
				loadedPackages.head = loadedPackages.head;//Nothing happens
			}
			else if(smallest.data.type == 1)//Check if smallest is box
			{
				if(loadedPackages.head.data.type!=1)//Check to see is head of list is a Box
				{
					Node sift;//Used in for loop
					prev2 = loadedPackages.head;
					for(sift = loadedPackages.head.next; sift.data.type!=1 && sift.next != null; sift = sift.next)//Moves LoadedPackages, package by package till it finds the first Box
					{
						prev2 = sift;//Advance prev
					}
				
					temp = smallest.next;
					prev.next = temp;
					smallest.next = sift;
					prev2.next = smallest;
					start = loadedPackages.head;
				}
				else//(Head of list is a Box)
				{
					temp = smallest.next;
					prev.next = temp;
					smallest.next = loadedPackages.head;
					loadedPackages.head = smallest;
					start = loadedPackages.head;
				}
			}
			else//(smallest is Letter)
			{
				temp = smallest.next;
				prev.next = temp;
				smallest.next = loadedPackages.head;
				loadedPackages.head = smallest;
				start = loadedPackages.head;
			}
		}
		
		if(loadedPackages.head.next != null)//Checks is only one package is in list
		{
			if(loadedPackages.head.data.type == loadedPackages.head.next.data.type && loadedPackages.head.data.weight > loadedPackages.head.next.data.weight)//Checks first two packages for sort
			{
				temp = loadedPackages.head.next.next;
				temp2 = loadedPackages.head.next;
				loadedPackages.head.next = temp;
				temp2.next = loadedPackages.head;
				loadedPackages.head = temp2;
			}
		}		
	}
	//End Sort method
	//___________________________________________________________________________
	
	
	//___________________________________________________________________________
	//Unload method
	//-------------
	//Writes all information gatherd in loading, moving, and unloading Packages to a log file,
	//and removes packages for loadedPackages list(Truck)
	//-------------
	public void unload()
	{
		File log = new File("log.txt");//Initialize file
		
		try//Try to access file with PrintWriter
		{
			this.outPrint = new PrintWriter(log);
		}
		catch(Exception e)
		{
			System.out.println("Opening log fail!");
		}
		
		outPrint.println("Driver name: "+this.driver);//Print drivers name
		outPrint.println("Maximum number of packages that truck can carry: "+this.max);//Print number of packages truck can carry
		outPrint.println();
		outPrint.println("PACKAGE LOADING INFORMATION:");
		outPrint.println("----------------------------");
		
		for(Node cur = this.loadingDock.head; cur!=null; cur = cur.next)//Moves through LoadingDock, package by package
		{
			if(cur.data.type == 1)//If Box print data
			{
				outPrint.println("Package Type: Box");
				outPrint.println("Tracking Number: "+cur.data.trackingNumber);
				outPrint.println("Weight: "+cur.data.weight+" Pounds");
				outPrint.println("Length: "+cur.data.length+" Inches");
				outPrint.println("Width: "+cur.data.width+" Inches");
				outPrint.println("Height: "+cur.data.height+" Inches");
				outPrint.println(cur.data.status);
				outPrint.println();
			}
			else if(cur.data.type == -1)//If unknown package print data
			{
				outPrint.println("Package Type: Unknown");
				outPrint.println("Tracking Number: "+cur.data.trackingNumber);
				outPrint.println("Weight: "+cur.data.weight+" Pounds");
				outPrint.println("Length: "+cur.data.length+" Inches");
				outPrint.println("Width: "+cur.data.width+" Inches");
				outPrint.println("Height: "+cur.data.height+" Inches");
				outPrint.println(cur.data.status);
				outPrint.println();
			}
			else//If Letter print data
			{
				outPrint.println("Package Type: Letter");
				outPrint.println("Tracking Number: "+cur.data.trackingNumber);
				outPrint.println("Weight: "+cur.data.weight+" Ounces");
				outPrint.println("Length: "+cur.data.length+" Inches");
				outPrint.println("Width: "+cur.data.width+" Inches");
				outPrint.println(cur.data.status);
				outPrint.println();
			}
		}
		
		outPrint.println("PACKAGE UNLOADING INFORMATION:");
		outPrint.println("------------------------------");
		
		for(Node cur = this.loadedPackages.head; cur!=null; cur = cur.next)//Moves through LoadedPackages, package by package
		{
			if(cur.data.type == 1)//If Box print data
			{
				outPrint.println("Package Type: Box");
				outPrint.println("Tracking Number: "+cur.data.trackingNumber);
				outPrint.println("Weight: "+cur.data.weight+" Pounds");
				outPrint.println("Length: "+cur.data.length+" Inches");
				outPrint.println("Width: "+cur.data.width+" Inches");
				outPrint.println("Height: "+cur.data.height+" Inches");
				outPrint.println();
			}
			else//If letter print data
			{
				outPrint.println("Package Type: Letter");
				outPrint.println("Tracking Number: "+cur.data.trackingNumber);
				outPrint.println("Weight: "+cur.data.weight+" Ounces");
				outPrint.println("Length: "+cur.data.length+" Inches");
				outPrint.println("Width: "+cur.data.width+" Inches");
				outPrint.println();
			}
		}
		
		while(loadedPackages.head != null)//Unloads all packages
		{
			loadedPackages.removeFirst();
		}
		
		outPrint.println("FINAL TRUCK INFORMATION:");
		outPrint.println("------------------------");
		outPrint.println("Packages Delivered: "+this.count);
		outPrint.println("Total weight of packages "+this.totalWeight);
		
		outPrint.close();
	
	}
	//End Unload method
	//___________________________________________________________________________
	
	
	//___________________________________________________________________________
	//Drive method
	//------------
	//Does nothing(Only for show)
	//------------
	public void drive()
	{
		return;
	}
	//End Drive method
	//___________________________________________________________________________

}
//End Truck class
//______________________________________________________________________________