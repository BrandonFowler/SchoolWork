//Name: Brandon Fowler
//File: inFixPostFixTester.java(Part of Lab 4)
//Class: CSCD 300


//Simple tester to run calculations
public class inFixPostFixTester
{
	//Main
	public static void main(String[] args)
	{
		inFixPostFix calculator = new inFixPostFix();
		calculator.loadFile();
		calculator.in2PostFix();
	}
}