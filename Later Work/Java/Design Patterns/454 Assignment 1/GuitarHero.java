
//Team: Larfleeze
//Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
//Assignment 1

/**
 GuitarHero class:
 Simple tester for guitar hero program(Contains main). 
*/
//==========================================================================================================
public class GuitarHero {
	//Simple main to perform tests of guitar hero functionality
	//======================================================================================================
    public static void main(String[] args) {
    	
    	//Initialize three different rock stars with guitar and solo behaviors
        GameCharacter player1 = new GameCharacterSlash(new PlaySG(), new SoloFire());
        GameCharacter player2 = new GameCharacterHendrix(new PlayTelecaster(),new SoloJump());
        GameCharacter player3 = new GameCharacterYoung(new PlayV(),new SoloSmash());
        
        //Play guitar and solo behaviors for all rock stars
        player1.playGuitar();
        player2.playGuitar();
        player3.playGuitar();
        player1.playSolo();
        player2.playSolo();
        player3.playSolo();
        
        
        System.out.println();
        System.out.println("Switch up guitars and solos");
        System.out.println();
        
        //Give rocks stars new guitar and solo behaviors
        player1.setGuitar(new PlayTelecaster());
        player1.setSolo(new SoloSmash());
        player2.setGuitar(new PlayV());
        player2.setSolo(new SoloFire());
        player3.setGuitar(new PlaySG());
        player3.setSolo(new SoloJump());
        
        //Play guitar and solo behaviors for all rock stars
        player1.playGuitar();
        player2.playGuitar();
        player3.playGuitar();
        player1.playSolo();
        player2.playSolo();
        player3.playSolo();
    }//End main
}