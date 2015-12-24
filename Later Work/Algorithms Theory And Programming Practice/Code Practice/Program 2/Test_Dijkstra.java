
//Author: Brandon Fowler
//EWUID: 00639348
//Class: CSCD320
//Quarter: Spring 2014
//Programming Assignment 2

import java.util.*;
import java.io.*;

//Contains main and manipulates data structures to preform Dijkstra's algorithm with input file data
//=========================================================================================================
public class Test_Dijkstra{

   static String fileName;                                 //Stores input file name
   static int vertex;                                      //Stores starting vertex
   static MinHeap heap;                                    //Min heap for Dijkstra's algorithm
   static LinkedList[] adjList;                            //Adjacency list to store input from file
   static LinkedList resultSet = new LinkedList();         //Stores results from Dijkstra's algorithm
   
   //Gets starting parameters and calls methods to run algorithm
   //======================================================================================================
   public static void main(String[] args) throws FileNotFoundException{
   
      if(args.length > 1){                       //Input parameters were given
         fileName = args[0];                     //Get input file name
         vertex = Integer.parseInt(args[1]);     //Get starting vertex
      }
      else{                                      //Bad command line input
         System.out.println("Proper input file or vertex was not given as command line perameters");
         System.out.println("Example: java Test_Dijkstra data.txt 3");
         System.exit(-1);
      }
      
      loadData();             //Get input, and set up Min heap and adjacency list
      
      dijkstra();             //Preform Dijkstra's algorithm
      
      printResults();         //Print final resilts
     
   }
   
   //Gets input from file, and loads required data into a Min heap and a adjacency list
   //====================================================================================================== 
   public static void loadData() throws FileNotFoundException{
      Scanner read = new Scanner(new File(fileName));    //Prep file scanner
      int count = 0;                                     //Initialize count, to count file lines
      String inputLine;                                  //Stores each line of input from file
      String[] inputParse;                               //Used to parse each line of input on delims(,:;)
      Node[] storeForHeap;                               //Stores an array of nodes for later conversion to Min heap
      
      //Iterate through entire file and count lines
      while(read.hasNextLine()){                         
         read.nextLine();
         count++;
      }
      
      read.close();                                      //Close for reset
      
      read = new Scanner(new File(fileName));            //Re-open input file
      adjList = new LinkedList[count];                   //Initialize adjacency list with array, length file line count
      storeForHeap = new Node[count];                    //Initialize array for later heap conversion, with length of file line count
      count = 0;                                         //Reset Count to be used for array index in adjacency list and heap array
      Node forAdjList;                                   //Stores each input Node that will be added to adjacency list
      Node forHeap;                                      //Stores each input Node that will be added to heap
      
      //Initialize LinkedLists in adjacency list
      for(int i = 0; i < adjList.length; i++)
         adjList[i] = new LinkedList();
         
      while(read.hasNextLine()){                                 //Iterate through file
         inputLine = read.nextLine();                            //Read each line
         inputParse = inputLine.split("[:,;]");                  //Parse Each line           
      
         //Add a node to start a LinkedList in adjacency list at array index saved by count
         forAdjList = new Node();                              
         forAdjList.vertex = Integer.parseInt(inputParse[0]);
         adjList[count].addLast(forAdjList);
         
         //Add a node to array used for heap at index saved by count
         forHeap = new Node();
         forHeap.vertex = Integer.parseInt(inputParse[0]);
         if(forHeap.vertex == vertex){                           //If input vertex is the same as starting parameter vertex
            forHeap.d = 0;                                       //Set .d value to zero
            forHeap.path = ""+vertex;                            //Set path as itself
         }
         storeForHeap[count] = forHeap;                          
      
         //Add next hop neighbors to LinkedList in adjacency list at array index saved by count
         for(int i = 1; i < inputParse.length;i+=2){
            forAdjList = new Node();
            forAdjList.vertex = Integer.parseInt(inputParse[i]);
            forAdjList.edgeWeight = Integer.parseInt(inputParse[i+1]);
            adjList[count].addLast(forAdjList);
         }
         
         count++;                            //Increment count for heap and adjacency list array index
      }
      
      heap = new MinHeap(storeForHeap);      //Turn array of nodes into a Min heap
     
   }
   
   //Uses a MinHeap and adjacency list to preform Dikstra's algorithm
   //========================================================================================================
   public static void dijkstra(){
      
      while(heap.heapSize > 0){                                      //While the heap contains Nodes
         Node top;                                                   //Stores extracted Node from top of MinHeap
         int i;                                                      //Used in loops to save array index in adjacency list
         int j;                                                      //Used in loops to save index in the heap
         top = heap.extractTop();                                    //Exctract the top Node in the heap
      
         //Iterate through LinkedList heads in adjacency list, until a vertex is found or end of array
         for(i = 0; i < adjList.length; i++){
            if(adjList[i].head.vertex == top.vertex){
               break;
            }
         }
      
         if(adjList[i].head.vertex == top.vertex){                   //If the required vetex was found
            Node cur = adjList[i].head.next;                         //Move to next hop neighbor
         
            while(cur != null){                                      //While next hop neighbor exists
               
               //Search the heap for vertex matching next hop neighbor
               for(j = 0; j < heap.heapSize; j++){
                  if(cur.vertex == heap.heap[j].vertex){
                     break;
                  }
               }
               
               if(cur.vertex == heap.heap[j].vertex){                      //If vertex of next hop neighbor was found in the heap
                  if(top.d + cur.edgeWeight < heap.heap[j].d){             //If a new shortest weight path has been found
                     heap.heap[j].d = top.d + cur.edgeWeight;              //Update .d with new shortest weight path value
                     heap.heap[j].path = top.path+","+heap.heap[j].vertex; //Update path
                     heap.upgradeKey(j);                                   //Fix the heap structure
                  }
               }
               
               cur = cur.next;                                       //Move to another next hop neighbor
            }
         }
         if(top.vertex != vertex)
            resultSet.addLast(top);                                  //Add extracted heap Node to a LinkedList that saves results
      }
      
      resultSet.sort();                                              //Sort the resulting LinkedList
   }
   
   //Prints the resulting LinkedList gained from Dijkstra's algorithm
   //==============================================================================================================
   public static void printResults(){
      Node cur = resultSet.head;
      while(cur != null){
         if(cur.d == 99999){                                      //Path was never updated
            System.out.println("["+cur.vertex+"]unreachable");
         }
         else{
            System.out.println("["+cur.vertex+"]shortest path:("+cur.path+") shortest distance:"+cur.d);
         }
         cur = cur.next;
      }
   }
}