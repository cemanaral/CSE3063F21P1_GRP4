package StudentRegistrationSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class encapsulates a Student's courses and grades
 */
public class Transcript {
	
	private HashMap<Course, String> coursesTaken = new HashMap(); // Course -> String(lettergrade) , Course -> String(lettergrade), Course -> String(lettergrade)

	/**
	 * This method calculates the sum
	 * credits which a student completed
	 *
	 * @implNote FF and FD grades are assumed
	 * to be failing grades (ie not completed)
	 *
	 * @return sum of completed credits
	 */
	public int getTotalCredits() {
		int sum = 0;
		for (HashMap.Entry<Course, String> pair: this.getCoursesTaken().entrySet()) {
			Course course = pair.getKey();
			String letterGrade = pair.getValue();

			if (!List.of("FF", "FD").contains(letterGrade)) {
				sum += course.getCredit();
			}

		}
		return sum;
	}

	/**
	 * Calculates the GPA in the transcript
	 *
	 * @implNote If there is no course, returns NaN
	 * @implNote Turkey's grading system was used
	 * @see @{https://en.wikipedia.org/wiki/Grading_systems_by_country#Turkey}
	 *
	 * @return Float indicating the GPA of given Student
	 */
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

	/**
	 * This method adds a course and grade achieved
	 * to transcript
	 *
	 * @param course Course object to be added to Transcript
	 * @param letterGrade String that indicates the letter grade
	 */
	public void addCourseAndLetterGrade(Course course, String letterGrade) {
		this.coursesTaken.put(course, letterGrade);
	}

	public HashMap<Course, String> getCoursesTaken() {
		return coursesTaken;
	}
	@Override
	public String toString() {
		return "Transcript{" +
				"coursesTaken=" + coursesTaken +
				'}'+"student's GPA = "+getGpa();
	}


}