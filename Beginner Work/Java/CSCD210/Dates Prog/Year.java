//Creator: Brandon Fowler
//Year.java(Part of Final Assignment)
//Class:CSCD 210 


public class Year
{

	//Initialize Variables______________________________________________
	public int y;
	
	//Constructors______________________________________________________
	
	//DVC
	public Year()
	{
		this(1970);
	}
	
	//EVC
	public Year(int year)
	{
		this.y = year;
	}
	
	//Sets and Gets_____________________________________________________
	
	//Set
	public void setY(int value)
	{
		this.y = value;
	}
	
	//Get
	public int getY()
	{
		return this.y;
	}
	
	
	//Overrides_________________________________________________________
	
	//toString Override
	@Override
	public String toString()
	{
		String result = ""+this.y;
		return result;
	}
	
	//equals Override
	@Override
	public boolean equals(Object obj)
	{
		if(obj.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
		{
			Year that = (Year)obj;
			return this.y == that.y;
		}
		return false;
	}

	
}