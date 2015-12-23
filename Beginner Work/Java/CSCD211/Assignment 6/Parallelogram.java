//Parallelogram.java(Part of Assignment 6)
//Creator: Brandon Fowler
//Class: CSCD 211


import java.math.*;

public class Parallelogram extends FourSidedShape
{
	//Values to hold side lengths, and initial coordanites
	protected double Side1;
	protected double Side2;
	protected double Side3;
	protected double Side4;
	protected double x1;
	protected double x2;
	protected double x3;
	protected double x4;
	protected double y1;
	protected double y2;
	protected double y3;
	protected double y4;
	
	//Default Constructor
	public Parallelogram()
	{
		super();
		this.Name = ("Parallelogram");
		this.Side1 = 0.0;
		this.Side2 = 0.0;
		this.Side3 = 0.0;
		this.Side4 = 0.0;
		this.x1 = 0.0;
		this.x2 = 0.0;
		this.x3 = 0.0;
		this.x4 = 0.0;
		this.y1 = 0.0;
		this.y2 = 0.0;
		this.y3 = 0.0;
		this.y4 = 0.0;
	}
	
	//Constructor based on given coordanates
	public Parallelogram(double x1, double x2, double x3, double x4, double y1, double y2, double y3, double y4)
	{
		super();
		this.Name = ("Parallelogram");
		
		//Calculate side lengths-----------------------------------------
		if (Math.abs(Math.abs(x1)-Math.abs(x2)) > Math.abs(Math.abs(y1)-Math.abs(y2)))
		{
			this.Side1 = Math.abs(Math.abs(x1)-Math.abs(x2));
		}
		else
		{
			this.Side1 = Math.abs(Math.abs(y1)-Math.abs(y2));
		}
		if (Math.abs(Math.abs(x2)-Math.abs(x3)) > Math.abs(Math.abs(y2)-Math.abs(y3)))
		{
			this.Side2 = Math.abs(Math.abs(x2)-Math.abs(x3));
		}
		else
		{
			this.Side2 = Math.abs(Math.abs(y2)-Math.abs(y3));
		}
		if(Math.abs(Math.abs(x3)-Math.abs(x4))>Math.abs(Math.abs(y3)-Math.abs(y4)))
		{
			this.Side3 = Math.abs(Math.abs(x3)-Math.abs(x4));
		}
		else
		{
			this.Side3 = Math.abs(Math.abs(y3)-Math.abs(y4));
		}
		if(Math.abs(Math.abs(x4)-Math.abs(x1))>Math.abs(Math.abs(y4)-Math.abs(y1)))
		{
			this.Side4 = Math.abs(Math.abs(x4)-Math.abs(x1));
		}
		else
		{
			this.Side4 = Math.abs(Math.abs(y4)-Math.abs(y1));
		}
		//End side calculations-----------------------------------------
		
		//Original coordanite values
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.x4 = x4;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
		this.y4 = y4;
		
	}
	
	//isParallelogram method
	//Checks to see if coordantites given make a valid parallelogram
	public static boolean isParallelogram(double x1, double x2, double x3, double x4, double y1, double y2, double y3, double y4)
	{
		if(x2>x1 && x3<x4 || x2<x1 && x3>x4 || y1>y4 && y2<y3 || y1<y4 && y2>y3)
		{
			return false;
		}
		if(x1==x3 || y1==y3)
		{
			return false;
		}
		return true;
	}
	
	//Overrides inherited area method
	@Override
	public double area()
	{
		return this.Side1*this.Side2;
	}
	
	//Overrides inherited perimeter method
	@Override
	public double perimeter()
	{
		return this.Side1+this.Side2+this.Side3+this.Side4;
	}
	
	//Compare to method to compare two Parallelograms
	//Used double to make it simpler to calculate
	public double compareTo(Parallelogram that)
	{
		if(this.Name.compareTo(that.Name)==0)
		{
			return this.area()-that.area();
		}
		return this.Name.compareTo(that.Name);
	}
	
	//Overrides inheridted toString method
	@Override
	public String toString()
	{
		return ("Name: "+this.Name+", Area: "+area()+", Perimeter: "+perimeter()+", Point1: ("+x1+","+y1+")"+", Point2: ("+x2+","+y2+")"+", Point3: ("+x3+","+y3+")"+", Point4: ("+x4+","+y4+")");
	}
	
	
	
}	