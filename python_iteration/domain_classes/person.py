from abc import ABC as AbstractBaseClass
from .transcript import Transcript
from .approval_request import ApprovalRequest

class Person(AbstractBaseClass):
    def __init__(self, first_name, last_name):
        self.__first_name = first_name
        self.__last_name = last_name

    def __repr__(self) -> str:
        return self.__str__()

    @property
    def first_name(self):
        return self.__first_name

    @property
    def last_name(self):
        return self.__last_name


class Student(Person):
    def __init__(self, first_name, last_name, student_no, semester, advisor=None) -> None:
        super().__init__(first_name, last_name)
        self.__student_no = student_no
        self.__semester = semester
        self.__advisor = advisor
        self.__transcript = Transcript()
        self.__approval_request = ApprovalRequest()

    def __str__(self) -> str:
        return ' '.join([
            type(self).__name__,
            self.first_name,
            self.last_name,
            str(self.__student_no),
            'semester:', str(self.__semester),
            'advisor:', str(self.__advisor),
            'transcript:', str(self.__transcript),
            'approval request:', str(self.__approval_request)
        ])

    @property
    def advisor(self):
        return self.__advisor

    @advisor.setter
    def advisor(self, advisor):
        self.__advisor = advisor

    # from information expert
    def add_course(self, course):
        if self.__semester > course.semester:
            self.__transcript.add_past_course(course)
        elif self.__semester == course.semester:
            self.__approval_request.add_current_course(course)


class Advisor(Person):
    def __init__(self, first_name, last_name):
        super().__init__(first_name, last_name)

    def __str__(self) -> str:
        return ' '.join([
            type(self).__name__,
            self.first_name,
            self.last_name,
        ])
