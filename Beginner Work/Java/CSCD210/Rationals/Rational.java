//Creator: Brandon Fowler
//Rational.java(Part of Assignment 5)
//Class:CSCD 210 



//Rational Class
//Constructs rationals with defined characteristics and math functions
//__________________________________________________________________________
public class Rational implements Comparable<Rational>
{
	//Initialize Numerator
	private int num;
	
	//Initialize Denominator
	private int den;
	
	
	//Default Value Constructor
	//_________________________________________________________________
	public Rational()
	{
		this(1,1);
	}
	
	
	//Explicit Value Constructor
	//_________________________________________________________________
	public Rational(int num, int den)
	{
		int greatestDiv = gcd(Math.abs(num),Math.abs(den));
		num = num/greatestDiv;
		den = den/greatestDiv;
		
		this.num = num;
		this.den = den;
	}
	
	
	//Set Numerator
	//__________________________________________________________________
	public void setNum(int value)
	{
		this.num = value;
	}
	
	
	//Get Numerator
	//__________________________________________________________________
	public int getNum()
	{
		return this.num;
	}
	
	
	//Set Denominator
	//__________________________________________________________________
	public void setDen(int value)
	{
		this.den = value;
	}
	
	
	//Get Denominator
	//__________________________________________________________________
	public int getDen()
	{
		return this.den;
	}
	
	
	//Rational Addition Method
	//__________________________________________________________________
	public static Rational add(Rational r1,Rational r2)
	{
		int num = (r1.num*r2.den)+(r2.num*r1.den);
		int den = (r1.den*r2.den);
		
		Rational result = new Rational(num,den);
		
		return result;
	}
	
	
	//Rational Subtraction Method
	//__________________________________________________________________
	public static Rational sub(Rational r1,Rational r2)
	{
		int num = (r1.num*r2.den)-(r2.num*r1.den);
		int den = (r1.den*r2.den);
		
		Rational result = new Rational(num,den);
		
		return result;
	}

	
	//Override compareTo
	//__________________________________________________________________
	@Override
	public int compareTo(Rational that)
	{
		return (this.num*that.den)-(that.num*this.den);
	}
	
	
	//Override Equals
	//__________________________________________________________________
	@Override
	public boolean equals(Object obj)
	{
		if(obj.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
		{
			Rational that = (Rational)obj;
			return this.num == that.num && this.den == that.den;
		}
		return false;
	}
	
	
	//Override toString
	//___________________________________________________________________
	@Override
	public String toString()
	{
		String result = this.num+"/"+this.den;
		return result;
	}
	
	
	//Greatest Common Divisor Calculator
	//Helps for reducing rationals
	//___________________________________________________________________
	private static int gcd(int num1, int num2)
	{
		int remainder = 0;
		
		while(num2 != 0)
		{
			remainder = num1 % num2;
			num1 = num2;
			num2 = remainder;
		}
		
		return num1;
	
	}
	
	
}