//Creator: Brandon Fowler
//Dna.java(Part of Assignment 1)
//Class:CSCD 211 


import java.util.*;
import java.io.*;

/**
Dna Class. Initialzes Variables, and constructs a digital simulation of a biological
testing dish.    
<p><b>
Extra Credit: 
</b><pre> 
N/A
</pre><b>  
History: 
</b><pre> 
Created 4/16/2012, No Changes made since.
</pre>  
@author	Brandon Fowler 
*/
public class Dna
{
	private final int MaxK = 16;
	private final int LoopBounds = 10;
	private int Row = 10;
	private int Col = 10;
	private int[][] DishCur = new int[Row][Col];
	private int[][] DishFut = new int[Row][Col];
	private int Gen;
	private String Input;
	private String Output;
	private int[] D = new int[MaxK];
	private int[][] Dish;
	
	
	
	public Dna(int R, int C, String In, String Out)
	{
		this.Row = R;
		this.Col = C; 
		this.Input = In;
		this.Output = Out;
		this.Dish = new int[R][C];
	}
	
	
 /**
 Build Sample Method. Builds a simulation of a biological sample.
 Data is read in from a file to create the sample, then the sample simulation data
 is stored in the two dimensional arrays: Dish, DishCur, and DishFut.
 @param       No Parameters
 @return	void - Does not return a value
 @exception	FileNotFoundException
 */ 
	public void buildSample() throws FileNotFoundException
	{
		try
		{
			Scanner InFile = new Scanner(new File(this.Input));
			
			this.Gen = InFile.nextInt();
			
			for(int i = 0; i < MaxK; i++)
			{
				this.D[i] = InFile.nextInt();
			}
			
			for (int r = 0; r < LoopBounds; r++)
			{
				for (int c = 0; c < LoopBounds; c++)
				{
					this.Dish[r][c] = InFile.nextInt();
				}
			}
			
			InFile.close();
			
			for (int r = 0; r < LoopBounds; r++)
			{
				for (int c = 0; c < LoopBounds; c++)
				{
					DishCur[r][c] = this.Dish[r][c];
				}
			}

			for (int r = 0; r < LoopBounds; r++)
			{
				for (int c = 0; c < LoopBounds; c++)
				{
					DishFut[r][c] = DishCur[r][c];
				}
			}
			
			
			
			
		}
		catch(Exception E)
		{
			System.out.println("Something went wrong reading from file");
		}
	} 
	
	
 /**
 Run Test Method. Runs a test of the biological simulation.
 Compares data then uses the array D to calculate how the data
 evolves, then prints the result.
 @param       No Parameters
 @return	void - Does not return a value
 @exception	FileNotFoundException
 */ 
	public void runTest()throws FileNotFoundException
	{
		PrintWriter Out = new PrintWriter(this.Output);
		Out.println("Generation: 0");
		for (int RowL = 0; RowL < LoopBounds; RowL++)
		{
			for (int ColL = 0; ColL < LoopBounds; ColL++)
			{
				if (DishFut[RowL][ColL]==0)
				{
					Out.print(". ");
				}
				else if (DishFut[RowL][ColL]==1)
				{
					Out.print("! ");
				}
				else if (DishFut[RowL][ColL]==2)
				{
					Out.print("X ");
				}
				else
				{
					Out.print("# ");
				}	
			}
			Out.println();
		}

		int GenCount = 0;
		for (int g = 0; g < this.Gen; g++)
		{
			for (int r = 0; r < LoopBounds; r++)
			{
				for (int c = 0; c < LoopBounds; c++)
				{
					int K = 0;
					if (r==0 && c==0)
					{
						K = DishFut[r][c]+DishFut[r][c+1]+DishFut[r+1][c];
					}
					else if (r==0 && c==9)
					{
						K = DishFut[r][c]+DishFut[r][c-1]+DishFut[r+1][c];
					}
					else if (r==9 && c==0)
					{
						K = DishFut[r][c]+DishFut[r-1][c]+DishFut[r][c+1];
					}
					else if (r==9 && c==9)
					{
						K = DishFut[r][c]+DishFut[r][c-1]+DishFut[r-1][c];
					}
					else if (r==0 && c==1 || r==0 && c==2 || r==0 && c==3 || r==0 && c==4 || r==0 && c==5 || r==0 && c==6 || r==0 && c==7 || r==0 && c==8) 
					{
						K = DishFut[r][c]+DishFut[r][c-1]+DishFut[r][c+1]+DishFut[r+1][c];
					}
					else if (r==9 && c==1 || r==9 && c==2 || r==9 && c==3 || r==9 && c==4 || r==9 && c==5 || r==9 && c==6 || r==9 && c==7 || r==9 && c==8)
					{
						K = DishFut[r][c]+DishFut[r][c-1]+DishFut[r][c+1]+DishFut[r-1][c];
					}
					else if (r==1 && c==0 || r==2 && c==0 || r==3 && c==0 || r==4 && c==0 || r==5 && c==0 || r==6 && c==0 || r==7 && c==0 || r==8 && c==0)
					{
						K = DishFut[r][c]+DishFut[r+1][c]+DishFut[r-1][c]+DishFut[r][c+1];
					}
					else if (r==1 && c==9 || r==2 && c==9 || r==3 && c==9 || r==4 && c==9 || r==5 && c==9 || r==6 && c==9 || r==7 && c==9 || r==8 && c==9)
					{
						K = DishFut[r][c]+DishFut[r+1][c]+DishFut[r-1][c]+DishFut[r][c-1];
					}
					else
					{
						K = DishFut[r][c]+DishFut[r-1][c]+DishFut[r+1][c]+DishFut[r][c+1]+DishFut[r][c-1];
					}
					
					
					if (D[K]+DishFut[r][c] > 3)
					{
						DishCur[r][c] = 3;
					}
					else if (D[K]+DishFut[r][c] < 0)
					{
						DishCur[r][c] = 0;
					}
					else
					{
						DishCur[r][c] = DishFut[r][c]+D[K];
					}
				}
			}
			for (int r = 0; r < LoopBounds; r++)
			{
				for (int c = 0; c < LoopBounds; c++)
				{
					DishFut[r][c] = DishCur[r][c];
				}
			}
			
			int RowCount = 0;
			
			Out.println("Generation: "+GenCount);
			for (int RowL = 0; RowL < LoopBounds; RowL++)
			{
				for (int ColL = 0; ColL < LoopBounds; ColL++)
				{
					if (DishFut[RowCount][ColL]==0)
					{
						Out.print(". ");
					}
					else if (DishFut[RowCount][ColL]==1)
					{
						Out.print("! ");
					}
					else if (DishFut[RowCount][ColL]==2)
					{
						Out.print("X ");
					}
					else
					{
						Out.print("# ");
					}	
				}
				Out.println();
				RowCount++;
			}
			

			GenCount++;
			
		}
		Out.close();
	}
	
	
 }