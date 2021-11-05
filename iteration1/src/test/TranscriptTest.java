package test;


import main.CompulsoryCourse;
import main.Course;
import main.ElectiveCourse;
import main.Transcript;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.*;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class TranscriptTest {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(TranscriptTest.class);

    }

    @Test
    public void testTranscript() {
        System.out.println("\n\n");

        Transcript transcript = new Transcript();
        transcript.addCourseAndLetterGrade(new CompulsoryCourse("CSE3033", 4), "AA");
        transcript.addCourseAndLetterGrade(new ElectiveCourse("CSE4093", 3), "BA");
        transcript.addCourseAndLetterGrade(new ElectiveCourse("OB3000", 3), "FF");
        System.out.println(transcript.calculateGpa());
    }
}