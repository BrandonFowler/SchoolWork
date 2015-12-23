//Creator: Brandon Fowler
//Dungeon.java(Part of Assignment 3)
//Class:CSCD 211


import java.util.*;
import java.math.*;



/**
Dungeon Class. Contains all nethods needed to create
and manipulate an Object of type Dungeon. Also
contains Room Class.   
<p><b>
Extra Credit: 
</b><pre> 
N/A
</pre><b>  
History: 
</b><pre> 
Created 4/26/2012, No Changes made since.
</pre>  
@author	Brandon Fowler 
*/

public class Dungeon
{
	protected Room[][] Dungeon;
	protected Room HeroPosition;
	protected int StartRow;
	protected int StartCol;
	
	/**
	Dungeon method. Constructs a Dungeon object. 
 	@param    None
 	@return	 None
	 */ 

	public Dungeon()
	{
		this.Dungeon = new Room[5][5];
		this.HeroPosition = this.Dungeon[0][0];
	}
	
	/**
	MakeRooms Method. Creates Rooms in current Dungeon. 
 	@param      None
 	@return	void - Does not return a value
	 */ 

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
	
	/**
	MakeDoors Method. Sets Door values in Rooms of current Dungeon. 
 	@param       None
 	@return	void - Does not return a value
	 */ 

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
	
	/**
	setMonsters Method. Sets Monster values in Rooms of current Dungeon. 
 	@param       None
 	@return	void - Does not return a value
	 */ 

	public void setMonsters()
	{
		int row;
		int col;
		double monsterSpawn = .4;
		double childSpawn = .35;
		double vaporSpawn = .70;
		for (row = 0; row < this.Dungeon.length; row++)
		{
			for (col = 0; col < this.Dungeon[row].length; col++)
			{
				if (Math.random() < monsterSpawn)
				{
					if(Math.random() < childSpawn)
					{
						this.Dungeon[row][col].ChildOfInsanity = 1;
					}
					else if(Math.random() < vaporSpawn)
					{
						this.Dungeon[row][col].EvilVapor = 1;
					}
					else
					{
						this.Dungeon[row][col].LeperCannibal = 1;
					}
				}
			}
		} 
	}
	
	/**
	setEntranceAndHero Method. Sets entrance value to a Room int the current Dungeon. 
	Also, sets HeroPosition in the same Room.
 	@param       None
 	@return	void - Does not return a value
	 */ 

	public void setEntranceAndHero()
	{
		int row = randomIndex();
		int col = randomIndex();
		this.Dungeon[row][col].Entrance = 1;
		this.HeroPosition = Dungeon[row][col];
		this.Dungeon[row][col].HealthP = 0;
		this.Dungeon[row][col].Pit = 0;
		this.Dungeon[row][col].LeperCannibal = 0;
		this.Dungeon[row][col].ChildOfInsanity = 0;
		this.Dungeon[row][col].EvilVapor = 0;
		this.StartRow = row;
		this.StartCol = col;

	}
	
