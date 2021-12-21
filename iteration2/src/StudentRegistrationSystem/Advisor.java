package StudentRegistrationSystem;

import java.util.HashMap;
import java.util.List;

public class Advisor extends Person {

	public Advisor(String name, String surname) {
		super(name, surname);
		
	}

	public boolean checkFteInFall(Student student) {
		// if semester is fall
		if (student.getSemester() % 2 == 1) {
			// if there is any FTE in ApprovalRequest
			for (Course course : student.getApprovalRequest().getCourses()) {
				if (course instanceof FacultyElective) {
					// there is problem
					return false;
				}
			}
		}
		// no problem occurs

		return true;
	}


	public boolean checkEngineeringProjectStatus(Student student) {
		// "The advisor didn't approve graduation project CSE4197 because student completed credits < 165"
		// if sum of completed credits is < 165,
		// returns true

		if (student.getSemester() == 7 || student.getSemester() == 8) {
			int sum = student.getTranscript().getTotalCredits();

			if (sum < 165) {
				return false;
			}
		}
		// no problem occurs
		return true;
	}

}
