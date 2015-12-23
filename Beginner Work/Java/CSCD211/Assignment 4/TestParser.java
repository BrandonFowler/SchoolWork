//Creator: Brandon Fowler
//TestParser.java(Part of Assignment 4)
//Class:CSCD 211 


import java.io.*;

public class TestParser
{

	//Main
	//Reads file intput, and passes strings to parser line by line
	public static void main(String[] args) throws IOException
	{
		
		FileInputStream fstream = new FileInputStream("infix.txt");
  		DataInputStream in = new DataInputStream(fstream);
 	   BufferedReader br = new BufferedReader(new InputStreamReader(in));
  		String strLine;
		
		
  		while ((strLine = br.readLine()) != null)  
		{
  			Parser.parse(strLine);
 		}
				
 		in.close();

	}

}