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
 * This is the main driver class for our project.
 */
public class Main {
    // number of students to be created
    private final int noOfStudents = 100;
    private final int noOfAdvisors = noOfStudents / 10;

    // Used for random student and advisor creation
    private final String[] names = {"Ali","Veli","Halil","Can","Ayse","Mehmet",
            "Cem","Sena","Mert","Yusuf","Melisa","Zeynep","Hasan",
            "Berke","Yasin","Murat","Hasan","Mert","Ahmet","Tugba",
            "Gizem","Ozlem","Fatih","Ramiz","Ezel","Utku","Omer",
            "Eylem","Asli","Osman"};

    private final String[] surnames = {"Ozturk","Ganiz","Bayraktar","Yilmaz","Tas","Kerim",
            "Karaeski","Alemdar","Bas","Yeter","Dundar","Yildiz","Kaya",
            "Erden","Marmara","Ege","Karadeniz","Akdeniz","Dogan","Ulas"};

    private final String[] titles = {"Associate Professor","Doctor","Professor Doctor"
            ,"Assistant Professor","Assistant"};

    // Used for assigning random grades to transcripts
    private final String[] gradeList={"AA","BA","BB","CB","CC","DC","DD","FD","FF"};

    /**
     * This method parses Course objects
     * from intput.json
     *
     * Creates random students, assigns Advisors
     * adds parsed Course objects and
     * assigns randomized letter grades for them
     *
     * Enrolls students to current semesters courses
     * Runs checks in both RegistrationSystem and Advisor side
     *
     * Prints unaccepted ApprovalRequests to console
     *
     * And finally serializes every student object to output directory
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ArrayList<Student> studentList  = new ArrayList<>();
        ArrayList<Advisor> advisorList = new ArrayList<>();

        Random rand = new Random();
        RegistrationSystem registrationSystem = RegistrationSystem.getInstance();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Main main = new Main();

        Path filepath = Paths.get(
                System.getProperty("user.dir"), "iteration2", "src", "StudentRegistrationSystem", "input.json");
        System.out.println(filepath);
        JsonReader reader = new JsonReader(new FileReader(filepath.toString()));

// Loading input.json
// {"courseCode":"","name":"","credit":, "semester": },

        JsonObject inputJson = gson.fromJson(reader, JsonObject.class);
        System.out.println("semester to be simulated: " + inputJson.get("semester").getAsString());

// loading compulsoryCourses[]
        JsonElement compulsory_json = inputJson.get("courses").getAsJsonObject().get("CompulsoryCourse");
        CompulsoryCourse[] compulsoryCourses= gson.fromJson(compulsory_json, CompulsoryCourse[].class);
// to prevent null schedule and null prerequisites
        // Arrays.stream(compulsoryCourses).forEach((course) -> course.setSchedule(new Schedule()));
        Arrays.stream(compulsoryCourses).forEach((course) -> course.setPrerequisites(new ArrayList<>()));

// loading facultyElectives[]
        JsonElement faculty_json = inputJson.get("courses").getAsJsonObject().get("FacultyElective");
        FacultyElective[] facultyElectives= gson.fromJson(faculty_json, FacultyElective[].class);
// to prevent null schedule and null prerequisites
        // Arrays.stream(facultyElectives).forEach((course) -> course.setSchedule(new Schedule()));
        Arrays.stream(facultyElectives).forEach((course) -> course.setPrerequisites(new ArrayList<>()));

// loading technicalElectives[]
        JsonElement technical_json = inputJson.get("courses").getAsJsonObject().get("TechnicalElective");
        TechnicalElective[] technicalElectives= gson.fromJson(technical_json, TechnicalElective[].class);
// to prevent null schedule and null prerequisites
        // Arrays.stream(technicalElectives).forEach((course) -> course.setSchedule(new Schedule()));
        Arrays.stream(technicalElectives).forEach((course) -> course.setPrerequisites(new ArrayList<>()));

// loading nontechnicalElectives[]
        JsonElement nontechnical_json = inputJson.get("courses").getAsJsonObject().get("NonTechnicalElective");
        NonTechnicalElective[] nontechnicalElectives= gson.fromJson(nontechnical_json, NonTechnicalElective[].class);
// to prevent null schedule and null prerequisites
        // Arrays.stream(nontechnicalElectives).forEach((course) -> course.setSchedule(new Schedule()));
        Arrays.stream(nontechnicalElectives).forEach((course) -> course.setPrerequisites(new ArrayList<>()));


// loading prerequisites
        Set<HashMap.Entry<String, JsonElement>> json_prerequisites = inputJson.get("prerequisites").getAsJsonObject().entrySet();

        HashMap<String, String[]> prerequisiteTree = new HashMap<>();
        for (HashMap.Entry<String, JsonElement> pair: json_prerequisites) {
            String courseName = pair.getKey();
            String[] prerequisites= gson.fromJson(pair.getValue(), String[].class);
            prerequisiteTree.put(courseName, prerequisites);
        }


// adding prerequisites to compulsory courses
        for (String key : prerequisiteTree.keySet()) {
            Course course = main.findCourseByName(key, compulsoryCourses);
            for (String prerequisiteName : prerequisiteTree.get(key)) {
                Course prerequisiteCourse = main.findCourseByName(prerequisiteName, compulsoryCourses);
                course.addPrerequisite(prerequisiteCourse);
            }
        }

      /*  Arrays.stream(facultyElectives).forEach(System.out::println);
        Arrays.stream(technicalElectives).forEach(System.out::println);
        Arrays.stream(nontechnicalElectives).forEach(System.out::println);
        Arrays.stream(compulsoryCourses).forEach(System.out::println);*/


