import itertools
import json
import logging
import os
import shutil


class RegistrationSystem:
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
        self.__count_not_approved_due_engineering_project_status = 0
        self.__count_not_approved_due_fall_two_te = 0
        self.__output_json_path = ""

    def __load_semester_info_from_json(self):
        with open(self.__json_file_name, 'r') as file_in:
            data = json.load(file_in)
        self.__semester = data['semester']

    def __setup_logger(self):
        if os.path.exists('output.log'):
            os.remove('output.log')
        logging.basicConfig(filename='output.log', level=logging.INFO)

    def __setup_json_folder(self):
        self.__output_json_path = os.path.join(os.getcwd(), 'output_jsons')
        if os.path.exists(self.__output_json_path):
            shutil.rmtree(self.__output_json_path)
        os.makedirs(self.__output_json_path)

    def __write_students_to_json(self):
        self.__setup_json_folder()
        for student in self.__students:
            student_json = self.__convert_student_to_json(student)
            file_path = os.path.join(self.__output_json_path, '{}.json'.format(str(student.student_no)))
            with open(file_path, 'w') as file:
                file.write(student_json)

    def __convert_student_to_json(self, student):
        student_dict = {
            'student_no': student.student_no,
            'first_name': student.first_name,
            'last_name': student.last_name,
            'semester': student.semester,
            'advisor': str(student.advisor.first_name + ' ' + student.advisor.last_name),
            'completed_credits': student.transcript.completed_credits,
            'transcript': [{course.course_code: grade}  for course, grade in student.transcript.past_courses.items()],
            'is_approved': student.approval_request.is_approved,
            'added_courses': [course.course_code for course in student.approval_request.current_courses]
        }
        return json.dumps(student_dict, indent=4)

    def __write_statistics_to_log(self):
        logging.info(
            f"number of students not approved due to credit limit: {self.__count_not_approved_due_credit_limit}")
        logging.info(
            f"number of students not approved due to unsatisfied prerequisites: {self.__count_not_approved_due_prerequisites}")
        logging.info(
            f"number of students not approved due to having fte in fall: {self.__count_not_approved_due_fte_in_fall}")
        logging.info(
            f"number of students not approved due to not being eligible to take graduation project: {self.__count_not_approved_due_engineering_project_status}")
        logging.info(
            f"number of students not approved due to having more than two TE in fall: {self.__count_not_approved_due_fall_two_te}")

    def start_simulation(self):
        self.__setup_logger()
        self.__load_semester_info_from_json()
        self.__start_subsystems()
        self.__load_data_from_subsystems()
        self.__assign_advisors()
        self.__add_courses_to_students()
        self.__run_checks()
        self.__write_students_to_json()
        self.__write_statistics_to_log()

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
        self.__run_engineering_project_status_check()
        if self.__semester == 'fall':
            self.__run_fte_in_fall_check()
            self.__run_fall_two_te_check()

    def __run_credit_limit_check(self):
        for student in self.__students:
            approval_request = student.approval_request
            passed_check = approval_request.check_credit_limit()
            if not passed_check:
                approval_request.is_approved = False
                self.__count_not_approved_due_credit_limit += 1
                logging.info(f"{student.student_no} not approved because of exceeding credit limit (>40)")

    def __run_prerequisite_check(self):
        for student in self.__students:
            transcript = student.transcript
            approval_request = student.approval_request
            for course in approval_request.current_courses:
                passed_check = transcript.check_prerequisite_satisfied(course)
                if not passed_check:
                    approval_request.is_approved = False
                    self.__count_not_approved_due_prerequisites += 1
                    logging.info(f"{student.student_no} not approved because of unsatisfied prerequisites.")

    def __run_fte_in_fall_check(self):
        for student in self.__students:
            approval_request = student.approval_request
            passed_check = approval_request.check_fte_in_fall(student)
            if not passed_check:
                approval_request.is_approved = False
                self.__count_not_approved_due_fte_in_fall += 1
                logging.info(f"{student.student_no} not approved because of having FTE in fall.")

    def __run_engineering_project_status_check(self):
        for student in self.__students:
            passed_check = student.check_engineering_project_status()
            if not passed_check:
                approval_request = student.approval_request
                approval_request.is_approved = False
                self.__count_not_approved_due_engineering_project_status += 1
                logging.info(f"{student.student_no} not approved because not eligible to take engineering project ("
                             f"completed credits < 165 or not in semester 7-8)")

    def __run_fall_two_te_check(self):
        for student in self.__students:
            approval_request = student.approval_request
            passed_check = approval_request.check_two_te_in_fall(student)
            if not passed_check:
                approval_request.is_approved = False
                self.__count_not_approved_due_fall_two_te += 1
                logging.info(f"{student.student_no} not approved because having more than 2 TE in fall.")
