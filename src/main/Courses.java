package studentRegistirationSystem;

import java.util.ArrayList;
public class Courses {

   

    int courseId;
    String category;
    String courseName;
    String prerequisite;//boolean?
    String courses1[]={"CSE3063","CSE2025","CSE2023","CSE2138",                 			 //10
           "MATH2059","ISG121","ISG122","MATH2056","CSE2003","CSE2004"};


    public Courses(int courseId, String category, String courseName, String prerequisite){
        this.courseId=courseId;
        this.courseName=courseName;
        this.category=category;
        this.prerequisite=prerequisite;

    }
 
	@Override
    public String toString() {
        return super.toString();
    }
    
   
    public void printCourseInfo(){

        System.out.println("Course Name:"+courseName+"Course Id:"+courseId+"Category:"+category);
    }
}

