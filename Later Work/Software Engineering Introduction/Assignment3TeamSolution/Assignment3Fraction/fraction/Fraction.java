
//Brandon Fowler
//CSCD350
//Spring 2014
//Assignment 3


package fraction;

//Fraction class represents a fraction with two data fields num and den ========================================
public class Fraction implements Comparable<Fraction> {
	
	private int num;
	
	private int den;
	
	//Fraction constructor checks for valid input, reduces input, and initializes Fraction fields===============
	public Fraction(int num, int den){
		
		//Make sure denominator is not zero
		if(den == 0){
			throw new IllegalArgumentException("The denominator with the value of 0 is not permitted");
		}
		//If both num and den are negative, then make them positive
		if(num < 0 && den < 0){
			num = num*(-1);
			den = den*(-1);
		}
		//If num is positive and den is negative, then times both by -1
		if(num > 0 && den < 0){
			num = num*(-1);
			den = den*(-1);
		}
		
		//Find greatest common divisor, then reduce
		int greatestDiv = gcd(Math.abs(num),Math.abs(den));
		num = num/greatestDiv;
		den = den/greatestDiv;
		
		this.num = num;
		this.den = den;
		
	}
	
	//Retrieves private data=====================================================================================
	public int getNum(){
		return this.num;
	}
	
	//Retrieves private data=====================================================================================
	public int getDen(){
		return this.den;
	}
	
	//Check if this Fraction object is equal to another==========================================================
	@Override
	public boolean equals(Object obj){
		
		Fraction that = (Fraction)obj;
		
		if(this.num == that.getNum() && this.den == that.getDen()){
			return true;
		}
		
		return false;
	}
	
	//Compare this fraction object to another====================================================================
	@Override
	public int compareTo(Fraction that){
		
		int res = (this.num*that.den)-(that.num*this.den);
		
		if( res > 0){
			return 2;
		}
		if(res < 0){
			return -1;
		}
		
		return 0;
		
	}
	
	//Print Fraction object in readable format===================================================================
	@Override
	public String toString(){
		
		String result = this.num+"/"+this.den;
		return result;

	}
	
	//Adds this Fraction object with another=====================================================================
	public Fraction add(Fraction toAdd){
		
		//Make sure Fraction object that was passed in is not null
		if(toAdd == null){
			throw new NullPointerException("Cannot perform math operations on a null fraction object!");
		}
		
		int num = (this.num*toAdd.den)+(toAdd.num*this.den);
		int den = (this.den*toAdd.den);
		
		//Let constructor to the work of validity check, and reduction of new Fraction object
		Fraction res = new Fraction(num, den);
		
		return res;

	}
	
	//Multiply's this Fraction object with another================================================================
	public Fraction multiply(Fraction toMultiply){
		
		//Make sure Fraction object that was passed in is not null
		if(toMultiply == null){
			throw new NullPointerException("Cannot perform math operations on a null fraction object!");
		}
		
		int num = (this.num*toMultiply.num);
		int den = (this.den*toMultiply.den);
		
		//Let constructor to the work of validity check, and reduction of new Fraction object
		Fraction res = new Fraction(num, den);
		
		return res;
		
	}
	
	//Divide Fraction object numerator by denominator and return real number======================================
	public double realValue(){
		
		double num = (double)this.num;
		double den = (double)this.den;
		
		return (num/den);
	}
	
	//Takes two numbers and finds the greatest common denominator=================================================
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
