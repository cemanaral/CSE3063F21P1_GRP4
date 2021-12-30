package StudentRegistrationSystem;

import static org.junit.jupiter.api.Assertions.*;

public class AdvisorTest {
    private Student createExampleStudentInFall() {
        return new Student("Mahmut", "Yilmaz", 123,
                7, new Advisor("Ahmet", "Ozturk"));
    }

    @org.junit.jupiter.api.Test
    public void testCheckFteInFall() {
        Student student = createExampleStudentInFall();

        // no course in ApprovalRequest
        assertTrue(
                student.getAdvisor().checkFteInFall(student),
                "no course in ApprovalRequest"
        );

        // no fte in fall
        student.getApprovalRequest().addCourse(
                new CompulsoryCourse("CSE4003", "Microprocessors", 4, 7)
        );
        assertTrue(
                student.getAdvisor().checkFteInFall(student),
                "no fte in fall"
        );

        // fte in fall
        student.getApprovalRequest().addCourse(
                new FacultyElective("ENG4006", "Innovative Design", 4, 7)
        );
        assertFalse(
                student.getAdvisor().checkFteInFall(student),
                "fte in fall"
        );
    }

    @org.junit.jupiter.api.Test
    public void testCheckEngineeringProjectStatus() {
        Student student = createExampleStudentInFall();

        assertFalse(student.getAdvisor().checkEngineeringProjectStatus(student), "0 credits completed (no courses taken)");

        student.getTranscript().addCourseAndLetterGrade(new CompulsoryCourse("CSE1000", "Intro", 10, 1), "FF");
        assertFalse(student.getAdvisor().checkEngineeringProjectStatus(student), "0 credits completed (one course taken and FF'd)");

        // completing 165 credits
        for (int i=0; i < 33; i++)
            student.getTranscript().addCourseAndLetterGrade(new CompulsoryCourse("code", "name", 5, 4), "BB");

        assertTrue(student.getAdvisor().checkEngineeringProjectStatus(student));
    }
}
