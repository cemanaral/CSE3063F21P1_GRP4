package StudentRegistrationSystem;

import java.util.ArrayList;

public class Student extends Person {

    //Student's attributes
    private int studentNumber;
    private int semester;
    private Transcript transcript = new Transcript();
    private ApprovalRequest approvalRequest = new ApprovalRequest();

    //Student constructor. It inherits (name+surname) from Person class
    public Student(String name, String surname, int studentNumber, int semester) {
        super(name, surname);            // name + surname comes from Person

        this.studentNumber = studentNumber;
        this.semester = semester;
    }




    @Override
    public String toString() {
        return "Student{" +
                "studentNumber=" + studentNumber +
                ", semester=" + semester +
                ", transcript=" + transcript +
                ", approvalRequest=" + approvalRequest +
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
}
