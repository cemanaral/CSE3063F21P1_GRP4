package StudentRegistrationSystem;

import java.util.ArrayList;

public abstract class Course {
// Attributes
    private String courseCode;
    private String name;
    private int credit;
    private Schedule schedule = new Schedule();
    private ArrayList <Course> prerequisites = new ArrayList<>();   
    
// Constructors
    public Course( String courseCode, String name, int credit){
      
        this.courseCode=courseCode;
        this.name=name;
        this.credit=credit;

    }

    public Course( String courseCode, int credit){

        this.courseCode=courseCode;
        this.credit=credit;

    }

// GETTERS AND SETTERS    
    public ArrayList<Course> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(ArrayList<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}
	
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

  
//To String Method
    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", name='" + name + '\'' +
                ", prerequisites=" + prerequisites +
                ", schedule=" + schedule +
                ", credit=" + credit +              
                '}'+"\n";
                
    }
}
