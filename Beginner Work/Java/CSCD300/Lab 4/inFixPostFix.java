//Name: Brandon Fowler
//File: inFixPostFix.java(Part of Lab 4)
//Class: CSCD 300

import java.util.*;
import java.math.*;
import java.io.*;

//inFixPostFix class
//------------------
//Creates Infix expressions from data in input file
//Creates a table of values from input file
//Preforms Infix to Posts fix conversions
//Proforms PostFix evaluations based on value table
public class inFixPostFix
{

	private BigInteger[] symbols;
	public Queue inFix;
	private Queue postFix;
	protected LinkedSymbolTable VTable;//Value table
	
	
	String fileName = "infix.txt";//Input File Name
	Stack stack = new Stack();
	
	//Cunstructor
	public inFixPostFix()
	{
		symbols = new BigInteger[26];
		inFix = new LinkedList<String>();
		postFix = new LinkedList<String>();
		VTable = new LinkedSymbolTable();
	}
	
	//loadFile method
	//---------------
	//Reads file and creates Infix expressions and value table
	public void loadFile()
	{
		String strLine = new String();
		BigInteger Btemp;//Used to hold a BigInteger for int cast
		int Itemp;//Used to hold a int to be cast to a BigInteger
		
		try
		{
			//Open file
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			boolean symTable = true;//Reading symbol Table
			boolean infixData = false;//Reading Infix Expressions
			
			System.out.println();
			System.out.println("Processing symbol table");
			System.out.println("-----------------------");
			System.out.println();
			
			while((strLine = br.readLine()) != null)
			{
				strLine = strLine.trim();
				if(strLine.length() == 0)
				{
					continue;//Skip Empty Lines
				}
				
				if(strLine.startsWith("#"))//Check for marker
				{
					if(!infixData)//Start Processing Infix Data
					{
						System.out.println();
						System.out.println("Processing infix expressions");
						System.out.println("----------------------------");
						System.out.println();
						symTable = false;
						infixData = true;
					}
					continue;
				}
				
				if(symTable)//Start processing Symbols
				{
					String delims = "[ ]+";
					String[] tokens = strLine.split(delims);
					
					System.out.println("Storing symbol ["+ tokens[0] +"] with value: "+tokens[1]);
					
					Itemp = Integer.parseInt(tokens[1]);
					Btemp = BigInteger.valueOf(Itemp);
					
					VTable.addLast(tokens[0].charAt(0),Btemp);//Store Symbols and Values in LinkedSymbolTable
					
					int index = tokens[0].charAt(0) - 'A';
					
					
					symbols[index] = new BigInteger(tokens[1]);
				}
				
				if(infixData)//Process Infix Data
				{
					System.out.println("Processing infix data:");
					System.out.println(strLine);
					inFix.add(strLine);
				}
			}//End While
			
			in.close();
			System.out.println();
			System.out.println("Converting and Evaluating");
			System.out.println("-------------------------");
			System.out.println();
		}
		catch(Exception e)
		{
			System.err.println("Error: "+e.getMessage());
		}
		
	}//End loadFile()
	
	//in2PostFix Method
	//-----------------
	//Converts and Infix expression to a Postfix expression
	public void in2PostFix()
	{
		Stack stack = new Stack<Character>();
		String temp, postfixExpr = "";
		int x;
		char ch;
		while(!inFix.isEmpty())
		{
			temp = (String)inFix.remove();
			System.out.println("Infix Expression: "+temp);
			for(x = 0; x < temp.length(); x++)
			{
				ch = temp.charAt(x);
				
				if(isOperand(ch))//Check for Operand
				{
					postfixExpr = postfixExpr + ch;
				}
				
				if(ch=='(')
				{
					stack.push(ch);
				}
				
				if(ch==')')
				{
					while((Character)stack.peek()!='(')
					{
						postfixExpr = postfixExpr + stack.pop();
					}
					stack.pop();//Get rid of "("
				}
				
				if(isOperator(ch))
				{
					while(!stack.isEmpty() && inputPrec(ch) <= stackPrec((Character)stack.peek()))
					{
						postfixExpr = postfixExpr + stack.pop();
					}
					stack.push(ch);
				}
			}//End if
			
			while(!stack.isEmpty())
			{
				postfixExpr = postfixExpr + stack.pop();//Append all remaining Operators
			}
			
			System.out.println("Converted Postfix Expression: "+postfixExpr);
			postFix.add(postfixExpr);
			BigInteger value = post2Eval(postfixExpr);
			System.out.println("Expression value after postfix evaluation: " +value+"\n");
			
			postfixExpr = "";
		}//End while
	}//End in2Postfix
	
