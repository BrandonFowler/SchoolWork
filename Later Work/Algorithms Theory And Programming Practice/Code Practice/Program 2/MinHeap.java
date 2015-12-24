
//Author: Brandon Fowler
//EWUID: 00639348
//Class: CSCD320
//Quarter: Spring 2014
//Programming Assignment 2

import java.util.*;

//Creates a MinHeap data stucture and contains methods to manipulate it
//=============================================================================================================
public class MinHeap{   
   public Node[] heap;                                //Stores heap of Nodes
   public int heapSize;                               //Stores Heap size
   
   //Constructs a min heap from given array
   //==========================================================================================================
   public MinHeap(Node A[]){
 	   this.heap = new Node[A.length];			         //Initialize Node array for heap
    	System.arraycopy(A,0,this.heap,0,A.length);	   //Copy passed in array into heap                                   

	   this.heapSize = A.length;				            //Set heap size

      //Turn entire array into a min heap
	   for(int i=this.heapSize/2-1; i>=0; i--){
   		heapify(i);				

	   }
   }
   
   //Starts at a given index and incorporates it into a proper
   //min heap structure, as long as the values below the given
   //index are already a proper min heap structure
   //==========================================================================================================
   public void heapify(int i){
	   int left;                                       //Stores index of left logical child
      int right;                                      //Stores index of right logical child
      int min;                                        //Stores index of minimum key(Node.d)                                        
      Node temp;		                                 //Used to swap nodes	

	   left = 2 * i + 1;                               //Get index of left logical child of i			
	   right = 2 * i + 2;                              //Get index of right logical child of i			
      
      //Left child value smaller than value at given index
  	   if(left < this.heapSize && this.heap[left].d < this.heap[i].d)		
     		min = left;             	
   	else//Left child not smaller
  	   	min = i;
      
      //Right child smaller than values already checked
   	if(right < this.heapSize && this.heap[right].d < this.heap[min].d)
     		min = right;           	
      
      //Value at given index not already smallest
   	if(min != i){
      	 			
            //Swap values then recursivley continue heapify process
      		temp = this.heap[i];      		
      		this.heap[i] = this.heap[min];
      		this.heap[min] = temp;
      		heapify(min); 			

      }
   }
   
   //Moves a Node up in the list if its .d value is smaller 
   //than its parents .d value(.d == shortest weight path so far)
   //==========================================================================================================
   public void upgradeKey(int index){
      
      //While key is less than parents key, swap Nodes
      while(this.heap[index].d < this.heap[index/2].d){
         Node temp = this.heap[index];
         this.heap[index] = this.heap[index/2];
         this.heap[index/2] = temp;
         index = index/2;
      }
   
   }
   
   //Removes the top node, insures heap structure, then returns the removed node
   public Node extractTop(){
      Node top = this.heap[0];                        //Save top
      this.heap[0] = this.heap[this.heapSize-1];      //Swap top with last
      this.heapSize--;                                //Lessen heap size by 1
      heapify(0);                                     //Insure min Heap structure
      return top;                                     //Return extracted node
   }
}