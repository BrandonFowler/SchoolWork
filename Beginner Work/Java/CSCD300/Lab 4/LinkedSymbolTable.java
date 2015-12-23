//Name: Brandon Fowler
//File: LinkedSymbolTable.java(Part of Lab 4)
//Class: CSCD 300


import java.math.*;
import java.util.*;

//LinkedSysmbolTable Class
//------------------------
//Creates a linked list of Table Nodes and has Methods to manipulate the list
//Used to store and find values for chars in PostFix expressions
public class LinkedSymbolTable
{
	protected TableNode head;
	
	//TableNodeClass
	//--------------
	//Holds char symbols and corresponing values
	private class TableNode
	{
		protected char symbol;
		protected BigInteger value;
		protected TableNode next;
		
		//TableNode Constructor	
		public TableNode(char S,BigInteger V)
		{
			this.symbol = S;
			this.value = V;
		}
	}
	
	//LinkedSymbolTable Constructor	
	public LinkedSymbolTable()
	{
		this.head = null;
	}
	
	//Adds a new TableNode to the end of the list	
	public void addLast(char S, BigInteger V)
	{
		if(this.head == null)
		{
			this.head = new TableNode(S,V);
		}
		else
		{	
			TableNode cur;
			for(cur = this.head; cur.next != null; cur = cur.next);
			cur.next = new TableNode(S,V);
		}
	}
	
	//Finds a char in the LinkedSymbolTable and returns the corresponding value
	public BigInteger getValue(char check)
	{
		TableNode cur;
		for(cur = this.head; cur.next != null && cur.symbol != check; cur = cur.next);
		return cur.value;
	}
}