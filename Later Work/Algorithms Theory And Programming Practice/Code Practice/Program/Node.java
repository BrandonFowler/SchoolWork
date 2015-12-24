
//Author: Brandon Fowler
//EWUID: 00639348
//CSCD320-Programming Assignment

import java.util.*;

/**
Node class:

Simple node implementation

Stores key data from input file

Stores line row index of were data was found in input file
**/

public class Node
{
   public int key;
   public int lineIndex;
   
   //Initializes a node with dummy values===============================
   public Node()
   {
      this.key = 0;
      this.lineIndex = 0;
   }
   
   //Initializes a node with values passed in===========================
   public Node(int key, int lineIndex)
   {
      this.key = key;
      this.lineIndex = lineIndex;
   }     
}