        int i;
        // creating advisors
        for (i=0; i < main.noOfAdvisors; i++) {
            String advisorName = main.names[(int)rand.nextInt(30)];
            String advisorSurname = main.surnames[(int)rand.nextInt(20)];
            advisorList.add(new Advisor(advisorName, advisorSurname));
        }


        //random student creating

        for (i=0; i < main.noOfStudents; i++) {
            String studentName = main.names[(int)rand.nextInt(30)];
            String StudentSurname = main.surnames[(int)rand.nextInt(20)];
            int StudentNumber = 150110000 + rand.nextInt(11000);
            int semester = 1+rand.nextInt(7);
            int j=rand.nextInt(9);
            Advisor currentAdvisor = advisorList.get(i % main.noOfAdvisors); // Each advisor assigned to 10 students
            Student currentStudent = new Student(studentName, StudentSurname,StudentNumber, semester, currentAdvisor);
            studentList.add(currentStudent);


// compulsory
            for (CompulsoryCourse course: compulsoryCourses){

                String grade = main.gradeList[rand.nextInt(9) ];
//adding taken courses
                if (currentStudent.getSemester()>course.getSemester()){
                    //  if (registrationSystem.checkSinglePrerequisiteSatisfied(currentStudent, course))
                    currentStudent.getTranscript().addCourseAndLetterGrade(course,grade);
                }
//adding current courses
                if (currentStudent.getSemester() == course.getSemester()){
                    currentStudent.getApprovalRequest().addCourse(course);
                }


            }
// technical
            for (TechnicalElective course: technicalElectives){
                String grade = main.gradeList[rand.nextInt(9) ];
                if (currentStudent.getSemester()>course.getSemester()){
                    //   if (registrationSystem.checkSinglePrerequisiteSatisfied(currentStudent, course))
                    currentStudent.getTranscript().addCourseAndLetterGrade(course,grade);
                }
                //adding current courses
                if (currentStudent.getSemester() == course.getSemester()){
                    currentStudent.getApprovalRequest().addCourse(course);
                }
            }
// fte
            for (FacultyElective course: facultyElectives){
                String grade = main.gradeList[rand.nextInt(9) ];
                if (currentStudent.getSemester()>course.getSemester()){
                    // if (registrationSystem.checkSinglePrerequisiteSatisfied(currentStudent, course))
                    currentStudent.getTranscript().addCourseAndLetterGrade(course,grade);
                }
                //adding current courses
                if (currentStudent.getSemester() == course.getSemester()){
                    currentStudent.getApprovalRequest().addCourse(course);
                }
            }
// nte
            for (NonTechnicalElective course: nontechnicalElectives){
                String grade = main.gradeList[rand.nextInt(9) ];
                if (currentStudent.getSemester()>course.getSemester()){
                    // if (registrationSystem.checkSinglePrerequisiteSatisfied(currentStudent, course))
                    currentStudent.getTranscript().addCourseAndLetterGrade(course,grade);
                }
                //adding current courses
                if (currentStudent.getSemester() == course.getSemester()){
                    currentStudent.getApprovalRequest().addCourse(course);
                }
            }
            if (registrationSystem.checkPrerequisitesSatisfied(currentStudent)
                    && !registrationSystem.checkCreditLimitExceeds(currentStudent)
                    && currentStudent.getAdvisor().checkFteInFall(currentStudent)
                    && currentStudent.getAdvisor().checkEngineeringProjectStatus(currentStudent)) {
                currentStudent.getApprovalRequest().setApproved(true);
            }
            else {
                currentStudent.getApprovalRequest().setApproved(false);

                System.out.println(
                        String.format("prereq satisfied %s creditlimitexceeds %s checkFteInFall %s checkEngineeringProjectStatus %s total credits %d studentno %d",
                                registrationSystem.checkPrerequisitesSatisfied(currentStudent),
                                !registrationSystem.checkCreditLimitExceeds(currentStudent),
                                currentStudent.getAdvisor().checkFteInFall(currentStudent),
                                currentStudent.getAdvisor().checkEngineeringProjectStatus(currentStudent),
                                currentStudent.getTranscript().getTotalCredits(),
                                currentStudent.getStudentNumber())
                );

            }

        }