	/**
	setExit Method. Sets Exit value in a Room of the current Dungeon. 
 	@param       None
 	@return	void - Does not return a value
	 */ 
	
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
			this.Dungeon[row][col].LeperCannibal = 0;
			this.Dungeon[row][col].ChildOfInsanity = 0;
			this.Dungeon[row][col].EvilVapor = 0;
		}
	}
	
	/**
	setCrown1 Method. Sets first crown piece value to a Room in the current Dungeon. 
 	@param       None
 	@return	void - Does not return a value
	 */ 

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
			this.Dungeon[row][col].LeperCannibal = 0;
			this.Dungeon[row][col].ChildOfInsanity = 0;
			this.Dungeon[row][col].EvilVapor = 0;
			this.Dungeon[row][col].Hydra = 1;
		}
	}
	
	/**
	setCrown2 Method. Sets second crown piece value to a Room in the current Dungeon. 
 	@param       None
 	@return	void - Does not return a value
	 */ 

	public void setCrown2()
	{
		int row = randomIndex();
		int col = randomIndex();
		if (this.Dungeon[row][col].Entrance==1 || this.Dungeon[row][col].Exit==1 || this.Dungeon[row][col].CrownP1 ==1)
		{
			setCrown2();
		}
		else
		{
			this.Dungeon[row][col].CrownP2 = 1;
			this.Dungeon[row][col].HealthP = 0;
			this.Dungeon[row][col].Pit = 0;
			this.Dungeon[row][col].LeperCannibal = 0;
			this.Dungeon[row][col].ChildOfInsanity = 0;
			this.Dungeon[row][col].EvilVapor = 0;
			this.Dungeon[row][col].DemonOfViolence = 1;
		}

	}
	
	/**
	setGoldenClaw Method. Sets GoldenClaw value to a Room in the Dungeon. 
 	@param       None
 	@return	void - Does not return a value
	 */ 

	public void setGoldenClaw()
	{
		int row = randomIndex();
		int col = randomIndex();
		if (this.Dungeon[row][col].Entrance==1 || this.Dungeon[row][col].Exit==1 || this.Dungeon[row][col].CrownP1 ==1 || this.Dungeon[row][col].CrownP2 ==1)
		{
			setGoldenClaw();
		}
		else
		{
			this.Dungeon[row][col].GoldenClaw = 1;
			this.Dungeon[row][col].HealthP = 0;
			this.Dungeon[row][col].Pit = 0;
			this.Dungeon[row][col].LeperCannibal = 0;
			this.Dungeon[row][col].ChildOfInsanity = 0;
			this.Dungeon[row][col].EvilVapor = 0;
		}
	}

	
	/**
	showItem Method. Returns a String representation of any items in the Room
	that is past in. 
 	@param       int,int
 	@return		 String
	 */ 	
	
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
	
	/**
	randomIndex Method. Generates two random numbers within range
	to be used as index values. 
 	@param       None
 	@return		 int
	 */ 
		
	public int randomIndex()
	{
		Random gen = new Random();
		int result = gen.nextInt(5);
		return result;
	}
	
	/**
	toStirng Method. Overrides toString Method inherited from parent. 
 	@param       None
 	@return		 String
	 */ 

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
	
	

	/**
	Room Class. Contains all methods needed to create and manipulate
	objects of type Room   
	<p><b>
	Extra Credit: 
	</b><pre> 
	N/A
	</pre><b>  
	History: 
	</b><pre> 
	Created 4/26/2012, No Changes made since.
	</pre>  
	@author	Brandon Fowler 
	*/

	public class Room
	{
		protected int HealthP;
		private int VisionP;
		protected int Pit;
		private int Entrance;
		protected int Exit;
		protected int CrownP1;
		protected int CrownP2;
		protected int GoldenClaw;
		private int NDoor;
		private int SDoor;
		private int WDoor;
		private int EDoor;
		protected int LeperCannibal;
		protected int EvilVapor;
		protected int ChildOfInsanity;
		protected int DemonOfViolence;
		protected int Hydra;
		
		
		/**
		Room method. Constructs a Room object. 
 		@param    None
 		@return	 None
		 */ 

		public Room()
		{
			this.HealthP = randomTwentyPerGen();
			this.VisionP = 0;
			this.Pit = randomTwentyPerGen();
			this.Entrance = 0;
			this.Exit = 0;
			this.CrownP1 = 0;
			this.CrownP2 = 0;
			this.GoldenClaw = 0;
			this.NDoor = 0;
			this.SDoor = 0;
			this.WDoor = 0;
			this.EDoor = 0;
			this.LeperCannibal = 0;
			this.EvilVapor = 0;
			this.ChildOfInsanity = 0;
			this.DemonOfViolence = 0;
			this.Hydra = 0;
		}
		
		/**
		randomTwentyPerGen Method. Generates a twenty percent chance. 
 		@param       None
 		@return	    int
	 	*/ 

		public int randomTwentyPerGen()
		{
			int [] chanceAra = {0,1,0,0,0,0,0,1,0,0};
			int result = chanceAra[(int)(Math.random()*chanceAra.length)];
			return result;
		}
		
		/**
		toString Method. Overrides toString method inherited from parent class. 
 		@param       None
 		@return		 String
	 	*/ 

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