package main;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Course {

    private String courseCode;
    private String name;
    private ArrayList <Course> prerequisites = new ArrayList<>();
    // 6 days from Monday to Saturday(6 columns)
    // and 10 hours(10 rows) if there is
    // a lecture fill it with 1's else 0's
    private int[][] schedule = new int[10][6];
    private int credit;
    private Instructor instructor;
    private ArrayList <Student> studentsTakingTheCourse = new ArrayList<>();


    public Course( String courseCode, String name, int credit){
      
        this.courseCode=courseCode;
        this.name=name;
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

    public int[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(int[][] schedule) {
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

    public void addStudent(Student student) {
        this.studentsTakingTheCourse.add(student);
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
                ", schedule=" + Arrays.toString(schedule) +
                ", credit=" + credit +
                ", instructor=" + instructor +
                ", studentsTakingTheCourse=" + studentsTakingTheCourse +
                '}';
    }
}

