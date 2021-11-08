package main;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
    	
    	 ArrayList <Student> students  = new ArrayList<>();
    	 ArrayList <Course> coursesList = new ArrayList<>();
 //Databases   	  
				    	 String[] Names={"Ali","Veli","Halil","Can","Ayşe","Mehmet",      			   //30
				    	          "Cem","Sena","Mert","Yusuf","Melisa"," Zeynep","Hasan",
				    	          "Berke","Yasin","Murat","Hasan","Mert","Ahmet","Tugba",
				    	          "Gizem","Ozlem","Fatih","Ramiz","Ezel","Utku","Omer",
				    	          "Eylem","Aslı","Osman"};
				    	 String[] Surnames={"Ozturk","Ganiz","Bayraktar","Yilmaz","Taş","Kerim",    	 //20
				    	            "Karaeski","Alemdar","Baş","Yeter","Dundar","Yildiz","Kaya",
				    	            "Erden","Marmara","Ege","Karadeniz","Akdeniz","Doğan","Ulas"};
				    	 
				    	String coursesCodes[]={"CSE3063","CSE2025","CSE2023","CSE2138",                 			 
				    	           "MATH2059","ISG121","ISG122","MATH2056","CSE2003","CSE2004",			//40
				    	           "CSE3061","CSE3072","CSE4081","CSE4082","ECON2003","ECON2004",
				    	           "CSE2003","CSE2004","CSE3061","CSE3072","CSE4081","CSE4082",
				    	           "CSE3067","CSE3076","CSE4089","CSE4090","CSE3003","CSE3004",
				    	           "CSE3050","CSE3055","CSE4040","CSE4022","CSE2162","CSE2182",
				    	           "CSE3129","CSE3127","CSE4124","CSE4250","CSE2223","CSE2250",};
				    	 
				    	 
				    	 String titles[]= {"Associate Professor","Doctor","Professor Doctor"
				    			 	,"Assistant Professor","Assistant"};
  
    	 
    	 Random rand = new Random();
 //random student creating   	    	
    	 for (int i=0; i<10; i++) {
    		 String studentName = Names[(int)rand.nextInt(30)];
    		 String StudentSurname = Surnames[(int)rand.nextInt(20)];
    		 int StudentNumber = 1000000 + rand.nextInt(900000);
    		 int semester = 1+rand.nextInt(7);
 //creating advisor for this student   		     		
    		 String advName = Names[(int)rand.nextInt(30)];
    		 String advSurname = Surnames[(int)rand.nextInt(20)];
    		 String title = titles[(int)rand.nextInt(5)];
    		 int roomLoc = rand.nextInt(500);
    		 Instructor advisor = new Instructor(advName,advSurname,title,roomLoc);
	 
    		 Student student = new Student(studentName, StudentSurname,advisor,StudentNumber, semester);
    		 students.add(student);
    	 } 
    	 

        // gson test
		Course c = new CompulsoryCourse("CSE3063", "Object Oriented Software Design", 6);
        Gson gson = new Gson();
		System.out.println(gson.toJson(c));


		// create random courses
		for (String code : coursesCodes) {
			coursesList.add(new ElectiveCourse(code, 1+rand.nextInt(5)));
		}
		System.out.println(Arrays.toString(coursesList.toArray()));
		System.out.println(Arrays.toString(students.toArray()));


    }
}
