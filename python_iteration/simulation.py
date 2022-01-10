from abc import ABC as AbstractBaseClass, abstractmethod
from domain_classes.course import *
import json


class Simulation:
    def __init__(self, **kwargs):
        self.__subsystems = kwargs
        self.__students = []
        self.__advisors = []
        self.__courses = []

    def start_simulation(self):
        self.__start_subsystems()
        self.__load_data_from_subsystems()
        print(self.__courses)

    def __start_subsystems(self):
        for subsystem in self.__subsystems.values():
            subsystem.start()

    def __load_data_from_subsystems(self):
        self.__courses = self.__subsystems['course_loader'].loaded_courses


class Subsystem(AbstractBaseClass):
    @abstractmethod
    def start(self):
        raise NotImplementedError()


class RandomStudentCreator(Subsystem):
    def start(self):
        print("RandomStudentCreator is executing")


class RandomAdvisorCreator(Subsystem):
    def start(self):
        print("RandomAdvisorCreator is executing")


class CourseLoader(Subsystem):
    def __init__(self, json_file_name: str):
        self.__json_file_name = json_file_name
        self.__json_file_dict = {}
        self.__loaded_courses = []

    def start(self):
        print("CourseLoader is executing")
        self.__load_json_file()
        self.__load_courses_from_json()
        self.__load_prerequisites()

    def __load_json_file(self):
        with open(self.__json_file_name, 'r') as file_in:
            data = json.load(file_in)
        self.__json_file_dict = data

    def __load_courses_from_json(self):    
        for course_type, course_dicts in self.__json_file_dict['courses'].items():
            for course_dict in course_dicts:
                self.__load_course_from_dict(course_dict, eval(course_type))



    def __load_course_from_dict(self, course_dict: dict, course_type: Course) -> Course:
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
        for course in self.loaded_courses:
            if course.course_code == course_code:
                return course

    @property
    def loaded_courses(self):
        return self.__loaded_courses
