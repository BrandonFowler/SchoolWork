//FourSidedShape.java(Part of Assignment 6)
//Creator: Brandon Fowler
//Class: CSCD 211



public abstract class FourSidedShape implements Comparable<FourSidedShape>
{
	//Name of shape
	protected String Name;
	
	//Constructor
	public FourSidedShape()
	{
		this.Name = ("FourSidedShape");
	}
	
	//area method to inherit(not enough info here for more)
	public double area()
	{
		return 0;
	}
	
	//perimeter method to inherit(not enough info here for more)
	public double perimeter()
	{
		return 0;
	}
	
	//Simple compareTo method
	@Override
	public int compareTo(FourSidedShape that)
	{
		return this.Name.compareTo(that.Name);
	}
	
	//Simple toString method
	public String toString()
	{
		return this.Name;
	}
}