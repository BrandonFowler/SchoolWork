//ShapeException.java(Part of Assignment 6)
//Creator: Brandon Fowler
//Class: CSCD 211

//Didn't spend long here. Pretty much just inherits from IllegalArgumentException.
//I know it's not good enough. My tester writes the error message to a file 
//cause I couldn't figure it out here. So I'm expecting to take a grade hit here.
//My program still completes all of the functions expected, but I did it other ways. :(
public class ShapeException extends IllegalArgumentException
{
	public ShapeException(String s)
	{
		super();
	}
}