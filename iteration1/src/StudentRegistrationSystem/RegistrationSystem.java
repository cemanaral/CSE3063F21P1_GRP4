package StudentRegistrationSystem;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Student> students  = new ArrayList<>();
        ArrayList <Course> coursesList = new ArrayList<>();
        //Databases
        String[] Names={"Ali","Veli","Halil","Can","Ayse","Mehmet",      			   //30
                "Cem","Sena","Mert","Yusuf","Melisa","Zeynep","Hasan",
                "Berke","Yasin","Murat","Hasan","Mert","Ahmet","Tugba",
                "Gizem","Ozlem","Fatih","Ramiz","Ezel","Utku","Omer",
                "Eylem","Asl�","Osman"};    
        String[] Surnames={"Ozturk","Ganiz","Bayraktar","Yilmaz","Tas","Kerim",    	 //20
                "Karaeski","Alemdar","Bas","Yeter","Dundar","Yildiz","Kaya",
                "Erden","Marmara","Ege","Karadeniz","Akdeniz","Dogan","Ulas"};

        String coursesCodes[]={"CSE3063","CSE2025","CSE2023","CSE2138",                 			 //10
                "MATH2059","ISG121","ISG122","MATH2056","CSE2003","CSE2004",			//40
                "CSE3061","CSE3072","CSE4081","CSE4082","ECON2003","ECON2004",
                "CSE2003","CSE2004","CSE3061","CSE3072","CSE4081","CSE4082",
                "CSE3067","CSE3076","CSE4089","CSE4090","CSE3003","CSE3004",
                "CSE3050","CSE3055","CSE4040","CSE4022","CSE2162","CSE2182",
                "CSE3129","CSE3127","CSE4124","CSE4250","CSE2223","CSE2250",};

        String titles[]= {"Associate Professor","Doctor","Professor Doctor"
                ,"Assistant Professor","Assistant"};


        Random rand = new Random();
        //random student creating
        for (int i=0; i<70; i++) {
            String studentName = Names[(int)rand.nextInt(30)];
            String StudentSurname = Surnames[(int)rand.nextInt(20)];
            int StudentNumber = 1000000 + rand.nextInt(900000);
            int semester = 1+rand.nextInt(7);
            

            Student student = new Student(studentName, StudentSurname,StudentNumber, semester);
            students.add(student);
        }


        // create random courses
        for (String code : coursesCodes) {
            coursesList.add(new NonTechnicalElective(code, "example name",1+rand.nextInt(7), 1+rand.nextInt(7)));
        }
        System.out.println(Arrays.toString(coursesList.toArray()));
        System.out.println(Arrays.toString(students.toArray()));

        // ***testing printSchedule***
        System.out.println("***testing printSchedule***");
        Schedule schedule = new Schedule();
        schedule.addLectureHour(0, 0);
        schedule.addLectureHour(1, 0);
        schedule.addLectureHour(2, 0);
        coursesList.get(0).getSchedule().addLectureHour(schedule);

        RegistrationSystem registrationSystem = new RegistrationSystem();
        registrationSystem.printSchedule(coursesList.get(0).getSchedule());

        System.out.println("***testing printSchedule***");
        // ***testing printSchedule***

        // ***gson test***
        System.out.println("***gson test***");
        Course c = new CompulsoryCourse("CSE3063", "Object Oriented Software Design", 6, 5);
        Gson gson = new Gson();
        System.out.println(gson.toJson(c));
        System.out.println("***gson test***");
        // ***gson test***

        // **transcript test***
        System.out.println("**transcript test***");
        Student s1 = students.get(0);
        s1.getTranscript().addCourseAndLetterGrade(coursesList.get(0), "AA");
        s1.getTranscript().addCourseAndLetterGrade(coursesList.get(1), "BB");
        s1.getTranscript().addCourseAndLetterGrade(coursesList.get(5), "BA");
        s1.getTranscript().addCourseAndLetterGrade(coursesList.get(2), "DC");
        s1.getTranscript().addCourseAndLetterGrade(coursesList.get(3), "FF");
        System.out.println("gpa: " + s1.getTranscript().getGpa());
        System.out.println("**transcript test***");
        // **transcript test***

        // **testing cem***

        Course systems_programming = new CompulsoryCourse("CSE2138", "Systems Programming", 7, 4);
        Course digital_design = new CompulsoryCourse("CSE3215", "Digital Logic Design", 6, 5);
        Course computer_organization = new CompulsoryCourse("CSE3038", "Computer Organization", 7, 6);
        Course modelling = new CompulsoryCourse("IE3081", "Modelling and Discrete Simulation", 4, 5);

        computer_organization.addPrerequisite(digital_design);
        computer_organization.addPrerequisite(systems_programming);

        Schedule schedule_computer_organization = new Schedule();
        schedule_computer_organization.addLectureHour(0, 0);
        schedule_computer_organization.addLectureHour(1, 0);
        computer_organization.setSchedule(schedule_computer_organization);

        Schedule schedule_digital_design = new Schedule();
        schedule_digital_design.addLectureHour(0, 0);
        schedule_digital_design.addLectureHour(3, 0);
        digital_design.setSchedule(schedule_digital_design);

        Schedule schedule_systems_programming = new Schedule();
        schedule_systems_programming.addLectureHour(0,1);
        schedule_systems_programming.addLectureHour(1,1);
        systems_programming.setSchedule(schedule_systems_programming);

        Schedule schedule_modelling = new Schedule();
        schedule_modelling.addLectureHour(4,0);
        schedule_modelling.addLectureHour(5,0);
        modelling.setSchedule(schedule_modelling);

        Student cem = new Student("Cem", "Anaral", 150119761, 5);
        cem.getTranscript().addCourseAndLetterGrade(systems_programming, "AA");
        cem.getTranscript().addCourseAndLetterGrade(digital_design, "BA");

        cem.getApprovalRequest().addCourse(computer_organization);
        cem.getApprovalRequest().addCourse(modelling);

        System.out.println("does cem satisfy prerequisites? " + registrationSystem.checkPrerequisitesSatisfied(cem));
        System.out.println("does cem exceed credit limit? " + registrationSystem.checkCreditLimitExceeds(cem));
        System.out.println("does cem have collision in his schedule? " + registrationSystem.checkHourCollision(cem));
        registrationSystem.printSchedule(cem.getApprovalRequest().getSchedule());

        Advisor advisor = new Advisor("Fatma", "Corut Ergin");
        System.out.println("can cem select graduation project? " + advisor.checkEngineeringProjectStatus(cem));
        System.out.println("does cem obey FTE rule? " + advisor.checkFteInFall(cem));
        System.out.println("cem's gpa: " + cem.getTranscript().getGpa());

        // ***testing cem***

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
        Arrays.stream(compulsoryCourses).forEach((course) -> course.setSchedule(new Schedule()));
        Arrays.stream(compulsoryCourses).forEach((course) -> course.setPrerequisites(new ArrayList<>()));

        // loading facultyElectives[]
        JsonElement faculty_json = inputJson.get("courses").getAsJsonObject().get("FacultyElective");
        FacultyElective[] facultyElectives= gson.fromJson(faculty_json, FacultyElective[].class);
        // to prevent null schedule and null prerequisites
        Arrays.stream(facultyElectives).forEach((course) -> course.setSchedule(new Schedule()));
        Arrays.stream(facultyElectives).forEach((course) -> course.setPrerequisites(new ArrayList<>()));


        // loading technicalElectives[]
        JsonElement technical_json = inputJson.get("courses").getAsJsonObject().get("TechnicalElective");
        TechnicalElective[] technicalElectives= gson.fromJson(technical_json, TechnicalElective[].class);
        // to prevent null schedule and null prerequisites
        Arrays.stream(technicalElectives).forEach((course) -> course.setSchedule(new Schedule()));
        Arrays.stream(technicalElectives).forEach((course) -> course.setPrerequisites(new ArrayList<>()));


        // loading nontechnicalElectives[]
        JsonElement nontechnical_json = inputJson.get("courses").getAsJsonObject().get("NonTechnicalElective");
        NonTechnicalElective[] nontechnicalElectives= gson.fromJson(nontechnical_json, NonTechnicalElective[].class);
        // to prevent null schedule and null prerequisites
        Arrays.stream(nontechnicalElectives).forEach((course) -> course.setSchedule(new Schedule()));
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

        Arrays.stream(facultyElectives).forEach(System.out::println);
        Arrays.stream(technicalElectives).forEach(System.out::println);
        Arrays.stream(nontechnicalElectives).forEach(System.out::println);
        Arrays.stream(compulsoryCourses).forEach(System.out::println);


    }

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

        //  all prerequisites are satisfied
        return true;
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

    public CompulsoryCourse findCourseByName(String courseName,CompulsoryCourse[] courses) {
        for (CompulsoryCourse course: courses) {
            if (course.getCourseCode().equals(courseName) ){
                return course;
            }
        }
        throw new RuntimeException("Could not find " + courseName + " in compulsoryCourses[]!!!");
    }


}
