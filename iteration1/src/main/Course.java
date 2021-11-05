package main;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Course {

    private String courseCode;
    private String name;
    private ArrayList <Course> prerequisites = new ArrayList<>();

    private Schedule schedule = new Schedule();


    private int credit;
    private Instructor instructor;
    private ArrayList <Student> studentsTakingTheCourse = new ArrayList<>();


    public Course( String courseCode, String name, int credit){
      
        this.courseCode=courseCode;
        this.name=name;
        this.credit=credit;

    }

    public Course( String courseCode, int credit){

        this.courseCode=courseCode;
        this.credit=credit;

    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public boolean addStudent(Student student) {
        this.studentsTakingTheCourse.add(student);
        return true;
    }

    public void addPrerequisiteCourse(Course course) {
        this.prerequisites.add(course);
    }



    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", name='" + name + '\'' +
                ", prerequisites=" + prerequisites +
                ", schedule=" + schedule +
                ", credit=" + credit +
                ", instructor=" + instructor +
                ", studentsTakingTheCourse=" + studentsTakingTheCourse +
                '}';
    }
}

