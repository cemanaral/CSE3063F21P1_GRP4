package StudentRegistrationSystem;

import static org.junit.jupiter.api.Assertions.*;

class TranscriptTest {

    // error acceptence threshold for
    // gpa calculation
    private final float gpaErrorThreshold = (float)0.01;

    @org.junit.jupiter.api.Test
    public void testGetTotalCredits() {
        Transcript transcript = new Transcript();

        assertEquals(0, transcript.getTotalCredits(), "with no course");

        transcript.addCourseAndLetterGrade(new CompulsoryCourse("code", "name", 10, 1), "FF");
        transcript.addCourseAndLetterGrade(new NonTechnicalElective("code", "name", 5, 2), "FD");
        assertEquals(0, transcript.getTotalCredits(), "with failed courses");

        transcript.addCourseAndLetterGrade(new TechnicalElective("code", "name", 5, 8), "AA");
        assertEquals(5, transcript.getTotalCredits(), "with one course");

        for (int i=0; i < 50; i++)
            transcript.addCourseAndLetterGrade(new CompulsoryCourse("code", "name", 3, 3), "CC");

        assertEquals(155, transcript.getTotalCredits(), "with 51 courses in total 155 credits");
    }

    @org.junit.jupiter.api.Test
    public void testGetGpa() {
        Transcript transcript = new Transcript();

        assertEquals(Float.NaN, transcript.getGpa(), "with no courses");

        transcript.addCourseAndLetterGrade(new CompulsoryCourse("code", "name", 5, 1), "FF");
        assertEquals((float)0, transcript.getGpa(), "with one failed course");

        transcript.addCourseAndLetterGrade(new CompulsoryCourse("code", "name", 4, 2), "AA");
        assertEquals((float)1.78, transcript.getGpa(), gpaErrorThreshold, "with two courses");

        transcript.addCourseAndLetterGrade(new CompulsoryCourse("code", "name", 6, 2), "BB");
        transcript.addCourseAndLetterGrade(new CompulsoryCourse("code", "name", 3, 2), "CB");
        transcript.addCourseAndLetterGrade(new CompulsoryCourse("code", "name", 7, 2), "DD");
        assertEquals((float)1.94, transcript.getGpa(), gpaErrorThreshold, "with many courses");

        transcript.addCourseAndLetterGrade(new CompulsoryCourse("code", "name", 6, 2), "AA");
        transcript.addCourseAndLetterGrade(new CompulsoryCourse("code", "name", 6, 2), "AA");
        transcript.addCourseAndLetterGrade(new CompulsoryCourse("code", "name", 6, 2), "AA");
        assertEquals((float)2.80, transcript.getGpa(), gpaErrorThreshold, "with many courses");
    }
}
