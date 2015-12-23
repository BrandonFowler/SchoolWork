
import java.math.*;

public class Prime
{
	
	
	
	public static void main(String[]args)
	{
		int UI = 1000;
		primeCalc(UI);
	}
	
	
	private static void primeCalc(int UI)
	{
		int P = 0;
        
		while (++P <= UI) 
		{

      	int P2 = (int) Math.ceil(Math.sqrt(P));

         boolean Decision = false;

         while (P2 > 1) 
			{

         	if ((P != P2) && (P % P2 == 0)) 
				{
            	Decision = false;
               break;
            }
				else if (!Decision) 
				{
            	Decision = true;
            }

            --P2;
			}

         if (Decision) 
			{
         	System.out.println(P);
                
         }

	
		}


	}





}