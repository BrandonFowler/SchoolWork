//Name: Brandon Fowler
//Class: CSCD 300
//Part of Assignment 5


import java.io.*;
import java.util.*;

//Contains an Array of linked lists(Hash Table), also contains 
//file scanner, print writer, and counters for hashing method 
//fill functionality
public class freqTable
{
	protected File inFile;
	protected PrintWriter outFile;
	protected String book;//Holds entire book as a String
	protected String[] words;//Holds entire book as an Array of Strings
	protected LinkedWordList[] hashTable;//Hash Table
	protected int hashCount1;//Counts filled buckets for hashing function 1
	protected int hashCount2;//Counts filled buckets for hashing function 2
	protected int hashCount3;//Counts filled buckets for hashing function 3
	protected int hashCount4;//Counts filled buckets for hashing function 4
	protected int hashCount5;//Counts filled buckets for hashing function 5
	
	//Constructor for Hash My Table
	public freqTable(String FileName)
	{
		book = "";//Initialize
		
		//Initialize Counts
		this.hashCount1 = 0;
		this.hashCount2 = 0;
		this.hashCount3 = 0;
		this.hashCount4 = 0;
		this.hashCount5 = 0;
		
		//Open File and Builds String Line by Line
		try
		{
			Scanner inFile = new Scanner(new File(FileName));
			while(inFile.hasNextLine())
			{
				book = book+inFile.nextLine();
			}
			inFile.close();
		}
		catch(Exception e)
		{
			System.out.println("Error: Input File Not Found");
		}
		
		book = book.replaceAll("\\.|\\]|\\[|[0-9]|,|\\?|:|\\(|\\)|;|!|-|\""," ");//Replaces all punctuation with whitespace
		book = book.replaceAll("'","");//Replace single quotes
		book = book.toLowerCase();//converts to lowercase
		String delims = "\\s+";//Set delimiter
		this.words = book.split(delims);//Splits entire book into an array using whitespace as a delimiter
		
		//Runs all hashing functions
		hash1();
		hash2();
		hash3();
		hash4();
		hash5();
	}
	
	//Clears Hash Table, then hashes entire book and tracks frequencies along with filled bucket count
	public void hash1()
	{
		String word;
		char[] chars;
		int index;
		this.hashTable = null;
		this.hashTable = new LinkedWordList[7949];
		for(int i = 0; i < this.words.length; i++)
		{
			word = this.words[i];
			word = word.toLowerCase();
			chars = word.toCharArray();
			int sum = 0;
			
			int c = 0;
			for( c = 0; c < chars.length; c++)
			{
				sum = sum+chars[c];
			}
			//Increases Uniqueness
			sum = sum+(chars[0]);
			sum = sum+(chars[c-1]);
			sum = sum+(chars[c-1]*10);
			sum = sum+(chars[0]%7);
			sum = sum+(chars[c-1]%5);
			sum = sum+(chars[c-1]%3);
			
			
			index = sum%7949;
			
			if(this.hashTable[index] == null)
			{
				this.hashTable[index] = new LinkedWordList();
				this.hashTable[index].addLast(word);
				this.hashCount1++; 
			}
			else
			{
				Node cur;
				for(cur = this.hashTable[index].head; cur.next != null && cur.word.compareTo(word) != 0; cur = cur.next);
				
				if(cur.word.compareTo(word) == 0)
				{
					cur.freq++;
				}
				else
				{
					this.hashTable[index].addLast(word);
					this.hashTable[index].sort();
				}
				
			}

		}

	}
	
	//Clears Hash Table, then hashes entire book and tracks frequencies along with filled bucket count
	public void hash2()
	{
		String word;
		char[] chars;
		int index;
		this.hashTable = null;
		this.hashTable = new LinkedWordList[7949];
		for(int i = 0; i < this.words.length; i++)
		{
			word = this.words[i];
			word = word.toLowerCase();
			chars = word.toCharArray();
			int sum = 0;
			
			int c = 0;
			for( c = 0; c < chars.length; c++)
			{
				//Increases Uniqueness
				sum = sum+chars[c];
				sum = sum+(chars[c]*10);
				sum = sum+(chars[c]%10);
			}
			
			
			index = sum%7949;
			
			if(this.hashTable[index] == null)
			{
				this.hashTable[index] = new LinkedWordList();
				this.hashTable[index].addLast(word);
				this.hashCount2++; 
			}
			else
			{
				Node cur;
				for(cur = this.hashTable[index].head; cur.next != null && cur.word.compareTo(word) != 0; cur = cur.next);
				
				if(cur.word.compareTo(word) == 0)
				{
					cur.freq++;
				}
				else
				{
					this.hashTable[index].addLast(word);
					this.hashTable[index].sort();
				}
				
			}

		}

	}
	
	//Clears Hash Table, then hashes entire book and tracks frequencies along with filled bucket count
	public void hash3()
	{
		String word;
		char[] chars;
		int index;
		this.hashTable = null;
		this.hashTable = new LinkedWordList[7949];
		for(int i = 0; i < this.words.length; i++)
		{
			word = this.words[i];
			word = word.toLowerCase();
			chars = word.toCharArray();
			int sum = 0;
			
			int c = 0;
			for( c = 0; c < chars.length; c++)
			{
				//Basic
				sum = sum+chars[c];
			}
			
			index = sum%7949;
			
			if(this.hashTable[index] == null)
			{
				this.hashTable[index] = new LinkedWordList();
				this.hashTable[index].addLast(word);
				this.hashCount3++; 
			}
			else
			{
				Node cur;
				for(cur = this.hashTable[index].head; cur.next != null && cur.word.compareTo(word) != 0; cur = cur.next);
				
				if(cur.word.compareTo(word) == 0)
				{
					cur.freq++;
				}
				else
				{
					this.hashTable[index].addLast(word);
					this.hashTable[index].sort();
				}
				
			}

		}

	}
	
