package studentRegistirationSystem;

import java.util.ArrayList;

public abstract class Course {

   
    String courseCode;
    String name;
    ArrayList <Course> prerequisites = new ArrayList<>();
    int[][]schedule;                                      // 6 days from Monday to Saturday(6 columns)  
    							  // and 10 hours(10 rows) if there is
    							  // a lecture fill it with 1's else 0's
    int credit;
    Instructor instructor;
    ArrayList <Student> studentsTakingTheCourse = new ArrayList<>();


    public Course( String courseCode, String name, int credit){
      
        this.courseCode=courseCode;
        this.name=name;
        this.credit=credit;
         
    }
 
	@Override
    public String toString() {
        return super.toString();
    }
    
   
    public void printCourseInfo(){

        System.out.println("Course Code :"+courseCode+" Course name :"+name);
    }
}

