//Name: Brandon Fowler
//Class: CSCD 300
//Part of Assignment 5


import java.io.*;
import java.util.*;

//Simple Tester Class
public class freqTableTester
{	
	//Main
	public static void main(String[] args)
	{
		String FileName = "";
		System.out.print("Enter file name: ");
		Scanner input = new Scanner(System.in);//Get input from user for file name
		FileName = input.next();
		System.out.println("Reading input file, and hashing words and frequencies. Please wait!");
		freqTable table = new freqTable(FileName);//Make a hash table
		table.printNWrite();//Print and Write results
	}
}