package StudentRegistrationSystem;

public class TechnicalElective extends ElectiveCourse{

	public TechnicalElective(String courseCode, String name, int credit, int semester) {
		super(courseCode, name, credit, semester);
	}

	@Override
	public String toString() {
		return "TechnicalElective{" +
				"courseCode='" + getCourseCode() + '\'' +
				", name='" + getName() + '\'' +
				", credit=" + getCredit() +
				// ", schedule=" + getSchedule() +
				", prerequisites=" + getPrerequisites() +
				", semester=" + getSemester() +
				'}';
	}

}


