//________________________________________________________________
//Truck Tester Class
//------------------
//Contains Main
//------------------
public class TruckTester
{

	//_____________________________________________________________
	//Main
	//----
	//Run main program by creating a new Truck object and calling methods from Truck class
	//----
	public static void main(String [] args)
	{
        Truck theTruck = new Truck("manifest.txt");
        theTruck.load();
		  theTruck.drive();
		  theTruck.unload();
		  
   
	}
	//End Main
	//______________________________________________________________
}
//End TruckTester
//_________________________________________________________________