
//________________________________________________________________
//LinkedList class
//----------------
//Contains constructor for LinkedList, head Node, addFirst method, removeFirst method
//----------------
public class LinkedList
{
	protected Node head;//Initialize head node for linked list
	
	public LinkedList()//LinkedList defualt constructor
	{
		this.head = null;
	}
	
	//Add method
	//Adds a node to the start this LinkedList
	public void add(Package dataToAdd)
	{
		Node newNode=new Node(dataToAdd);
		
		if(this.head == null)
		{
			this.head = newNode;
		}
		else
		{
			Node temp = null;
			for(temp=this.head;temp.next!=null;temp=temp.next);
			temp.next = newNode;
			
		}
	}
	
	//RemoveFirst method
	//Removes first node of this linked list
	public void removeFirst()
	{
		if(this.head.next == null)
		{
			this.head = null;
		}
		else
		{
			this.head = this.head.next;
		}
	}  
}
//End LinkedList
//________________________________________________________________________