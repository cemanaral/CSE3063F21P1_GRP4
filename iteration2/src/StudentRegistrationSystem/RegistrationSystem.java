package StudentRegistrationSystem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/**
 * This class is used for RegistrationSystem checks
 * which consists checking of prerequisites and credit limit
 *
 * Also this is a Singleton
 * @see {@https://refactoring.guru/design-patterns/singleton/java/example}
 */
public class RegistrationSystem {

    private static RegistrationSystem instance = null;

    private RegistrationSystem() {

    }

    /**
     * This method returns the
     * only instance of RegistrationSystem
     *
     * @return the only single instance of RegistrationSystem
     */
    public static RegistrationSystem getInstance() {
        if (instance == null)
            instance = new RegistrationSystem();

        return instance;
    }

    /**
     * This method checks if a student is eligible to take
     * the courses in ApprovalRequest in terms of prerequisites
     * @param student
     * @return is prerequisite satisfied?
     */
    public boolean checkPrerequisitesSatisfied(Student student) {
        HashMap<Course, String> coursesTaken = student.getTranscript().getCoursesTaken();

        for (Course course : student.getApprovalRequest().getCourses()) {
            for (Course prerequisite : course.getPrerequisites()) {
                // if prerequisite not in transcript or grade is FF or FD
                if (!coursesTaken.containsKey(prerequisite)
                        || List.of("FF","FD").contains(coursesTaken.get(prerequisite))) {
                    // prerequisite not satisfied
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * This method checks whether a <em>single</em>
     * prerequisite was satisfied
     * @see RegistrationSystem#checkPrerequisitesSatisfied

     * @param student
     * @param prerequisite
     *
     * @return a boolean that indicates a <em>single</em>
     * prerequisite is satisfied or not
     */
    public boolean checkSinglePrerequisiteSatisfied(Student student, Course prerequisite) {
        HashMap<Course, String> coursesTaken = student.getTranscript().getCoursesTaken();
        // if course is in students transcript and not FF or FD
        // return true
        if (coursesTaken.containsKey(prerequisite)
                && List.of("FF","FD").contains(coursesTaken.get(prerequisite))) {
            return true;
        }
        return false;
    }

    /**
     * This method returns a boolean that indicates
     * whether the sum of credits of given student
     * is above the limit (40) or not
     *
     * @param student
     * @return is credit limit exceeded?
     */

    public boolean checkCreditLimitExceeds(Student student) {
        // a student can not take more than 40 credits

        int sum = 0;

        for (Course course : student.getApprovalRequest().getCourses()) {
            sum += course.getCredit();
        }

        if (sum > 40) {
            // limit exceeded
            return true;
        }

        // limit not exceeded
        return false;

    }
/*
    public boolean checkHourCollision(Student student) {
        int[][] studentMatrix = student.getApprovalRequest().getSchedule().getMatrix();
        for (int i = 0; i < studentMatrix.length ; i++) {
            for (int j = 0; j < studentMatrix[i].length; j++) {
                // if there is collision
                if (studentMatrix[i][j] > 1) {
                    return true;
                }
            }
        }
        // no problem occurs
        return false;
    }
*/


}
