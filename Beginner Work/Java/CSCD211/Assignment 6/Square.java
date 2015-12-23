//Square.java(Part of Assignment 6)
//Creator: Brandon Fowler
//Class: CSCD 211


public class Square extends Rectangle
{
	//Default Constructor
	public Square()
	{
		super();
		this.Name = ("Square");
	}
	
	//Constructs Square based on given Parallelogram
	public Square(Parallelogram that)
	{
		super();
		this.Name = ("Square");
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
	
	//Checks to see if given parallelogram meets the requirements of a square
	public static boolean isSquare(Parallelogram test)
	{
		if(test.Side1 == test.Side2 && test.Side1 == test.Side3 && test.Side1 == test.Side4)
		{
			return true;
		}
		return false;
	}
}