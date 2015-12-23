
//Linear search method, that tells us the index value of the target
//we are searching for. Returns -1 if the target doesnt exist.



public class LinearSearch{



public static void main(String[]args){

int[] numbers={1,2,3,4,5};

int target= 3;

int result=linearSearch(numbers, target);

System.out.println(result);

}


public static int linearSearch(int[]array, int target)
{
	for (int i=0; i<array.length; i++)
	{
		if(array[i]==target)
		{
			return i;
		}
	}
	return -1;
}
}
