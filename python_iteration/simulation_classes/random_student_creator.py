import json
import random

from domain_classes.person import Student
from simulation_classes.subsystem import Subsystem


class RandomStudentCreator(Subsystem):
    def __init__(self):
        self.__first_names = []
        self.__last_names = []
        self.__no_of_students = 0
        self.__created_students = []

    def __load_json_file(self, json_file_name):
        with open(json_file_name, 'r') as file_in:
            data = json.load(file_in)
        self.__first_names = data['person']['first_names']
        self.__last_names = data['person']['last_names']
        self.__no_of_students = data['person']['no_of_students']

    def start(self, json_file_name):
        self.__load_json_file(json_file_name)
        self.__create_students()

    def __create_students(self):
        for i in range(self.__no_of_students):
            self.__create_single_student()

    def __create_single_student(self):
        first_name = random.choice(self.__first_names)
        last_name = random.choice(self.__last_names)
        student_no = random.randint(150_115_000, 150_120_999)
        semester = random.randint(1, 8)

        self.__created_students.append(
            Student(first_name, last_name, student_no, semester)
        )

    @property
    def loaded_data(self):
        return self.__created_students
