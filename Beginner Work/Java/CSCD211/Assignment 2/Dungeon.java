//Creator: Brandon Fowler
//Dungeon.java(Part of Assignment 2)
//Class:CSCD 211




import java.util.*;
import java.math.*;

//Dungeon Class
//Contains all of the methods to create, and manipulate a Dungeon.
public class Dungeon
{
	protected Room[][] Dungeon;
	protected Room HeroPosition;
	protected int StartRow;
	protected int StartCol;
	
	//Dungeon Constructor
	public Dungeon()
	{
		this.Dungeon = new Room[5][5];
		this.HeroPosition = this.Dungeon[0][0];
	}
	
	//MakeRooms Method
	//Makes rooms in current Dungeon
	public void makeRooms()
	{
		for(int row = 0; row < this.Dungeon.length; row++)
		{
			for(int col = 0; col < this.Dungeon.length; col++)
			{
				this.Dungeon[row][col] = new Room();
			}
		}
	}
	
	//MakeDoors Method
	//Sets doors in current Dungeon
	public void makeDoors()
	{
		for(int row = 0;  row < this.Dungeon.length; row++)
		{
			for(int col = 0; col < this.Dungeon[row].length; col++)
			{
				if(row == 0 && col == 0)
				{
					this.Dungeon[row][col].EDoor = 1;
					this.Dungeon[row][col].SDoor = 1;
				}
				else if(row == 0 && col == 4)
				{
					this.Dungeon[row][col].WDoor = 1;
					this.Dungeon[row][col].SDoor = 1;
				}
				else if(row == 4 &&  col == 0)
				{
					this.Dungeon[row][col].NDoor = 1;
					this.Dungeon[row][col].EDoor = 1;
				}
				else if(row == 4 && col == 4)
				{
					this.Dungeon[row][col].NDoor = 1;
					this.Dungeon[row][col].WDoor = 1;
				}
				else if(row == 0)
				{
					this.Dungeon[row][col].WDoor = 1;
					this.Dungeon[row][col].EDoor = 1;
					this.Dungeon[row][col].SDoor = 1;
				}
				else if(col == 0)
				{
				this.Dungeon[row][col].NDoor = 1;
				this.Dungeon[row][col].SDoor = 1;
				this.Dungeon[row][col].EDoor = 1;
				}
				else if(col == 4)
				{
					this.Dungeon[row][col].NDoor = 1;
					this.Dungeon[row][col].SDoor = 1;
					this.Dungeon[row][col].WDoor = 1;
				}
				else if (row == 4)
				{
					this.Dungeon[row][col].NDoor = 1;
					this.Dungeon[row][col].WDoor = 1;
					this.Dungeon[row][col].EDoor = 1;
				}
				else
				{
					this.Dungeon[row][col].NDoor = 1;
					this.Dungeon[row][col].WDoor = 1;
					this.Dungeon[row][col].EDoor = 1;
					this.Dungeon[row][col].SDoor = 1;
				}
			}
		}
	}
	
	//SetEntranceAndHero Method
	//Sets Entrance and Hero position in current Dungeon
	public void setEntranceAndHero()
	{
		int row = randomIndex();
		int col = randomIndex();
		this.Dungeon[row][col].Entrance = 1;
		this.HeroPosition = Dungeon[row][col];
		this.Dungeon[row][col].HealthP = 0;
		this.Dungeon[row][col].Pit = 0;
		this.StartRow = row;
		this.StartCol = col;

	}
	
	//SetExit Method
	//Sets Exit in current Dungeon
	public void setExit()
	{
		int row = randomIndex();
		int col = randomIndex();
		if (this.Dungeon[row][col].Entrance==1)
		{
			setExit();
		}
		else
		{
			this.Dungeon[row][col].Exit = 1;
			this.Dungeon[row][col].HealthP = 0;
			this.Dungeon[row][col].Pit = 0;
		}
	}
	
	//SetCrown1 Method
	//Sets first crown piece in current Dungeon
	public void setCrown1()
	{
		int row = randomIndex();
		int col = randomIndex();
		if (this.Dungeon[row][col].Entrance==1 || this.Dungeon[row][col].Exit==1)
		{
			setCrown1();
		}
		else
		{
			this.Dungeon[row][col].CrownP1 = 1;
			this.Dungeon[row][col].HealthP = 0;
			this.Dungeon[row][col].Pit = 0;
		}
	}
	
	//SetCrown2 Method
	//Sets second crown piece in current Dungeon
	public void setCrown2()
	{
		int row = randomIndex();
		int col = randomIndex();
		if (this.Dungeon[row][col].Entrance==1 || this.Dungeon[row][col].Exit==1)
		{
			setCrown2();
		}
		else
		{
			this.Dungeon[row][col].CrownP2 = 1;
			this.Dungeon[row][col].HealthP = 0;
			this.Dungeon[row][col].Pit = 0;
		}

	}
	
