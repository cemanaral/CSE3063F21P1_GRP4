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

public class RegistrationSystem {

    public void printSchedule(Schedule schedule) {
        int hour = 9;
        System.out.println("\n        Mon Tue Wed Thu Fri");
        for (int i = 0; i < 10; i++) {
            if (hour == 9) System.out.print(' ');
            System.out.print(hour++ + ":00   ");
            for (int j = 0; j < 5; j++) {
                System.out.print(schedule.getMatrix()[i][j] + "   ");
            }
            System.out.print('\n');
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Student> studentList  = new ArrayList<>();

        String[] Names={"Ali","Veli","Halil","Can","Ayse","Mehmet",
                "Cem","Sena","Mert","Yusuf","Melisa","Zeynep","Hasan",
                "Berke","Yasin","Murat","Hasan","Mert","Ahmet","Tugba",
                "Gizem","Ozlem","Fatih","Ramiz","Ezel","Utku","Omer",
                "Eylem","Asli","Osman"};
        String[] Surnames={"Ozturk","Ganiz","Bayraktar","Yilmaz","Tas","Kerim",
                "Karaeski","Alemdar","Bas","Yeter","Dundar","Yildiz","Kaya",
                "Erden","Marmara","Ege","Karadeniz","Akdeniz","Dogan","Ulas"};



        String titles[]= {"Associate Professor","Doctor","Professor Doctor"
                ,"Assistant Professor","Assistant"};

        Random rand = new Random();



        RegistrationSystem registrationSystem = new RegistrationSystem();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Advisor advisor = new Advisor("Fatma", "Corut Ergin");

        Path filepath = Paths.get(
                System.getProperty("user.dir"), "iteration1", "src", "StudentRegistrationSystem", "input.json");
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
            CompulsoryCourse course = registrationSystem.findCourseByName(key, compulsoryCourses);
            for (String prerequisiteName : prerequisiteTree.get(key)) {
                CompulsoryCourse prerequisiteCourse = registrationSystem.findCourseByName(prerequisiteName, compulsoryCourses);
                course.addPrerequisite(prerequisiteCourse);
            }
        }

      /*  Arrays.stream(facultyElectives).forEach(System.out::println);
        Arrays.stream(technicalElectives).forEach(System.out::println);
        Arrays.stream(nontechnicalElectives).forEach(System.out::println);
        Arrays.stream(compulsoryCourses).forEach(System.out::println);*/

        String[] gradeList={"AA","BA","BB","CB","CC","DC","DD","FD","FF"};


        int i =0;
        //random student creating
        for (i=0; i<30; i++) {
            String studentName = Names[(int)rand.nextInt(30)];
            String StudentSurname = Surnames[(int)rand.nextInt(20)];
            int StudentNumber = 150110000 + rand.nextInt(11000);
            int semester = 1+rand.nextInt(7);
            int j=rand.nextInt(9);


            Student student1 = new Student(studentName, StudentSurname,StudentNumber, semester);
            studentList.add(student1);


// compulsory
            for (CompulsoryCourse course: compulsoryCourses){

               String grade = gradeList[rand.nextInt(9) ];
//adding taken courses
                if (student1.getSemester()>course.getSemester()){
                  //  if (registrationSystem.checkSinglePrerequisiteSatisfied(student1, course))
                        student1.getTranscript().addCourseAndLetterGrade(course,grade);
                }
//adding current courses
                if (student1.getSemester() == course.getSemester()){
                    student1.getApprovalRequest().addCourse(course);
                }


            }
// technical
            for (TechnicalElective course: technicalElectives){
                String grade = gradeList[rand.nextInt(9) ];
                if (student1.getSemester()>course.getSemester()){
                 //   if (registrationSystem.checkSinglePrerequisiteSatisfied(student1, course))
                        student1.getTranscript().addCourseAndLetterGrade(course,grade);
                }
                //adding current courses
                if (student1.getSemester() == course.getSemester()){
                    student1.getApprovalRequest().addCourse(course);
                }
            }
// fte
            for (FacultyElective course: facultyElectives){
                String grade = gradeList[rand.nextInt(9) ];
                if (student1.getSemester()>course.getSemester()){
                   // if (registrationSystem.checkSinglePrerequisiteSatisfied(student1, course))
                        student1.getTranscript().addCourseAndLetterGrade(course,grade);
                }
                //adding current courses
                if (student1.getSemester() == course.getSemester()){
                    student1.getApprovalRequest().addCourse(course);
                }
            }
// nte
            for (NonTechnicalElective course: nontechnicalElectives){
                String grade = gradeList[rand.nextInt(9) ];
                if (student1.getSemester()>course.getSemester()){
                   // if (registrationSystem.checkSinglePrerequisiteSatisfied(student1, course))
                        student1.getTranscript().addCourseAndLetterGrade(course,grade);
                }
                //adding current courses
                if (student1.getSemester() == course.getSemester()){
                    student1.getApprovalRequest().addCourse(course);
                }
            }
            if (registrationSystem.checkPrerequisitesSatisfied(student1)
                && !registrationSystem.checkCreditLimitExceeds(student1)
                && advisor.checkFteInFall(student1)
                && advisor.checkEngineeringProjectStatus(student1)) {
                student1.getApprovalRequest().setApproved(true);
            }
            else {
                student1.getApprovalRequest().setApproved(false);

                System.out.println(
                        String.format("prereq satisfied %s creditlimitexceeds %s checkFteInFall %s checkEngineeringProjectStatus %s total credits %d studentno %d",
                                registrationSystem.checkPrerequisitesSatisfied(student1),
                                !registrationSystem.checkCreditLimitExceeds(student1),
                                advisor.checkFteInFall(student1),
                                advisor.checkEngineeringProjectStatus(student1),
                                student1.getTranscript().getTotalCredits(),
                                student1.getStudentNumber())
                );


            }




            // System.out.println(student1.toString());
        }

        Path folderPath = Paths.get(
                System.getProperty("user.dir"), "iteration1", "src", "StudentRegistrationSystem", "output"
        );
        File theDir = new File(folderPath.toString());
        if (theDir.exists()) {
            FileUtils.deleteDirectory(theDir);
        }
        theDir.mkdir();


        // writing students to output folder as json objects
        for (Student std: studentList) {
            // System.out.println(gson.toJson(student1));
            String filename = std.getStudentNumber() + ".json";

            Path path = Paths.get(
                    System.getProperty("user.dir"), "iteration1", "src", "StudentRegistrationSystem", "output", filename
            );

            File file = new File(path.toString());
            file.createNewFile();
            // System.out.println(path.toString());
            OutputStreamWriter output_stream = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            output_stream.write(StringEscapeUtils.unescapeJava(gson.toJson(std)));
            output_stream.close();

        }





    }
    // checks for every course
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
    // checks for single course
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
    public CompulsoryCourse findCourseByName(String courseName,CompulsoryCourse[] courses) {
        for (CompulsoryCourse course: courses) {
            if (course.getCourseCode().equals(courseName) ){
                return course;
            }
        }
        throw new RuntimeException("Could not find " + courseName + " in compulsoryCourses[]!!!");
    }


}