	//Clears Hash Table, then hashes entire book and tracks frequencies along with filled bucket count
	public void hash4()
	{
		String word;
		char[] chars;
		int index;
		this.hashTable = null;
		this.hashTable = new LinkedWordList[7949];
		for(int i = 0; i < this.words.length; i++)
		{
			word = this.words[i];
			word = word.toLowerCase();
			chars = word.toCharArray();
			int sum = 0;
			
			int c = 0;
			for( c = 0; c < chars.length; c++)
			{
				//Increases Uniqueness
				sum = sum+chars[c];
				sum = sum+(chars[c]*7+9*7%3);
				sum = sum+(chars[c]*100);
				sum = sum+(chars[c]%13);
			}
			
			index = sum%7949;
			
			if(this.hashTable[index] == null)
			{
				this.hashTable[index] = new LinkedWordList();
				this.hashTable[index].addLast(word);
				this.hashCount4++; 
			}
			else
			{
				Node cur;
				for(cur = this.hashTable[index].head; cur.next != null && cur.word.compareTo(word) != 0; cur = cur.next);
				
				if(cur.word.compareTo(word) == 0)
				{
					cur.freq++;
				}
				else
				{
					this.hashTable[index].addLast(word);
					this.hashTable[index].sort();
				}
				
			}

		}
		
	}
	
	//Clears Hash Table, then hashes entire book and tracks frequencies along with filled bucket count
	public void hash5()
	{
		String word;
		char[] chars;
		int index;
		this.hashTable = null;
		this.hashTable = new LinkedWordList[7949];
		for(int i = 0; i < this.words.length; i++)
		{
			word = this.words[i];
			word = word.toLowerCase();
			chars = word.toCharArray();
			int sum = 0;
			
			int c = 0;
			for( c = 0; c < chars.length; c++)
			{
				//Increases Uniqueness(Works Best)
				sum = sum+chars[c];
				sum = sum+(chars[c]*10);
				sum = sum+(chars[c]%10);
				sum = sum+(chars[c]*15);
				sum = sum-(chars[c]*7);
				sum = sum+(chars[c]%5);
				sum = sum+(chars[c]%13);
				sum = sum+(chars[c]%17);
				sum = sum+(chars[c]%83);
				sum = sum+(chars[c]%11);
				sum = sum+(chars[c]%23);
				sum = sum+(chars[c]%183);
			}
			
			index = sum%7949;
			
			if(this.hashTable[index] == null)
			{
				this.hashTable[index] = new LinkedWordList();
				this.hashTable[index].addLast(word);
				this.hashCount5++; 
			}
			else
			{
				Node cur;
				for(cur = this.hashTable[index].head; cur.next != null && cur.word.compareTo(word) != 0; cur = cur.next);
				
				if(cur.word.compareTo(word) == 0)
				{
					cur.freq++;
				}
				else
				{
					this.hashTable[index].addLast(word);
					this.hashTable[index].sort();
				}
				
			}

		}
				
	}
	
	//Prints results to screen, and writes them to output
	public void printNWrite()
	{
		int count = 0;
		Node cur;
		int maxFreq = 0;
		
		try
		{
			outFile = new PrintWriter("output");
		}
		catch(Exception e)
		{
			System.out.println("Error: Unable to open output file!");
		}
		
		for(count = 0; count < this.hashTable.length; count++)
		{
			if(this.hashTable[count] != null)
			{
				for(cur = this.hashTable[count].head; cur!=null; cur = cur.next)
				{
					if(cur.freq > maxFreq)
					{
						maxFreq = cur.freq;
					}
				}
			}
		}
		
		while(maxFreq > 0)
		{
			for(count = 0; count < this.hashTable.length; count++)
			{
				if(this.hashTable[count] != null)
				{
					for(cur = this.hashTable[count].head; cur!=null; cur = cur.next)
					{
						if(cur.freq == maxFreq)
						{
							System.out.print("Word: "+cur.word+" Frequency: "+cur.freq);
							System.out.println();
							outFile.print("Word: "+cur.word+" Frequency: "+cur.freq);
							outFile.println();
						}
					}
				}
			}
			maxFreq--;
		}
		
		System.out.println();
		System.out.println("************************************");
		System.out.println("Hashing function efficiency results:");
		System.out.println();
		outFile.println();
		outFile.println("************************************");
		outFile.println("Hashing function efficiency results:");
		outFile.println();
		
		
		double result1 = this.hashCount1/7949.0;
		System.out.println("Hash method one, fill factor: "+result1);
		outFile.println("Hash method one, fill factor: "+result1);

		
		double result2 = this.hashCount2/7949.0;
		System.out.println("Hash method two, fill factor: "+result2);
		outFile.println("Hash method two, fill factor: "+result2);

		
		double result3 = this.hashCount3/7949.0;
		System.out.println("Hash method three, fill factor: "+result3);
		outFile.println("Hash method three, fill factor: "+result3);

		
		double result4 = this.hashCount4/7949.0;
		System.out.println("Hash method four, fill factor: "+result4);
		outFile.println("Hash method four, fill factor: "+result4);

		
		double result5 = this.hashCount5/7949.0;
		System.out.println("Hash method five, fill factor: "+result5);
		outFile.println("Hash method five, fill factor: "+result5);
		
		outFile.close();
	}
}