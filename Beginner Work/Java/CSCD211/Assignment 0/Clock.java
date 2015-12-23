//Creator: Brandon Fowler
//Clock.java(Part of Assignment 0)
//Class:CSCD 211 


public class Clock implements Comparable<Clock>
{
	//Initialize Variables___________________________________________
	private int Hour;
	private int Minute;
	private int Second;
	
	//Constructors___________________________________________________
	
	//DVC
	public Clock()
	{
		this(23,58,00);
	}
	
	//EVC
	public Clock(int H, int M, int S)
	{
		if (H >= 0 && H <= 24)
		{
			this.Hour = H;
		}
		else 
		{
			throw new IllegalArgumentException("This Hour is not Valid!");
		}
		if (M >= 0 && M <= 60)
		{
			this.Minute = M;
		}
		else
		{
			throw new IllegalArgumentException("This Minute is not Valid!");
		}
		if (S >= 0 && S <= 60)
		{
			this.Second = S; 
		}
		else
		{
			throw new IllegalArgumentException("This Second is not Valid!");
		}
	}
	
	//Sets and Gets___________________________________________________
	
	//Set Month
	public void setHour(int val)
	{
		this.Hour = val;
	}
	
	//Set Day
	public void setMinute(int val)
	{
		this.Minute = val;
	}
	
	//Set Year
	public void setSecond(int val)
	{
		this.Second = val;
	}
	
	//Get Month
	public int getHour()
	{
		return this.Hour;
	}

	//Get Day
	public int getMinute()
	{
		return this.Minute;
	}
	
	//Get Year
	public int getSecond()
	{
		return this.Second;
	}
	
	//Overrides________________________________________________________
	
	//Override equals	
	@Override
	public boolean equals(Object obj)
	{
		if (obj.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
		{
			Clock that = (Clock)obj;
			return this.compareTo(that) == 0;
		}
		return false;
	}
	
	//Override toString
	@Override
	public String toString()
	{
		return ""+this.Hour+":"+this.Minute+":"+this.Second;
	}
	
	//Override compareTo
	@Override
	public int compareTo(Clock that)
	{
		if (this.Hour != that.Hour)
		{
			return this.Hour - that.Hour;
		}
		
		else if (this.Minute != that.Minute)
		{
			return this.Minute - that.Minute;
		}
		else
		{
			return this.Second - that.Second;
		}
	}
	
	 
  
}