
import java.util.*;

public class GetInt{


public static void main(String [] args)
{

 int Val = getInt();
 
 System.out.println(Val);

}

private static int getInt()
{
	Scanner kb;
	
	int Val;
	
	kb = new Scanner(System.in);
	
	System.out.print("Enter Integer: ");
	
	while(!kb.hasNextInt())
	{
		System.out.println("Try again");
		System.out.println();
		System.out.print("Enter Integer: ");
		kb.nextLine();
	}
	
	Val = kb.nextInt();
	
	return Val;

}



}