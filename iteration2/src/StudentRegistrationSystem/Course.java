package StudentRegistrationSystem;
import java.util.ArrayList;

/**
 * This class is used to
 * encapsulate Course information
 */
public abstract class Course {
// Attributes
    private String courseCode;
    private String name;
    private int credit;
    private ArrayList <Course> prerequisites = new ArrayList<>();
    private int semester;
    
// Constructors

    public Course(String courseCode, String name, int credit, int semester) {
        this.courseCode = courseCode;
        this.name = name;
        this.credit = credit;
        this.semester = semester;
        this.prerequisites = new ArrayList<Course>();
    }

    // GETTERS AND SETTERS
    public ArrayList<Course> getPrerequisites() {
        if (prerequisites==null) {
            prerequisites = new ArrayList<>();
        }

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



    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void addPrerequisite(Course course) {
        this.prerequisites.add(course);
    }

}
