
//_________________________________________________________
//Node class
//----------
//Constructs Node objects that hold data, and link to more nodes via LinkedList
//----------
public class Node
{
	protected Package data;
	protected Node next;
	
	//Node default Constructor	
	public Node()
	{
		this.data = null;
		this.next = null;
	}
	
	//Node Constructor	
	public Node(Package d)
	{
		this.data = d;
		this.next = null;
	}	
}