	//post2Eval Method
	//----------------
	//Evaluates a Postfix expression
	public BigInteger post2Eval(String temp)
	{
		Stack<BigInteger> stack = new Stack<BigInteger>();
		for(char c : temp.toCharArray())
		{
			BigInteger left,right,result = null;
			int exp=0;
			
			if(c=='-' || c=='+' || c=='*' || c=='/' || c=='^')
			{
				if(c=='-')
				{
					right = stack.pop();
					left = stack.pop();
					result = left.subtract(right);
					stack.push(result);
				}
				else if(c=='+')
				{
					right = stack.pop();
					left = stack.pop();
					result = left.add(right);
					stack.push(result);
				}
				else if(c=='*')
				{
					right = stack.pop();
					left = stack.pop();
					result = left.multiply(right);
					stack.push(result);
				}
				else if(c=='/')
				{
					right = stack.pop();
					left = stack.pop();
					result = left.divide(right);
					stack.push(result);
				}
				else if(c=='^')
				{
					right = stack.pop();
					left = stack.pop();
					
					if(right.longValue() > Integer.MAX_VALUE)//Check for int exp exception
					{
						try//Purposefully throw and catch an exception if the BigInteger is to big to be cast to and int
						{
							throw new Exception();
						}
						catch(Exception e)
						{
							System.out.println("Error: Exponent to big!");
						}
					}
					else
					{
						exp = right.intValue();//Store exponent as int
					}
					
					result = left.pow(exp);
					stack.push(result);
				}
			}
			else
			{
				stack.push(VTable.getValue(c));//Push value onto stack based on LinkedSymbolTable
			}
		}
		BigInteger value = stack.pop();//Final Evaluation
		return value;
	}//End Post2Eval
	
	//Checks to see if a char is an Operator
	private boolean isOperator(char cIn)
	{
		return (cIn=='+' || cIn=='-' || cIn=='*' || cIn=='/' || cIn=='^');
	}
	
	//Checks to see if char is an Operand
	private boolean isOperand(char cIn)
	{
		return (cIn>='A' && cIn<= 'Z');
	}
	
	//Table for Input Operator Values
	private int inputPrec(char cIn)
	{
		int res = -200;
		
		if(cIn=='(')
		{
			res=100;
		}
		else if(cIn==')')
		{
			res=0;
		}
		else if(cIn=='^')
		{
			res=6;
		}
		else if(cIn=='*')
		{
			res=3;
		}
		else if(cIn=='/')
		{
			res=3;
		}
		else if(cIn=='-')
		{
			res=1;
		}
		else if(cIn=='+')
		{
			res=1;
		}
		
		return res;
	}
	
	//Table for on stack Operator Values
	private int stackPrec(char cIn)
	{
		int res = -200;
		
		if(cIn=='(')
		{
			res=0;
		}
		else if(cIn==')')
		{
			res=99;
		}
		else if(cIn=='^')
		{
			res=5;
		}
		else if(cIn=='*')
		{
			res=4;
		}
		else if(cIn=='/')
		{
			res=4;
		}
		else if(cIn=='-')
		{
			res=2;
		}
		else if(cIn=='+')
		{
			res=2;
		}
		
		return res;
	}
}