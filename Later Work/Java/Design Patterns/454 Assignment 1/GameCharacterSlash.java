
//Team: Larfleeze
//Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
//Assignment 1

/**
GameCharacterSlash class:
Provides a constructor for a concrete GameCharacter object,
with a state pertaining to Slash.
*/
//=========================================================================================
public class GameCharacterSlash extends GameCharacter{

		//Simple constructor to initialize Slash with specified behaviors
		//=================================================================================
		public GameCharacterSlash(GuitarBehavior guitar, SoloBehavior solo){
			this.name = "Slash";
			this.guitar = guitar;
		    this.solo = solo;
		}
}
