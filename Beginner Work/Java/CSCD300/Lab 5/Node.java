//Name: Brandon Fowler
//Class: CSCD 300
//Part of Assignment 5


//Basic Node class for LinkedWordList that holds a word, and a frequency count
public class Node implements Comparable<Node>
{
		protected String word;
		protected int freq;
		protected Node next;
		
		public Node(String w)
		{
			this.word = w;
			this.freq = 1;
			this.next = null;
		}
		
		public int compareTo(Node that)
		{
			return this.word.compareTo(that.word);
		}
}