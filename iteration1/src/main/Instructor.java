package main;

public class Instructor extends Person {
	
	String title;
	int roomLocation;
	
	public Instructor(String name,String surname,String title,int roomLocation) {
		super(name, surname);
		this.title = title;
		this.roomLocation = roomLocation;
	}

}
