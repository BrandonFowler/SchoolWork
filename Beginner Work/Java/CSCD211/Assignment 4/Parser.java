//Creator: Brandon Fowler
//Parser.java(Part of Assignment 4)
//Class:CSCD 211


public class Parser
{	
	//Keeps track of position in array
	static int index = 0;
	
	//Parse
	//Accepts String parameter. Breaks String into array.
	//Passes array to isExpression, then prints results.
	public static void parse(String Line)
	{
		index = 0;
		char[] CAra;
		CAra = Line.toCharArray();
		String[] LAra = new String[CAra.length];
		for (int i = 0; i < CAra.length; i++)
		{
			LAra[i] = Character.toString(CAra[i]);
		}
		System.out.println();
		System.out.print(Line);
		
		boolean result = false;
		
		result = isExpression(LAra);
		
		if (index != LAra.length)
		{
			result = false;
		}
		
		if (result == true)
		{
			System.out.print(" is Valid");
		}
		else
		{
			System.out.print(" is Not Valid");
		}
		
	}
	
	//Start of recursive methods-----------------------------
	
	//isExpression
	//Tests passed in parameter to see if it's an expression.
	public static boolean isExpression(String[] S)
	{
		boolean result = isTerm(S);
		try
		{
			if (S[index].compareTo("+")==0 || S[index].compareTo("-")==0)
			{
				index++;
				result = isTerm(S);
			}
		}
		catch (Exception e)
		{
			return result;
		}
		return result;	
	}
	
	//isTerm
	//Tests passed in parameter to see if it's a term.
	public static boolean isTerm(String[] S)
	{
		boolean result = isFactor(S);
		try
		{
			if (S[index].compareTo("/")==0 || S[index].compareTo("*")==0)
			{
				index++;
				result = isFactor(S);
			}
		}
		catch (Exception e)
		{
			return result;
		}
		return result;	
	}
	
	//isFactor
	//Tests passed in parameter to see if it's a factor.
	public static boolean isFactor(String[] S)
	{
		boolean result = false;
		try
		{
			if (isLetter(S[index])==true)
			{
				return true;
			}
			else if (S[index].compareTo("(")==0)
			{
				index++;
				result = isExpression(S);
				if(S[index].compareTo(")")==0)
				{
					index++;
					return result;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	//isLetter
	//Tests passed in parameter to see if it's a letter.
	public static boolean isLetter(String A)
	{
		if (A.compareTo("A") == 0 || A.compareTo("B") == 0 || A.compareTo("C") == 0
		|| A.compareTo("D") == 0 || A.compareTo("E") == 0 || A.compareTo("F") == 0
		|| A.compareTo("G") == 0 || A.compareTo("H") == 0 || A.compareTo("I") == 0
		|| A.compareTo("J") == 0 || A.compareTo("K") == 0 || A.compareTo("L") == 0
		|| A.compareTo("M") == 0 || A.compareTo("N") == 0 || A.compareTo("O") == 0
		|| A.compareTo("P") == 0 || A.compareTo("Q") == 0 || A.compareTo("R") == 0
		|| A.compareTo("S") == 0 || A.compareTo("T") == 0 || A.compareTo("U") == 0
		|| A.compareTo("V") == 0 || A.compareTo("W") == 0 || A.compareTo("X") == 0
		|| A.compareTo("Y") == 0 || A.compareTo("Z") == 0)
		{
			
			index++;
			return true;
			
		}
		else
		{
			return false;
		}
	}
}