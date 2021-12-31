package StudentRegistrationSystem;

import java.util.ArrayList;

/**
 * This class is used for encapsulating
 * courses a Student wants to enroll.
 */
public class ApprovalRequest {
	private boolean isApproved;
	private ArrayList <Course> courses = new ArrayList<Course>();

	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public ArrayList<Course> getCourses() {
		return courses;
	}
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	/**
	 * Adds a course to ApprovalRequest
	 *
	 * @param course
	 * @return false if course was already added else true
	 */
	public boolean addCourse(Course course) {
		if (this.courses.contains(course))
			return false;

		this.courses.add(course);
		return true;
	}
	@Override
	public String toString() {
		return "ApprovalRequest{" +
				"isApproved=" + isApproved +
				", CurrentCourses=" + courses +
				// ", schedule=" + schedule +
				'}';
	}
	

}
