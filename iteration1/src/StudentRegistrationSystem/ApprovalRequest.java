package StudentRegistrationSystem;

import java.util.ArrayList;

public class ApprovalRequest {
	// Attributes
	private boolean isApproved;
	private ArrayList <Course> courses = new ArrayList<Course>();



	private Schedule schedule = new Schedule();
	
	
	//GETTERS AND SETTERS

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
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public boolean addCourse(Course course) {
		if (this.courses.contains(course))
			return false;

		this.courses.add(course);
		this.schedule.addLectureHour(course.getSchedule());

		return true;
	}
	@Override
	public String toString() {
		return "ApprovalRequest{" +
				"isApproved=" + isApproved +
				", CurrentCourses=" + courses +
				", schedule=" + schedule +
				'}';
	}
	

}
