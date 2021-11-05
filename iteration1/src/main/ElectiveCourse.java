package main;

public class ElectiveCourse extends Course {


    public ElectiveCourse(String courseCode, String name, int credit) {
        super(courseCode, name, credit);
    }

    public ElectiveCourse(String courseCode,  int credit) {
        super(courseCode,  credit);
    }


}