	//ShowItem Method
	//Looks at specified room in the dungeon, and returns a string
	//representation of any items in the room
	public String showItem(int row, int col)
	{
		String result = "";
		if (this.Dungeon[row][col].HealthP == 1 && this.Dungeon[row][col].Pit == 1)
			{
				result = result+"M";
			}
			if (this.Dungeon[row][col].HealthP == 1 && this.Dungeon[row][col].Pit == 0)
			{
				result = result+"H";
			}
			if (this.Dungeon[row][col].HealthP == 0 && this.Dungeon[row][col].Pit == 1)
			{
				result = result+"P";
			}
			if (this.Dungeon[row][col].CrownP1 == 1 || this.Dungeon[row][col].CrownP2 == 1)
			{
				result = result+"C";
			}
			if (this.Dungeon[row][col].Entrance == 1)
			{
				result = result+"I";
			}
			if (this.Dungeon[row][col].Exit == 1)
			{
				result = result+"O";
			}
			if (this.Dungeon[row][col].HealthP == 0 && this.Dungeon[row][col].Pit == 0 && this.Dungeon[row][col].CrownP1 == 0 && this.Dungeon[row][col].CrownP2 == 0 && this.Dungeon[row][col].Entrance == 0 && this.Dungeon[row][col].Exit == 0)
			{
				result = result+"E";
			}
			return result;

	}
	
	//RandomIndex Method
	//Generates a random number to be used as an index value	
	public int randomIndex()
	{
		Random gen = new Random();
		int result = gen.nextInt(5);
		return result;
	}
	
	//ToString Method
	//Overrides toString Method
	@Override
	public String toString()
	{
		int row;
		int col;
		String result = "* * * * * * * * * * *"+"\n";
		for ( row = 0; row < 4; row++)
		{
			result = result+"* ";
			for ( col = 0; col < 4; col++)
			{	
				result = result+this.showItem(row,col)+" | ";
			}
			result = result+this.showItem(row,col)+" *"+"\n";
			result = result+"* - * - * - * - * - *"+"\n";
		}
		result = result+"* ";
		for (col = 0; col < 4; col++)
		{
			result = result+this.showItem(row,col)+" | ";
		}
		result = result+this.showItem(row,col)+" *"+"\n";
		result = result+"* * * * * * * * * * *";
		return result;
	}
	
	

	//Room Class
	//Contains all of the methods to create, and manipulate a Room
	public class Room
	{
		protected int HealthP;
		private int VisionP;
		protected int Pit;
		private int Entrance;
		protected int Exit;
		protected int CrownP1;
		protected int CrownP2;
		private int NDoor;
		private int SDoor;
		private int WDoor;
		private int EDoor;
		
		//Constructs a Room
		public Room()
		{
			this.HealthP = randomTwentyPerGen();
			this.VisionP = 0;
			this.Pit = randomTwentyPerGen();
			this.Entrance = 0;
			this.Exit = 0;
			this.CrownP1 = 0;
			this.CrownP2 = 0;
			this.NDoor = 0;
			this.SDoor = 0;
			this.WDoor = 0;
			this.EDoor = 0;
		}
		
		//RandomTenPerGen
		//Creates twenty percent chance
		public int randomTwentyPerGen()
		{
			int [] chanceAra = {0,1,0,0,0,0,0,1,0,0};
			int result = chanceAra[(int)(Math.random()*chanceAra.length)];
			return result;
		}
		
		//ToString Method
		//Overrides toString Method
		@Override
		public String toString()
		{
			String result;
			result = "* ";
			if (this.NDoor == 0)
			{
				result = result+"* *"+"\n";
			}
			if (this.NDoor == 1)
			{
				result = result+"- *"+"\n";
			}
			if (this.WDoor == 0)
			{
				result = result+"* ";
			}
			if (this.WDoor == 1)
			{
				result = result+"| ";
			}
			if (this.HealthP == 1 && this.Pit == 1)
			{
				result = result+"M ";
			}
			if (this.HealthP == 1 && this.Pit == 0)
			{
				result = result+"H ";
			}
			if (this.HealthP == 0 && this.Pit == 1)
			{
				result = result+"P ";
			}
			if (this.CrownP1 == 1 || this.CrownP2 == 1)
			{
				result = result+"C ";
			}
			if (this.Entrance == 1)
			{
				result = result+"I ";
			}
			if (this.Exit == 1)
			{
				result = result+"O ";
			}
			if (this.HealthP == 0 && this.Pit == 0 && this.CrownP1 == 0 && this.CrownP2 == 0 && this.Entrance == 0 && this.Exit == 0)
			{
				result = result+"E ";
			}
			if (this.EDoor == 1)
			{
				result = result+"|"+"\n";
			}
			if (this.EDoor == 0)
			{
				result = result+"*"+"\n";
			}
			result = result+"* ";
			if (this.SDoor == 1)
			{
				result = result+"- *";
			}
			if (this.SDoor == 0)
			{
				result = result+"* *";
			}
			return result;
		} 
	}
}