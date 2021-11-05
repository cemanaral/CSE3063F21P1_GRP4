package test;

import main.CompulsoryCourse;
import main.Course;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.*;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class CourseTest {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(CourseTest.class);

    }

    @Test
    public void test_JUnit() {
        Course oosd = new CompulsoryCourse("CSE3063", "Object Oriented Software Design", 6);

        oosd.getSchedule().addLectureHour(0, 0);
        oosd.getSchedule().addLectureHour(1, 0);
        oosd.getSchedule().printSchedule();

    }
}