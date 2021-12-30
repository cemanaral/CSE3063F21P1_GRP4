package StudentRegistrationSystem;

import com.sun.source.tree.AssertTree;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationSystemTest {
    private RegistrationSystem registrationSystem = RegistrationSystem.getInstance();

    // example courses for testPrerequisitesSatisfied
    private Course systems_programming;
    private Course digital_design;
    private Course computer_organization;
    private Course modelling;
    /////////////////////////////////////////////////

    private Student createExampleStudent() {
        return new Student("Mahmut", "Yilmaz", 123,
                3, new Advisor("Ahmet", "Ozturk"));
    }

    @org.junit.jupiter.api.Test
    public void testCheckCreditLimitExceeds() {
        Student student = createExampleStudent();

        // with no courses
        assertFalse(registrationSystem.checkCreditLimitExceeds(student), "with no courses");

        // with total course credits < 40
        student.getApprovalRequest().addCourse(new CompulsoryCourse("CSE1000", "Intro", 20,3));
        student.getApprovalRequest().addCourse(new CompulsoryCourse("CSE1001", "Not Intro", 10,3));
        assertFalse(registrationSystem.checkCreditLimitExceeds(student), "with total course credits < 40");

        // with total course credits == 40
        student.getApprovalRequest().addCourse(new NonTechnicalElective("OB3001", "Organizational Behavior", 10, 3));
        assertFalse(registrationSystem.checkCreditLimitExceeds(student), "with total course credits == 40");

        // with total course credits > 40
        student.getApprovalRequest().addCourse(new NonTechnicalElective("VB3001", "Vorganizational Behavior", 10, 3));
        assertTrue(registrationSystem.checkCreditLimitExceeds(student), "with total course credits > 40");
    }

    @org.junit.jupiter.api.Test
    public void testPrerequisitesSatisfied() {
        setupCoursesForPrerequisiteTest();
        Student student = createExampleStudent();


        assertTrue(
            registrationSystem.checkPrerequisitesSatisfied(student),
                "without any added course to ApprovalRequest"
        );

        student.getApprovalRequest().addCourse(modelling);
        assertTrue(
                registrationSystem.checkPrerequisitesSatisfied(student),
                "ApprovalRequest contains a course without any prerequisites"
        );

        student.getApprovalRequest().addCourse(computer_organization);
        assertFalse(
                registrationSystem.checkPrerequisitesSatisfied(student),
                "prereq not satisfied"
        );

        student.getTranscript().addCourseAndLetterGrade(systems_programming, "AA");
        student.getTranscript().addCourseAndLetterGrade(digital_design, "BB");
        assertTrue(
                registrationSystem.checkPrerequisitesSatisfied(student),
                "prereqs satisfied"
        );

    }

    private void setupCoursesForPrerequisiteTest() {
        systems_programming = new CompulsoryCourse("CSE2138", "Systems Programming", 7, 4);
        digital_design = new CompulsoryCourse("CSE3215", "Digital Logic Design", 6, 5);
        computer_organization = new CompulsoryCourse("CSE3038", "Computer Organization", 7, 6);
        modelling = new CompulsoryCourse("IE3081", "Modelling and Discrete Simulation", 4, 5);

        computer_organization.addPrerequisite(digital_design);
        computer_organization.addPrerequisite(systems_programming);
    }
}