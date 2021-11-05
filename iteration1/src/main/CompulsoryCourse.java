package main;

public class CompulsoryCourse extends Course{

    public CompulsoryCourse(String courseCode , String name , int credit) {
        super(courseCode , name , credit);

    }

    public CompulsoryCourse(String courseCode , int credit) {
        super(courseCode , credit);

    }
}
