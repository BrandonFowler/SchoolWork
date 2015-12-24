
//Team: Larfleeze
//Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
//Assignment 1

/**
GameCharacter class:
Abstract class with generic behavior for rock stars. 
*/
//=========================================================================================
public abstract class GameCharacter{
	
	//Attributes that all concrete rock stars will use 
	protected String name;
	protected GuitarBehavior guitar;
	protected SoloBehavior solo;
   
	//Generic method to call guitar behaviors
	//=====================================================================================
	public void playGuitar(){
		System.out.println(""+name+" "+guitar.play());
	}
	
	//Generic method to call solo behaviors
	//=====================================================================================
	public void playSolo(){
		System.out.println(""+name+" "+solo.play());
	}
   
	//Set new guitar behaviors
	//=====================================================================================
	public void setGuitar(GuitarBehavior guitar){
		this.guitar = guitar;
	}
   
	//Set new solo behaviors
	//=====================================================================================
	public void setSolo(SoloBehavior solo){
		this.solo = solo;
	}

}