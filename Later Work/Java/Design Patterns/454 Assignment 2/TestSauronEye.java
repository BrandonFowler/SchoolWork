
//Team: Larfleeze
//Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
//Assignment 2

/**
TestSauronEye class:
Contains main, builds EyeOfSauron Observable object, and BadGuy Observer objects and
tests Observer pattern functionality.
**/
public class TestSauronEye {
	
	public static void main(String[] args) {

	
        EyeOfSauron eye = new EyeOfSauron(); //Initialize Observable EyeOfSauron
		
        //Register two Observers(Saruman and Angmar)
        BadGuy saruman = new BadGuy(eye, "Saruman");
        BadGuy angmar = new BadGuy(eye, "Angmar");
        
        //Update Observable info(hobbits, elves, dwarves, men)
        eye.setEnemies(1, 1, 2, 0);
        
        //Unregister an observer(Saruman)
        saruman.defeated(); 
        //eye.deleteObserver(saruman);
        
        //Update Observable info(hobbits, elves, dwarves, men)
        eye.setEnemies(4, 2, 2, 100);

    }
}
