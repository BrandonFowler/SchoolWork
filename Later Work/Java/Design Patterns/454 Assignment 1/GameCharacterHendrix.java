
//Team: Larfleeze
//Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
//Assignment 1

/**
GameCharacterHendrix class:
Provides a constructor for a concrete GameCharacter object,
with a state pertaining to Jimi Hendrix.
*/
//=========================================================================================
public class GameCharacterHendrix extends GameCharacter{
	
	//Simple constructor to initialize Jimi Hendrix with specified behaviors
	//=====================================================================================
	public GameCharacterHendrix(GuitarBehavior guitar, SoloBehavior solo){
		this.name = "Jimi Hendrix";
		this.guitar = guitar;
	    this.solo = solo;
	}
}
