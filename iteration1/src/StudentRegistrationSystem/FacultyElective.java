package StudentRegistrationSystem;

public class FacultyElective extends ElectiveCourse {

	public FacultyElective(String courseCode,String name, int credit) {
		super(courseCode, credit);
		
	}

	@Override
	public String toString() {
		return "FacultyElective{" +
				"courseCode='" + getCourseCode() + '\'' +
				", name='" + getName() + '\'' +
				", credit=" + getCredit() +
				// ", schedule=" + getSchedule() +
				", prerequisites=" + getPrerequisites() +
				", semester=" + getSemester() +
				'}';
	}
}
