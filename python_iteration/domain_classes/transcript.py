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

    def check_prerequisite_satisfied(self, course):
        prerequisites = course.prerequisites
        for prereq in prerequisites:
            in_transcript = prereq in self.__past_courses
            passed_grade = self.__past_courses.get(prereq)
            if not in_transcript or passed_grade in ('FD', 'FF'):
                return False  # should not be approved
        return True  # no problem

    @property
    def completed_credits(self):
        total_completed_credits = 0
        for course, grade in self.__past_courses.items():
            if grade not in ('FF', 'FD'):
                total_completed_credits += course.credit
        return total_completed_credits

    @property
    def past_courses(self):
        return self.__past_courses



    def __str__(self):
        return ' '.join([
            'completed credits:', str(self.completed_credits),
            'past courses:', str(self.__past_courses),
        ])

    def __repr__(self):
        return self.__str__()
