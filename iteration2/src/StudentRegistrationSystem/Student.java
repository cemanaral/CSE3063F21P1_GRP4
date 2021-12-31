package StudentRegistrationSystem;
import java.util.ArrayList;

/**
 * This class represents a student
 *
 * @see {@link Person}
 */
public class Student extends Person {

    //Student's attributes
    private int studentNumber;
    private int semester;
    private Transcript transcript = new Transcript();
    private ApprovalRequest approvalRequest = new ApprovalRequest();
    private Advisor advisor;

    //Student constructor. It inherits (name+surname) from Person class
    public Student(String name, String surname, int studentNumber, int semester, Advisor advisor) {
        super(name, surname);            // name + surname comes from Person
        this.studentNumber = studentNumber;
        this.semester = semester;
        this.advisor = advisor;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber = " + studentNumber +
                ", studentName = "+getName()+
                ", studentSurname = "+getSurname()+
                ", semester = " + semester +
                ", transcript = " + transcript +
                ", approvalRequest = " + approvalRequest +
                '}';
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }


    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public ApprovalRequest getApprovalRequest() {
        return approvalRequest;
    }

    public void setApprovalRequest(ApprovalRequest approvalRequest) {
        this.approvalRequest = approvalRequest;
    }


    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
}
