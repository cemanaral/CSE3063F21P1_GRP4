import itertools
import json


class Simulation:
    def __init__(self, json_file_name, **kwargs):
        self.__json_file_name = json_file_name
        self.__subsystems = kwargs
        self.__students = []
        self.__advisors = []
        self.__courses = []
        self.__semester = ""

        self.__count_not_approved_due_credit_limit = 0
        self.__count_not_approved_due_prerequisites = 0
        self.__count_not_approved_due_fte_in_fall = 0

    def __load_semester_info_from_json(self):
        with open(self.__json_file_name, 'r') as file_in:
            data = json.load(file_in)
        self.__semester = data['semester']

    def start_simulation(self):
        self.__load_semester_info_from_json()
        self.__start_subsystems()
        self.__load_data_from_subsystems()
        self.__assign_advisors()
        self.__add_courses_to_students()
        self.__run_checks()

        # for c in self.__courses: print(c)
        for s in self.__students: print(s)
        # for a in self.__advisors: print(a)

    def __start_subsystems(self):
        for subsystem in self.__subsystems.values():
            subsystem.start(self.__json_file_name)

    def __load_data_from_subsystems(self):
        self.__courses = self.__subsystems['course_loader'].loaded_data
        self.__students = self.__subsystems['student_creator'].loaded_data
        self.__advisors = self.__subsystems['advisor_creator'].loaded_data

    def __assign_advisors(self):
        no_of_advisors = len(self.__advisors)
        for i, student in enumerate(self.__students):
            student.advisor = self.__advisors[i % no_of_advisors]

    def __add_courses_to_students(self):
        for student, course in itertools.product(self.__students, self.__courses):
            student.add_course(course)

    def __run_checks(self):
        self.__run_credit_limit_check()
        self.__run_prerequisite_check()
        if self.__semester == 'fall':
            self.__run_fte_in_fall_check()
        # self.__run_engineering_project_status_check()
        # self.__run_fall_two_te_check()

    def __run_credit_limit_check(self):
        for student in self.__students:
            approval_request = student.approval_request
            passed_check = approval_request.check_credit_limit()
            if not passed_check:
                approval_request.is_approved = False
                self.__count_not_approved_due_credit_limit += 1

    def __run_prerequisite_check(self):
        for student in self.__students:
            transcript = student.transcript
            approval_request = student.approval_request
            for course in approval_request.current_courses:
                passed_check = transcript.check_prerequisite_satisfied(course)
                if not passed_check:
                    approval_request.is_approved = False
                    self.__count_not_approved_due_prerequisites += 1

    def __run_fte_in_fall_check(self):
        for student in self.__students:
            approval_request = student.approval_request
            passed_check = approval_request.check_fte_in_fall(student)
            if not passed_check:
                approval_request.is_approved = False
                self.__count_not_approved_due_fte_in_fall += 1

    def __run_engineering_project_status_check(self):
        pass

    def __run_fall_two_te_check(self):
        pass
