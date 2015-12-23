

//Student Name: Brandon Fowler
//Class: CSCD 300
//Part of Lab 3
//Sieve Class

//Note: I used <Integer> When declaring my lists and variables, and it still says 
//that is uses "Unsafe Operations" during compile, not sure why it still says 
//this, but the program runs fine.


import java.util.*;

//Sieve class
//Constructs a Sieve object, and contains methods to manipulate it
public class Sieve
{
	protected LinkedList<Integer> conInts;//Holds n consecutive Integers
	protected LinkedList<Integer> primes;//Holds prime Integers
	protected Integer p;//Temporarilly holds a prime integer
	protected int lastMax;//Holds value user gave for n
	protected int lastAP;//Hold total amount of primes stored
	protected int comCalled;//Used to mark wether computeTo() was called or not

	//Constructs a Sieve object
	public Sieve()
	{
		this.conInts = new <Integer>LinkedList();
		this.primes = new <Integer>LinkedList();
		this.p = 0;	
		this.lastMax = 0;
		this.lastAP = 0;
		this.comCalled = 0;	
	}
	
	//Builds lists, finds primes and stores them
	public void computeTo(int n)
	{
		clear();//Clears previous data if user has already calculated primes
		
		Integer number = 2;//Counts up to user imput for n
		
		if(n >= 2)//Makes sure user imput is valid
		{
			do
			{
				conInts.addLast(number);//Adds consecutive Integer
				number++;
			}
			while(number <= n);
			
			this.lastMax = n;//Stores user imput
			this.comCalled = 1;//Marks that computeTo() has been called
			
		}
		else
		{
			throw new IllegalArgumentException("Bad User Input: Try again!");	
		}
		
		//Checks for primes and adds them to primes list
		for(int i = 0; i < conInts.size(); i++)
		{
			this.p = this.conInts.get(i);
			this.primes.addLast(this.p);
			this.lastAP++;//Counts numbers of primes found
			
			for(int c = 0; c < conInts.size(); c++)
			{
			
				if(conInts.get(c)%this.p == 0 && this.conInts.get(c) != p)
				{
					this.conInts.remove(c);
				}
			}
			
			
		}
				
	}
	
	//Gets stored user imput for n if computeTo() has run
	public int getMax()
	{
		if(this.comCalled == 1)
		{
			return this.lastMax;
		}
		else
		{
			throw new IllegalStateException("Error: No call had been made to computeTo()"); 
		}
	}
	
	//Gets stored value for total number of primes held by primes list if computeTo() has run
	public int getCount()
	{
		if(this.comCalled == 1)
		{
			return this.lastAP;
		}
		else
		{
			throw new IllegalStateException("Error: No call had been made to computeTo()"); 
		}
	}
	
	//Prints everything in primes list if computeTo() has run 
	public void reportResults()
	{
		if(this.comCalled == 1)
		{
		
			System.out.println("Primes up to "+this.lastMax+" are as follows:");
			System.out.println();
		
			for(int i = 0; i < this.primes.size(); i++)
			{
				System.out.print(""+this.primes.get(i)+" ");
			}
			
			System.out.println();
			System.out.println();
		}
		else
		{
			throw new IllegalStateException("Error: No call had been made to computeTo()"); 
		}
		
	}
	
	//Clears users previous primes calculations by reseting all values
	public void clear()
	{
		this.conInts = new <Integer>LinkedList();
		this.primes = new <Integer>LinkedList();
		this.p = 0;	
		this.lastMax = 0;
		this.lastAP = 0;
		this.comCalled = 0;
	}

}