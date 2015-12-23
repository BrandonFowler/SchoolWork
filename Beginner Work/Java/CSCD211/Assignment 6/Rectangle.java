//Rectangle.java(Part of Assignment 6)
//Creator: Brandon Fowler
//Class: CSCD 211

public class Rectangle extends Parallelogram
{
	//Default Constructor
	public Rectangle()
	{
		super();
		this.Name = ("Rectangle");
	}
	
	//Constructor based on given Parallelogram
	public Rectangle(Parallelogram that)
	{
		super();
		this.Name = ("Rectangle");
		this.Side1 = that.Side1;
		this.Side2 = that.Side2;
		this.Side3 = that.Side3;
		this.Side4 = that.Side4;
		this.x1 = that.x1;
		this.x2 = that.x2;
		this.x3 = that.x3;
		this.x4 = that.x4;
		this.y1 = that.y1;
		this.y2 = that.y2;
		this.y3 = that.y3;
		this.y4 = that.y4;
	}
	
	//Tests to see if a parallelogram meets the requirments of a rectangle
	public static boolean isRectangle(Parallelogram test)
	{
		if(test.Side1 == test.Side3 && test.Side2 == test.Side4 && test.x1 == test.x4 && test.x2 == test.x3)
		{
			return true;
		}
		return false;
	}
}