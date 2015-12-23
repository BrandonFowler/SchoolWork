//ShapeList.java(Part of Assignment 6)
//Creator: Brandon Fowler
//Class: CSCD 211

import java.io.*;

//Basic Linked List Class that uses ShapeNodes instead of normal Nodes
public class ShapeList
{
	//ShapeNode class
	private class ShapeNode
	{
		private Parallelogram data;
		private ShapeNode next;
		
		//Constructors-------------
		private ShapeNode(Parallelogram data, ShapeNode next)
		{
			this.data = data;
			this.next = next;
		}
		private ShapeNode(Parallelogram data)
		{
			this(data,null);
		}
	}
	
	private ShapeNode head;
	private int size;
	
	//Constructors-----------
	public ShapeList()
	{
		this.head = null;
		this.size = 0;
	}
	
	//isEmpty method
	//Checks to see if this ShapeList is empty
	private boolean isEmpty()
	{
		return this.head == null;
	}
	
	//addFirstMethod
	//Adds a ShapeNode to the begining of this ShapeList
	private void addFirst(Parallelogram data)
	{
		ShapeNode newShapeNode = new ShapeNode(data);
		newShapeNode.next = this.head;
		this.head = newShapeNode;
		this.size++;
	}
	
	//arrayToLinked method
	//Converts an array of Parallelograms to a ShapeList
	public static ShapeList arrayToLinked(Parallelogram[] ara)
	{
		ShapeList LinkedShapes = new ShapeList();
		
		for(int i = 0; i < ara.length && ara[i] != null; i++)
		{
			LinkedShapes.addFirst(ara[i]);
		}
		
		return LinkedShapes;
	}
	
	//printData method
	//Prints data from given Shapelist to the screen, and a file
	public static void printData(ShapeList toPrint)throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter("shapes.out"));
		
		for(ShapeNode cur = toPrint.head; cur != null; cur = cur.next)
		{
			System.out.println(cur.data);
			try 
				{
					out.write(cur.data.toString());
					out.write("\r\n");	    				
				} 				
				catch (IOException e)
				{
					System.out.println("Failed to read file");
				}
		}
		out.close();
	}
	
	//selectionSort Method
	//Sorts a ShapeList
	public void selectionSort()
	{
		if(this.size > 1)
		{
			ShapeNode start,smallest,cur;
			Parallelogram temp;
			
			for(start = this.head; start.next != null;start = start.next)
			{
				smallest = start;
				
				for(cur = start.next; cur != null; cur = cur.next)
				{
					if(cur.data.compareTo(smallest.data) < 0)
					{
						smallest = cur;
					}
				}
				
				temp = start.data;
				start.data = smallest.data;
				smallest.data = temp;
			}
		}
	}
	
	//buildNPrint method
	//Builds a sub list with a certain type of shape, then prints it
	public void buildNPrint(String target)
	{
		ShapeList sub = new ShapeList();
		ShapeNode cur;
		for(cur = this.head; cur != null; cur = cur.next)
		{
			if(cur.data.Name.compareTo(target) == 0)
			{
				sub.addFirst(cur.data);
			}
		}
		
		for(cur = sub.head; cur != null; cur = cur.next)
		{
			System.out.println(cur.data);
		}
	}
	
	//Calls other prints revers for this ShapeList
	public void printReverse()
	{
		printReverse(this.head);
	}
	//Prints a Shapelist in reverse
	public void printReverse(ShapeNode cur)
	{
		if(cur != null)
		{
			printReverse(cur.next);
			System.out.println(cur.data);
		}
	}
	
	//remove method
	//Removes a ShapeNode from this ShapeList
	public boolean remove(Parallelogram dataToRemove)
	{
		if(isEmpty())
		{
			return false;
		}
		ShapeNode cur,prev;
		for(cur = this.head, prev = null; cur != null && cur.data.compareTo(dataToRemove) != 0; cur = cur.next)
		{
			prev = cur;
		}
		if (prev == null)
		{
			this.head = this.head.next;
		}
		else
		{
			prev.next = cur.next;
		}
		this.size--;
		return true;
	}
	
	//removeType method
	//Finds a certain type of shape in a Shaplist, and removes all instances of it
	public static ShapeList removeType(ShapeList LinkedShapes, String target)
	{
		ShapeNode cur;
		for(cur = LinkedShapes.head; cur != null; cur = cur.next)
		{
			if(cur.data.Name.compareTo(target) == 0)
			{
				boolean val = LinkedShapes.remove(cur.data);
			}
		}
		return LinkedShapes;
	} 
	
}