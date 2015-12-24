
//Author: Brandon Fowler
//EWUID: 00639348
//Class: CSCD320
//Quarter: Spring 2014
//Programming Assignment 2

//Basic Node class for LinkedList(Used in both adjacency list, and MinHeap)
//================================================================================================================
public class Node
{
		public int vertex;               //Stores vertex number
		public int edgeWeight;           //Stores edge weight(Use in adjacency list)
      public String path;              //Stores path from source Node
      public int d;                    //Stores shortest weight path found so far(Used in MinHeap and result LinkedList)
		public Node next;                //Link for Nodes in LinkedLists
		
      //Default constructor to initialize a Node with dummy values 
      //========================================================================================================== 
		public Node(){
			this.vertex = -99999;         //Unlikely number for acctual vertex
			this.edgeWeight = -1;         //Acctual edge weights are guarenteed to be positive
         this.d = 99999;               //Large unlikley starting number needed for MinHeap 
			this.next = null; 
         this.path = "";            
		}
}