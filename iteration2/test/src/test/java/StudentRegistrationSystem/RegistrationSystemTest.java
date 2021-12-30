package StudentRegistrationSystem;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationSystemTest {
    private RegistrationSystem registrationSystem = RegistrationSystem.getInstance();

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
}