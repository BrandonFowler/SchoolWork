//Creator: Brandon Fowler
//Day.java(Part of Final Assignment)
//Class:CSCD 210 


public class Day
{

	//Initialize Variables______________________________________________
	public int d;
	
	//Constructors______________________________________________________
	
	//DVC
	public Day()
	{
		this(1);
	}
	
	//EVC
	public Day(int day)
	{
		this.d = day;
	}
	
	//Sets and Gets_____________________________________________________
	
	//Set
	public void setD(int value)
	{
		this.d = value;
	}
	
	//Get
	public int getD()
	{
		return this.d;
	}
	
	
	//Overrides_________________________________________________________
	
	//toString Override
	@Override
	public String toString()
	{
		String result = ""+this.d;
		return result;
	}
	
	//equals Override
	@Override
	public boolean equals(Object obj)
	{
		if(obj.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
		{
			Day that = (Day)obj;
			return this.d == that.d;
		}
		return false;
	}

	
}