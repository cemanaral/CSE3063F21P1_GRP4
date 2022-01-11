import random


class Transcript:
    def __init__(self):
        self.__grades = {
            "AA": 4.00,
            "BA": 3.50,
            "BB": 3.00,
            "CB": 2.50,
            "CC": 2.00,
            "DC": 1.50,
            "DD": 1.00,
            "FD": 0.50,
            "FF": 0.00
        }
        self.__past_courses = {}

    def add_past_course(self, course):
        grade = random.choice(list(self.__grades.keys()))
        self.__past_courses[course] = grade

    def __str__(self):
        return ' '.join([
            type(self).__name__,
            'past courses:', str(self.__past_courses)
        ])

    def __repr__(self):
        return self.__str__()