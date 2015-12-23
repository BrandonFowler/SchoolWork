//Creator: Brandon Fowler
//Date.java(Part of Final Assignment)
//Class:CSCD 210 


public class Date implements Comparable<Date>
{
	//Initialize Variables___________________________________________
	private Month month;
	private Day day;
	private Year year;
	
	//Constructors___________________________________________________
	
	//DVC
	public Date()
	{
		this(1,1,1970);
	}
	
	//EVC
	public Date(int m, int d, int y)
	{
		this.month = new Month(m);
		this.day = new Day(d);
		this.year = new Year(y); 
	}
	
	//Sets and Gets___________________________________________________
	
	//Set Month
	public void setMonth(int val)
	{
		this.month.m = val;
	}
	
	//Set Day
	public void setDay(int val)
	{
		this.day.d = val;
	}
	
	//Set Year
	public void setYear(int val)
	{
		this.year.y = val;
	}
	
	//Get Month
	public int getMonth()
	{
		return this.month.m;
	}

	//Get Day
	public int getDay()
	{
		return this.day.d;
	}
	
	//Get Year
	public int getYear()
	{
		return this.year.y;
	}
	
	//Overrides________________________________________________________
	
	//Override equals	
	@Override
	public boolean equals(Object obj)
	{
		if (obj.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
		{
			Date that = (Date)obj;
			return this.month.m == that.month.m && this.day.d == that.day.d && this.year.y == that.year.y;
		}
		return false;
	}
	
	//Override toString
	@Override
	public String toString()
	{
		return ""+this.month.m+"/"+this.day.d+"/"+this.year.y;
	}
	
	//Override compareTo
	@Override
	public int compareTo(Date that)
	{
		if (this.year.y != that.year.y)
		{
			return this.year.y - that.year.y;
		}
		
		else if (this.month.m != that.month.m)
		{
			return this.month.m - that.month.m;
		}
		else
		{
			return this.day.d - that.day.d;
		}
	}
	
	//Other Methods_____________________________________________________
	
	//isValid method
	//Checks dates to make sure they are valid.
	public static boolean isValid(int month,int day, int year)
	{
		boolean result = false;
		if (month > 0 && month < 13 && year > 1970 && year < 2100)
		{
			switch(month)
			{
				case 2:
					if (day > 0 && day < 29)
					{
						result = true;
					}
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					if (day > 0 && day < 31)
					{
						result = true;
					}
					break;
				default:
					if (day > 0 && day < 32)
					{
						result = true;
					}
					break;
			}
		}
		return result;
	}

 
  
}