        main.createOutputDirectory();
        // writing students to output folder as json objects
        for (Student std: studentList) {
            main.writeStudentToJson(std, gson);
        }
    }


    /**
     * Creates output folder.
     * Deletes output json files from past executions
     *
     * @throws IOException
     */
    private void createOutputDirectory() throws IOException {
        Path folderPath = Paths.get(
                System.getProperty("user.dir"), "iteration2", "src", "StudentRegistrationSystem", "output"
        );
        File theDir = new File(folderPath.toString());
        if (theDir.exists()) {
            FileUtils.deleteDirectory(theDir);
        }
        theDir.mkdir();
    }

    /**
     * Outputs a single Student object to json file
     * where filename is Student's id
     *
     * @param std student to be serialized
     * @param gson gson driver used in serialization
     * @throws IOException
     */
    private void writeStudentToJson(Student std, Gson gson) throws IOException {
        // System.out.println(gson.toJson(student1));
        String filename = std.getStudentNumber() + ".json";

        Path path = Paths.get(
                System.getProperty("user.dir"), "iteration2", "src", "StudentRegistrationSystem", "output", filename
        );

        File file = new File(path.toString());
        file.createNewFile();
        // System.out.println(path.toString());
        OutputStreamWriter output_stream = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
        output_stream.write(StringEscapeUtils.unescapeJava(gson.toJson(std)));
        output_stream.close();
    }

    /**
     * Finds course object from an array
     * from courseName string
     *
     * @param courseName Course object's name to be found
     * @param courses Where the search is made
     * @return Course object
     *
     * @throws RuntimeException if course is not found in courses parameter
     */
    private Course findCourseByName(String courseName, Course[] courses) {
        for (Course course: courses) {
            if (course.getCourseCode().equals(courseName) ){
                return course;
            }
        }
        throw new RuntimeException("Could not find " + courseName + " in compulsoryCourses[]!!!");
    }



}
