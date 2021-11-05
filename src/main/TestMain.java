package studentRegistirationSystem;
import java.util.Random;
import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
    	
    	 ArrayList <Student> students  = new ArrayList<>();
    	 String[] Names={"Ali","Veli","Halil","Can","Ayþe","Mehmet",      			   //30
    	          "Cem","Sena","Mert","Yusuf","Melisa"," Zeynep","Hasan",
    	          "Berke","Yasin","Murat","Hasan","Mert","Ahmet","Tugba",
    	          "Gizem","Ozlem","Fatih","Ramiz","Ezel","Utku","Omer",
    	          "Eylem","Aslý","Osman"};
    	 String[] Surnames={"Ozturk","Ganiz","Bayraktar","Yilmaz","Taþ","Kerim",    	 //20
    	            "Karaeski","Alemdar","Baþ","Yeter","Dundar","Yildiz","Kaya",
    	            "Erden","Marmara","Ege","Karadeniz","Akdeniz","Doðan","Ulas"};
    	 ArrayList <Courses> courses = new ArrayList<>();
    	 Random rand = new Random();
    	    	
    	 for (int i=0; i<10; i++) {

    	 int number = 1000000 + rand.nextInt(900000);
    	 String name = Names[i] ;
    	 String surname = Surnames[i];
    	 int semester = rand.nextInt(8);
    	 Student student = new Student(number, name, surname,semester);
    	 students.add(student);
    	 } 
    	 
       
          
        Courses course1 = new Courses(123,"essential","OOSD","??");
              
        System.out.print(course1.courseName+" ");
        System.out.println(course1.category);  
        courses.add(course1);
         
        Courses course2 = new electiveCourses(155,"elective","german","?") ;
        courses.add(course2);
        System.out.print(course2.courseName+" ");
        System.out.println(course2.category);
        
        Courses course3 = new electiveCourses(2138,"essential","Sysem Programming","?") ;
        courses.add(course3);
        
        System.out.print(course3.courseName+" ");
        System.out.println(students);
        

    }
}