package StudentRegistrationSystem;

import java.util.HashMap;
import java.util.Map;

public class Transcript {
	
	private HashMap<Course, String> coursesTaken = new HashMap(); // Course -> String(lettergrade) , Course -> String(lettergrade), Course -> String(lettergrade)


	public float getGpa() {
		Map<String, Double> gradeMap = new HashMap<>();
		gradeMap.put("AA", 4.00);
		gradeMap.put("BA", 3.50);
		gradeMap.put("BB", 3.00);
		gradeMap.put("CB", 2.50);
		gradeMap.put("CC", 2.00);
		gradeMap.put("DC", 1.50);
		gradeMap.put("DD", 1.00);
		gradeMap.put("FD", 0.50);
		gradeMap.put("FF", 0.00);

		int totalCredits = 0;
		float totalPoints = 0;
		for (HashMap.Entry<Course, String> pair : this.coursesTaken.entrySet()) {
			Course course = pair.getKey();
			String letterGrade = pair.getValue();

			totalPoints += gradeMap.get(letterGrade) * course.getCredit();
			totalCredits += course.getCredit();
		}

		return (float)totalPoints / totalCredits;


	}

	public void addCourseAndLetterGrade(Course course, String letterGrade) {
		this.coursesTaken.put(course, letterGrade);
	}

}