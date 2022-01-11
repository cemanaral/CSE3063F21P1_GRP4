import json

from domain_classes.course import *
from simulation_classes.subsystem import Subsystem


class CourseLoader(Subsystem):
    def __init__(self):
        self.__json_file_dict = {}
        self.__loaded_courses = []

    def start(self, json_file_name):
        self.__load_json_file(json_file_name)
        self.__load_courses_from_json()
        self.__load_prerequisites()

    def __load_json_file(self, json_file_name):
        with open(json_file_name, 'r') as file_in:
            data = json.load(file_in)
        self.__json_file_dict = data

    def __load_courses_from_json(self):
        for course_type, course_dicts in self.__json_file_dict['courses'].items():
            for course_dict in course_dicts:
                self.__load_course_from_dict(course_dict, eval(course_type))

    def __load_course_from_dict(self, course_dict: dict, course_type) -> Course:
        # since credits of
        # compulsory courses may vary
        # we take it from json
        if course_type == CompulsoryCourse:
            course = course_type(
                course_code=course_dict['courseCode'],
                name=course_dict['name'],
                semester=course_dict['semester'],
                credit=course_dict['credit']
            )
        else:
            course = course_type(
                course_code=course_dict['courseCode'],
                name=course_dict['name'],
                semester=course_dict['semester']
            )
        self.__loaded_courses.append(course)

    def __load_prerequisites(self):
        for course_code, prerequisite_codes in self.__json_file_dict['prerequisites'].items():
            course = self.__find_course_object_from_code(course_code)
            for prerequisite_code in prerequisite_codes:
                prerequisite = self.__find_course_object_from_code(prerequisite_code)
                course.add_prerequisite(prerequisite)

    def __find_course_object_from_code(self, course_code: str) -> Course:
        for course in self.loaded_data:
            if course.course_code == course_code:
                return course

    @property
    def loaded_data(self):
        return self.__loaded_courses
