//Student Name: Brandon Fowler
//Class: CSCD 300
//Part of Lab 3
//Sieve2 Class
//Sieve 1 is Slower than Sieve 2(Tested both at an n value of 10000)

//Note: I used <Integer> When declaring my lists and variables, and it still says 
//that is uses "Unsafe Operations" during compile, not sure why it still says 
//this, but the program runs fine.



import java.util.*;

//Sieve2 class
//Constructs a Sieve object, and contains methods to manipulate it
public class Sieve2
{
	protected ArrayDeque<Integer> conInts;//Holds n consecutive integers
	protected ArrayDeque<Integer> primes;//Holds prime numbers
	protected Integer p;//Temporarily holds a prime number
	protected int lastMax;//Stores user imput for n
	protected int lastAP;//Stores total number of primes held in primes list
	protected int comCalled;//Marks wether or not computeTo() has been called

	//SConstructs a sieve2 object
	public Sieve2()
	{
		this.conInts = new <Integer>ArrayDeque();
		this.primes = new <Integer>ArrayDeque();
		this.p = 0;	
		this.lastMax = 0;
		this.lastAP = 0;
		this.comCalled = 0;	
	}
	
	//Builds lists, finds primes and stores them
	public void computeTo(int n)
	{
		clear();//Clears previous user prime calculations
		
		Integer number = 2;//Used to count consecutive integers
		
		if(n >= 2)
		{
			do
			{
				conInts.addLast(number);//Adds consecutive integers to list
				number++;
			}
			while(number <= n);
			
			this.lastMax = n;//Stores user imput
			this.comCalled = 1;//Marks that computeTo()has been called 
			
		}
		else
		{
			throw new IllegalArgumentException("Bad User Input: Try again!");	
		}
		
		
		Integer temp = 0;//Stores an int temporarily
		ArrayDeque<Integer> temp2 = new ArrayDeque();//Stores consecutive integers temporarily
		
		//Finds primes, and adds them to primes list
		while(this.conInts.peekFirst() != null)
		{
			
			this.p = this.conInts.pop();
			this.primes.addLast(this.p);//Adds to primes list
			this.lastAP++;//Counts number of primes found
			
			//Pops all integers from conInts list, compares them with modulus, 
			//then pushes them onto temp2 if they are not evenly divisible
			while(this.conInts.peekFirst() != null)
			{
				temp = this.conInts.pop();
				if(temp%this.p == 0)
				{
					temp = 0;
				}
				else
				{
					temp2.push(temp);
				}
				
			}
			
			//Repopulates conInts list with Integers from temp2 list
			while(temp2.peekFirst() != null)
			{
				this.conInts.push(temp2.pop());
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
		Integer temp = 0;
		if(this.comCalled == 1)
		{
		
			System.out.println("Primes up to "+this.lastMax+" are as follows:");
			System.out.println();
		
			while(this.primes.peekFirst() != null)
			{
				temp = this.primes.pop();
				System.out.print(""+temp+" ");
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
		this.conInts = new <Integer>ArrayDeque();
		this.primes = new <Integer>ArrayDeque();
		this.p = 0;	
		this.lastMax = 0;
		this.lastAP = 0;
		this.comCalled = 0;
	}

}
//Sieve 1 is Slower than Sieve 2(Tested both at an n value of 10000)