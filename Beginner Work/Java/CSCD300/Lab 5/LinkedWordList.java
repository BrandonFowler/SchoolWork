//Name: Brandon Fowler
//Class: CSCD 300
//Part of Assignment 5



import java.util.*;

//Basic linked List with Nodes, constructor, addlast(), and a sort method
public class LinkedWordList
{
	protected Node head;
	
	public LinkedWordList()
	{
		this.head = null;
	}
	
	public void addLast(String w)
	{
		if(this.head == null)
		{
			this.head = new Node(w);
		}
		else
		{
			Node cur;
			for(cur = this.head; cur.next != null; cur = cur.next);
			cur.next = new Node(w);
		}
	}
	
	public void sort()
	{
		Node start;
		Node cur;
		Node smallest;
		String wTemp;
		int fTemp;
		
		for(start = this.head; start.next != null; start = start.next)
		{
			smallest = start;
			
			for(cur = start; cur != null; cur = cur.next)
			{
				if(cur.compareTo(smallest) < 0)
				{
					smallest = cur;
				}
			}
			
			wTemp = smallest.word;
			fTemp = smallest.freq;
			smallest.word = start.word;
			smallest.freq = start.freq;
			start.word = wTemp;
			start.freq = fTemp;
		}
	}
}