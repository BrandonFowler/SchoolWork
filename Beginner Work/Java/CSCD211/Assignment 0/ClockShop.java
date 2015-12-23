//Creator: Brandon Fowler
//ClockShop.java(Part of Assignment 0)
//Class:CSCD 211 

import java.io.*;

public class ClockShop
{
	private Clock [] clocks;

	public ClockShop()
	{

		this.clocks = new Clock[1];
		this.clocks[0] = new Clock(0,0,0);
	}

	public void fillClockShop(String Name)
	{
		int count = 0;
		int count2 = 0;
		Clock[] ara;
		try
		{
  
  		FileInputStream fstream = new FileInputStream(Name);
  		DataInputStream in = new DataInputStream(fstream);
 	   BufferedReader br = new BufferedReader(new InputStreamReader(in));
  		String strLine;
		
		
  		while ((strLine = br.readLine()) != null)   
		{
  			String A = strLine;
			count++;
 		}		
 		in.close();
    	}
	 	catch (Exception e)
	 	{
		System.out.println();
  		System.err.println("Error reading the file; something went wrong! Please Run Pragram again!");
  	 	}
		ara = new Clock[count];
		try
		{
  
  		FileInputStream fstream = new FileInputStream(Name);
  		DataInputStream in = new DataInputStream(fstream);
 	   BufferedReader br = new BufferedReader(new InputStreamReader(in));
  		String strLine;
		
		
  		while ((strLine = br.readLine()) != null)   
		{
  			String A2 = strLine;
			String[] S2 = A2.split("\\.");
			String m3 = S2[0];
			String d3 = S2[1];
			String y3 = S2[2];
			int m4 = Integer.parseInt(m3);
			int d4 = Integer.parseInt(d3);
			int y4 = Integer.parseInt(y3);
		
			
			ara[count2] = new Clock(m4,d4,y4);
			count2++;
		
 		}		
 		in.close();
    	}
	 	catch (Exception e)
	 	{
  		System.err.println("!");
  	 	}
		
		this.clocks = ara;

	}

	public void sortClocks()
	{
		int position = 0;
		int indexSmallest = 0;
		int cur = 0;
		Clock temp;
		
		for (position = 0; position < this.clocks.length; position++)
		{
			indexSmallest = position;
			for (cur = position + 1; cur < this.clocks.length; cur++)
			{
				if (this.clocks[cur].compareTo(this.clocks[indexSmallest]) < 0);
				{
					indexSmallest = cur;
				}
			}
			temp = this.clocks[position];
			this.clocks[position] = this.clocks[indexSmallest];
			this.clocks[indexSmallest] = temp;
		}
	}

	public int findClock(Clock target)
	{
		int high = this.clocks.length-1;
		int low = 0;
		int mid = 0;
		boolean found = false;
		
		while (!found && low <= high)
		{
			mid = (low+high)/2;
			if (this.clocks[mid].compareTo(target) > 0 )
			{
				high = mid-1;
			}
			else if (this.clocks[mid].compareTo(target) < 0)
			{
				low = mid+1;
			}
			else
			{
				found = true;
			}
			
		}
		
		if (found == true)
			{
				return mid;
			}
		else
			{
				return -1;
			}

	}

	@Override
	public String toString()
	{
 
		String result = "";
		
		for (int i = 0; i < this.clocks.length; i++)
		{
			result = result+this.clocks[i].toString()+"\n";
		}

		return result;
	}

	public Clock getClock(int index)
	{
		if (this.clocks.length >= index)
		{
			Clock result = this.clocks[index];
			return result;
		}
		else
		{
			throw new IllegalArgumentException("Index out of range!");
		}

	}

	public void setClock(Clock newClock, int index)
	{
		if (findClock(this.clocks[index]) != -1)
		{
			this.clocks[index] = newClock;
		}
		else
		{
			throw new IllegalArgumentException("Index out of range!");
		}
	}

	public void writeClocksToFile(String outputFileName)throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter(outputFileName);
		for (int i = 0; i < this.clocks.length; i++)
			{
				out.println(this.clocks[i]);					
			}
		out.close();
	}
}