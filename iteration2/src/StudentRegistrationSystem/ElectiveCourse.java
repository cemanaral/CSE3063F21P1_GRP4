package StudentRegistrationSystem;

public abstract class ElectiveCourse extends Course {

    public ElectiveCourse(String courseCode, String name, int credit, int semester) {
        super(courseCode, name, credit, semester);
    }
}