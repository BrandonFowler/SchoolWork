
//Team: Larfleeze
//Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
//CSCD454
//Assignment 5

/**
 * Student class contains simple student
 * information, and a toString override
 * for printing student information.
 */
public class Student {
	
	protected String name;
	protected int ID;
	
	public Student(String name, int ID){
		this.name = name;
		this.ID = ID;
	}
	
	public Student(){
		this.name = "";
		this.ID = 0;
	}
	
	@Override
	public String toString(){
		return "Name:"+this.name+"\n"+"ID:"+this.ID;
	}
}
