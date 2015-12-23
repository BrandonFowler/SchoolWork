//Creator: Brandon Fowler
//DnaTester.java(Part of Assignment 1)
//Class:CSCD 211 


import java.io.*;

/**
DnaTester Class. Tests Dna Class
testing dish.    
<p><b>
Extra Credit: 
</b><pre> 
N/A
</pre><b>  
History: 
</b><pre> 
Created 4/16/2012, No Changes made since.
</pre>  
@author	Brandon Fowler 
*/
public class DnaTester
{
	/**
	Main for DnaTester. Calls methods from Dna Class to run simulation.   
	<p><b>
	Extra Credit: 
	</b><pre> 
	N/A
	</pre><b>  
	History: 
	</b><pre> 
	Created 4/16/2012, No Changes made since.
	</pre>  
	@author	Brandon Fowler 
	*/
	public static void main(String []args) throws FileNotFoundException
	{
		Dna Test = new Dna(10,10,"dna.txt","dna_results.txt");
		Test.buildSample();
		Test.runTest();
	}
}