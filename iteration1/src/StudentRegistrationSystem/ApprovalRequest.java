package StudentRegistrationSystem;

import java.util.ArrayList;

public class ApprovalRequest {
// Attributes	
	
	private boolean isApproved;
	private ArrayList <Course> courses = new ArrayList<Course>();
	private Schedule schedule;
	
	
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
	
	

}
