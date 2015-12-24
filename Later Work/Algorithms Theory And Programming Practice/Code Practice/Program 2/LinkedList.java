
//Author: Brandon Fowler
//EWUID: 00639348
//Class: CSCD320
//Quarter: Spring 2014
//Programming Assignment 2

import java.util.*;

//Basic linked List with Nodes, constructor, addlast(), and a sort method
//==========================================================================================================
public class LinkedList{
	public Node head;             //Start of linked list
   public int length;            //Amount of nodes in LinkedList
	
   //Basic default constructor
   //=======================================================================================================
	public LinkedList(){
		this.head = null;
      this.length = 0;
	}
	
   //Adds a given Node to the end of the LinkedList
   //======================================================================================================
	public void addLast(Node n){
		if(this.head == null){                                         //If LinkedList is empty
			this.head = n;                                              //New node is the Head                                             
		}
		else{                                                          //If list is not empty
			Node cur;
			for(cur = this.head; cur.next != null; cur = cur.next);     //Iterate to end of list
			cur.next = n;                                               //Add the Node
		}
      length++;                                                      //Increase Node count
	}
   
   //Sorts a the LinkedList in ascending order based on vertex number
   //=====================================================================================================
   public void sort(){
      if(this.head != null && this.length > 1){           //If LinkedList is not empty or containing only one node
         
         //Preform basic sorting proceedure for LinkedList
         Node start = this.head;
         Node cur;
         while(start.next != null){
            cur = start;
            while(cur.next != null){
               if(cur.next.vertex < start.vertex){       //If smaller vertex is found
                  
                  //Swap vertex .d and path(required data for pinting)
                  int temp = start.vertex;
                  start.vertex = cur.next.vertex;
                  cur.next.vertex = temp;
                  
                  temp = start.d;
                  start.d = cur.next.d;
                  cur.next.d = temp;
                  
                  String t = start.path;
                  start.path = cur.next.path;
                  cur.next.path = t;  
               }
               cur = cur.next;
            }
            start = start.next;
         }
      }
   }
}