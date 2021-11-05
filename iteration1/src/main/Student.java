package main;

import java.util.ArrayList;

public class Student extends Person{
	
//Student's attributes	
	Instructor advisor;
	int studentNumber;
	int semester;
	int[][] schedule;
	Transcript transcript;
	ArrayList <Course> currentCourses = new ArrayList<>();
 
 //Student constructor. It inherits (name+surname) from Person class 
  public Student(String name,String surname, Instructor advisor,int studentNumber,int semester) {
	  super();			// name + surname comes from Person
	  this.advisor=advisor;
	  this.studentNumber=studentNumber;
	  this.semester=semester;	  
  }
  
  public boolean addToCurrentCourse(Course course){
	  return true;
  }
  
  public void  dropCourse(Course course) {
	  
  }

  
  @Override
  public String toString() {
      return super.toString();
  }
}
