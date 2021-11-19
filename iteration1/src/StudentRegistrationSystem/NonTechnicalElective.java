package StudentRegistrationSystem;

public class NonTechnicalElective extends ElectiveCourse {

	public NonTechnicalElective(String courseCode, String name, int credit) {
		super(courseCode, name, credit);
		
	}


	@Override
	public String toString() {
		return "NonTechnicalElective{" +
				"courseCode='" + getCourseCode() + '\'' +
				", name='" + getName() + '\'' +
				", credit=" + getCredit() +
				// ", schedule=" + getSchedule() +
				", prerequisites=" + getPrerequisites() +
				", semester=" + getSemester() +
				'}';
	}

}
