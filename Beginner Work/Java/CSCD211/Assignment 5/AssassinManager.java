

import java.util.*;


/**
Assasin Manager.java
Contains AssassinNode class, and creates a linked list of AssasinNodes.
<p><b>
Extra Credit: 
</b><pre> 
N/A
</pre><b>  
History: 
</b><pre> 
Created 1/18/2012, No Changes made since.
</pre>  
@author	Brandon Fowler 
*/
public class AssassinManager
{
	
	
	/**
	AssasinNode class
	Contains a constructor for AssasinNodes
	<p><b>
	Extra Credit: 
	</b><pre> 
	N/A
	</pre><b>  
	History: 
	</b><pre> 
	Created 1/18/2012, No Changes made since.
	</pre>  
	@author	Brandon Fowler 
	*/
	public class AssassinNode
	{
		private String Name;
		private String Killer;
		private AssassinNode Next;
		
	 /**
	 AssasinNode()
	 Constructs an AssasinNode
 	 @param       Name
	 */ 

		private AssassinNode(String Name)
		{
			this.Name = Name;
			this.Killer = null;
			this.Next = null;
		}
	}
	
	//Initialize KillRing and Graveyard
	private AssassinNode KillRing;
	private AssassinNode Graveyard;
	
	
	
	 /**
	 AssasinManager() Accepts a list of names, then creates a linked list of AssassinNodes
	 @param       List names
	 */ 
	public AssassinManager(List names)
	{	
		//Checks if list is empty
		if (names.isEmpty()==true)
		{
			throw new IllegalArgumentException("Empty List/File");
		}
		//Creates Head/Initial Reference
		KillRing = new AssassinNode((String)names.get(0));
		
		//Used to traverse and add to KillRing
		AssassinNode Position = KillRing;
		
		//Adds Nodes using list of names
		for (int i = 1; i < names.size(); i++) 
		{
			Position.Next = new AssassinNode((String)names.get(i));
			Position = Position.Next;
		}
		
	}
	
		
	/**
	 printKillRing() Traverses through linked list KillRing and prints the Name field
 	@param       
 	@return	void - Does not return a value
	 */ 
	public void printKillRing()
	{
		AssassinNode Position = KillRing;
		
		//Walks through list and prints all but last Name
		while (Position.Next != null)
		{
			System.out.println("    "+Position.Name+" is stalking "+Position.Next.Name);
			Position = Position.Next;
		}
		
		//Prints last Name
		System.out.println("    "+Position.Name+" is stalking "+KillRing.Name);
	}
	
	
	
	/**
	 printKillRing()Traverses through linked list Graveyard, and prints the Name and Killer fields
 	@param       
 	@return	void - Does not return a value
	 */ 
	public void printGraveyard()
	{
		if (Graveyard != null)
		{
			AssassinNode Position = Graveyard;
			
			//Walks through list and prints all but last Name and Killer
			while (Position.Next != null)
			{
				System.out.println("    "+Position.Name+" was killed by "+Position.Killer);
				Position = Position.Next;
			}
			
			//Prints last Name and Killer
			System.out.println("    "+Position.Name+" was killed by "+Position.Killer);
		}
		
		//If no one is in Graveyard
		else
		{
			System.out.println();
		}
	}
	
	
	
	/**
	KillRingContains() checks to see what names are left in the killRing  
 	@param      name 
 	@return	Boolean
	 */ 
	public boolean killRingContains(String name)
	{
		AssassinNode Position = KillRing;
		
		while(Position.Next != null)
		{
			if (name.compareToIgnoreCase(Position.Name)==0)
			{
				return true;
			}
			Position = Position.Next;
		}
		if (name.compareToIgnoreCase(Position.Name)==0)
		{
			return true;
		}
		
		return false;
	}
	
	
	
	/**
	graveyardContains()
	Checks to see if a name is contained in the Graveyard
	@param	name
	@return	Boolean
	*/
	public boolean graveyardContains(String name)
	{
		//Check to see if Graveyard is empty
	 	if (Graveyard == null)
		{
			return false;
		}
		
		AssassinNode Position = Graveyard;
		
		while(Position.Next != null)
		{
			if (name.compareToIgnoreCase(Position.Name)==0)
			{
				return true;
			}
			Position = Position.Next;
		}
		if (name.compareToIgnoreCase(Position.Name)==0)
		{
			return true;
		}
		
		return false;
	}
	
	
	
	/**
	gameOver()
	Checks to see if only one node is left in the KillRing, and returns true or false
	@param
	@return
	*/
	public boolean gameOver()
	{
		if (KillRing.Next == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/**
	winner()
	Checks to see if only one node is left in the KillRing, and returns a name or null
	@param
	@return
	*/
	public String winner()
	{
		if (KillRing.Next == null)
		{
			return KillRing.Name;
		}
		else
		{
			return null;
		}
	}
	
	
	/**
	Kill()
	Moves the selected node from the KillRing to the Graveyard, and records the Killer
	@param
	@return
	*/
	public void kill(String name)
	{
		//Checks to make sure game isn't over
		if (KillRing.Next == null)
		{
			throw new IllegalStateException("Game is over!");
		}
		
		//Used to travers KillRing Linked List
		AssassinNode Position = KillRing;
		//Used to move nodes without losing links
		AssassinNode Temp = null;
		//Used to store Graveyard
		AssassinNode Temp2 = Graveyard;
		//Used to Hold the position of the node that is to be moved
		AssassinNode Hold = null;
		
		//Traverse KillRing, and look for Name(except first node)
		while (Position.Next != null)
		{
			if (name.compareToIgnoreCase(Position.Next.Name)==0)
			{
				Temp = Position.Next;
				Hold = Position;
			}
			Position = Position.Next;
		}
		
		//Gives Position variable the position of the node to be moved
		Position = Hold;
		
		if (Temp != null)
		{
			//Store Killer name
			Temp.Killer = Hold.Name;
			
			//Moves chosen node
			Graveyard = Position.Next;
			Position.Next = Position.Next.Next;
			Graveyard.Next = Temp2;
		}
		
		//Checks first node in KillRing for name
		if (name.compareToIgnoreCase(KillRing.Name)==0)
		{
			//Used to traverse KillRing list
			Position = KillRing;
			
			//Traverse KillRing to end to get the name of last node to store as killer
			while(Position.Next != null)
			{
				Position = Position.Next;
			}
			
			//Store killer name
			KillRing.Killer = Position.Name;
			
			//Moves chosen node
			Temp = KillRing.Next;
			KillRing.Next = Graveyard;
			Graveyard = KillRing;
			KillRing = Temp;
		}
		
		//Throws exception if passed in name doesn't exist
		if (Temp == null)
		{
			throw new IllegalArgumentException("Name doesn't exist");
		}
	}	
}