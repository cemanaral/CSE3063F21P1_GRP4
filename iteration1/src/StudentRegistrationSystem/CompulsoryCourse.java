package StudentRegistrationSystem;

public class CompulsoryCourse extends Course{

    public CompulsoryCourse(String courseCode , String name , int credit) {
        super(courseCode , name , credit);

    }

    @Override
    public String toString() {
        return "CompulsoryCourse{" +
                "courseCode='" + getCourseCode() + '\'' +
                ", name='" + getName() + '\'' +
                ", credit=" + getCredit() +
                //", schedule=" + getSchedule() +
                ", prerequisites=" + getPrerequisites() +
                ", semester=" + getSemester() +
                '}';
    }
}