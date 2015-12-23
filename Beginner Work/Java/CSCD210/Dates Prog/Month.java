//Creator: Brandon Fowler
//Month.java(Part of Final Assignment)
//Class:CSCD 210 


public class Month
{

	//Initialize Variables______________________________________________
	private final static String[] monthArray = {"January","Febuary","March","April","May","June","July","August","September","October","November","December"};
	public int m;
	
	//Constructors______________________________________________________
	
	//DVC
	public Month()
	{
		this(1);
	}
	
	//EVC
	public Month(int month)
	{
		this.m = month;
	}
	
	//Sets and Gets_____________________________________________________
	
	//Set
	public void setM(int value)
	{
		this.m = value;
	}
	
	//Get
	public int getM()
	{
		return this.m;
	}
	
	
	//Overrides_________________________________________________________
	
	//toString Override
	@Override
	public String toString()
	{
		String result = monthArray[m-1];
		return result;
	}
	
	//equals Override
	@Override
	public boolean equals(Object obj)
	{
		if(obj.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
		{
			Month that = (Month)obj;
			return this.m == that.m;
		}
		return false;
	}

	
}