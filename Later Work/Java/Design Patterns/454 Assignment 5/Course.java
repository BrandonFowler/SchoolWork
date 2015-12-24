
//Team: Larfleeze
//Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
//CSCD454
//Assignment 5

import java.util.*;

/**
 * Course class contains a list of students and
 * provides iterable functionality to traverse
 * the list of students.
 */
public class Course implements Iterable{
	
	protected Student [] students;
	
	public Course(Student[] students){
		this.students = students;
	}
	
	public Iterator iterator(){
		return new CourseIterator(students);
	}
	
	//Provides actual iterator functionality
	public class CourseIterator implements Iterator{
		
		private Student [] studentsList;
		private int cur;
		
		public CourseIterator(Student [] studentsList){
			this.studentsList = studentsList;
			this.cur = 0;
		}
		
		public boolean hasNext(){
			return cur < this.studentsList.length;
		}
		
		public Object next(){
			if(hasNext()){
				return studentsList[cur++];
			}
			else{
				return null;
			}
		}
		
		public void remove(){
			throw new UnsupportedOperationException("Remove is not an option!");
		}
	}
}
