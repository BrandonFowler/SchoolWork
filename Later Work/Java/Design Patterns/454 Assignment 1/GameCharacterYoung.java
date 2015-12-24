
//Team: Larfleeze
//Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
//Assignment 1

/**
GameCharacterYoung class:
Provides a constructor for a concrete GameCharacter object,
with a state pertaining to Angus Young.
*/
//=========================================================================================
public class GameCharacterYoung extends GameCharacter{
	
	//Simple constructor to initialize Angus Young with specified behaviors
	//=====================================================================================
	public GameCharacterYoung(GuitarBehavior guitar, SoloBehavior solo){
		this.name = "Angus Young";
		this.guitar = guitar;
	    this.solo = solo;
	}
}
