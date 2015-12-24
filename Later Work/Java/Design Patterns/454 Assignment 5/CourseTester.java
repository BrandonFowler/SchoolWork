
//Team: Larfleeze
//Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
//CSCD454
//Assignment 5

import java.util.*;

/**
 * CourseTester class simply initializes
 * a Course object, and tests the Course
 * object's iterable implementation;
 */
public class CourseTester {
	
	public static void main(String [] args){
		
		//Initialize a Course object with a simple Student array
		Course course = new Course(new Student [] {new Student("Nathan Graham",1111),new Student("Matt Wilhelm",2222)
		 										            ,new Student("Brandon Fowler",3333), new Student("Tom Capaul",4444)
		 									               ,new Student("Michael Jackson",5555), new Student("Santa Clause",6666)});
		
		//Test course iterator with a for each loop(print each students info)
		for(Object o : course){
		   System.out.println(o);
			System.out.println();
		}
	}

}
