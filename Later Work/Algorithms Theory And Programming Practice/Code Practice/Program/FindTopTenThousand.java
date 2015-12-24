
//Author: Brandon Fowler
//EWUID: 00639348
//CSCD320-Programming Assignment

import java.util.*;
import java.io.*;

/**
FindTopTenThousand Class:

Opens and closes input and output files.

Reads in data from a file.

Calls heap methods to store only the largest 10,000
numbers as they are read.

Outputs final results in the heap to a file.
**/
public class FindTopTenThousand
{

   //Start main()================================================================
   public static void main(String args[])throws FileNotFoundException
   {
     
      Node[] firstTenThousand = new Node[10000];                     //Stores the first ten thousand entries from input 
      PrintWriter Out = new PrintWriter("TopTenThousand.txt");       //Ready for output to file
      int inputValue;                                                //Stores each input value one at a time
      int count = 0;                                                 //Track line number of input values
      Scanner readFile = null;                                                 
      
      if(args.length > 0)
      {
         readFile = new Scanner(new File(args[0]));                  //Open Input File
      }
      else                                                           //No Input Given
      {
         System.out.println("Proper input file was not given as command line perameter");
         System.out.println("Example: java FindTopTenThousand data.txt");
         System.exit(-1);
      }
            
      //Read in first ten thousand values     
      for(int i = 0; i < 10000 && readFile.hasNextInt(); i++)
      {
         inputValue = readFile.nextInt();
         count++;
         firstTenThousand[i] = new Node(inputValue,count);
      }//End for loop
      
      MinHeap heapArray = new MinHeap(firstTenThousand);             //Turn first then thousand values into a min heap
      
      //Read all of the remaining entries in input file
      while(readFile.hasNextInt())
      {
         inputValue = readFile.nextInt();
         count++;
         
         //If new value is larger than smallest of current values
         if(heapArray.heap[0].key < inputValue)
         {
            heapArray.exchangeSmallest(inputValue,count);            //Exchange and heapify
         }
      }//End while loop
      
      readFile.close();//Close input file
      
      Out.println("Ten thousand highest numbers from input file:");  //Formating for output file
      Out.println();
      
      //Print final results stored in heap
      for(int i = 0; i < 10000; i++)
      {
         Out.println("Number From Input File: "+heapArray.heap[i].key+
            "      Input File Row Index: "+heapArray.heap[i].lineIndex);
      }//End for loop
      
      Out.close();//Close output file
   }//End main()
   
}