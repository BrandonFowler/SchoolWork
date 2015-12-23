import java.io.*; //FileNotFoundException

public class ClockShopTester
{

	public static void main(String [] args) throws FileNotFoundException
	{
		//create a ClockShop
		ClockShop rolexClockShop = new ClockShop();

		//display contents (there aren't any yet)
		System.out.println("Contents of brand new ClockShop:");
		System.out.println(rolexClockShop);

		//fill shop with Clocks
		rolexClockShop.fillClockShop("clocks.txt");
		System.out.println("Contents of ClockShop after filling with Clocks:");
		System.out.println(rolexClockShop);

		//attempt to retrieve a Clock
		try
		{
			System.out.println("Retrieving Clock 7, it's time is: " + rolexClockShop.getClock(7));
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("There is no Clock 7 in the ClockShop!");

		}

		//assign a new Clock
		System.out.println("Assigning a new Clock to location 0");
		rolexClockShop.setClock(new Clock(11, 11, 11), 0);
		System.out.println("Retrieving Clock 0, it's time is: " + rolexClockShop.getClock(0));

		//sort the Clocks
		System.out.println("Now sorting the Clocks...");
		rolexClockShop.sortClocks();
		System.out.println("The resulting array of Clocks after sorting:");
		System.out.println(rolexClockShop);

		//look for a Clock that might not be there
		System.out.println("Now searching for a Clock with a time of 0.0.0");
		int location = rolexClockShop.findClock(new Clock(0, 0, 0));
		if (location == -1)
			System.out.println("A Clock with time 0.0.0 does not exist in the ClockShop");
		else
			System.out.println("Clock with time 0.0.0 found at location: " + location);

		//look for a Clock that should be there
		System.out.println("Now searching for a Clock with a time of 11.11.11");
		location = rolexClockShop.findClock(new Clock(11, 11, 11));
		if (location == -1)
			System.out.println("A Clock with time 11.11.11 does not exist in the ClockShop");
		else
			System.out.println("Clock with time 11.11.11 found at location: " + location);


		//write the clocks to a file
		System.out.println("Now writing clocks to file...");
		rolexClockShop.writeClocksToFile("rolex_clocks.txt");



		System.out.println("\n\nClockShopTester class complete");

	}

}