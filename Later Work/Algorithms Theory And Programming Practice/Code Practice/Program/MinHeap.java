
//Author: Brandon Fowler
//EWUID: 00639348
//CSCD320-Programming Assignment

import java.util.*;

/**
MinHeap Class:

Creates a min heap structure of Nodes.

Contains methods to upkeep and manipulate heap. 
**/
public class MinHeap
{   
   public Node[] heap;                                //Initlialize heap of Nodes
   public int heapSize;                               //Stores Heap size
   
   //MinHeap()
   //Constructs a min heap from given array===========================================================
   public MinHeap(Node A[])	
   {
 	   this.heap = new Node[A.length];			         //Initialize Node array for heap
    	System.arraycopy(A,0,this.heap,0,A.length);	   //Copy passed in array into heap                                   

	   this.heapSize = A.length;				            //Set heap size

      //Turn entire array into a min heap
	   for(int i=this.heapSize/2-1; i>=0; i--) 		
	   {
   		heapify(i);				

	   }
   }
   
   //heapify()
   //Starts at a given index and incorporates it into a proper
   //min heap structure, as long as the values below the given
   //index is already a proper min heap structure.====================================================
   public void heapify(int i)  
   {
	   int left;
      int right;
      int min;
      Node temp;			

	   left = 2 * i + 1;                               //Get index of left logical child of i			
	   right = 2 * i + 2;                              //Get index of right logical child of i			
      
      //Left child value smaller than value at given index
  	   if(left < this.heapSize && this.heap[left].key < this.heap[i].key)		
     		min = left;             	
   	else//Left child not smaller
  	   	min = i;
      
      //Right child smaller than values already checked
   	if(right < this.heapSize && this.heap[right].key < this.heap[min].key)
     		min = right;           	
      
      //Value at given index not already smallest
   	if(min != i)	 			
   	{
            //Swap values then recursivley continue heapify process
      		temp = this.heap[i];      		
      		this.heap[i] = this.heap[min];
      		this.heap[min] = temp;
      		heapify(min); 			

      }
   }
   
   //exchangeSmallest()
   //Swaps and new value with the smallest heap value, then insures min heap structure===============
   public void exchangeSmallest(int key, int lineIndex)
   {
      this.heap[0].key = key;                         //Swap keys
      this.heap[0].lineIndex = lineIndex;             //Swap Input line Index
      heapify(0);                                     //Insure min Heap structure
   }
}