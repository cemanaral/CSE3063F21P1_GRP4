from abc import ABC as AbstractBaseClass
from os import name


class Course(AbstractBaseClass):
    def __init__(self, course_code: str, name: str, semester: int, credit: int):
        self.__course_code = course_code
        self.__name = name
        self.__credit = credit
        self.__semester = semester
        self.__prerequisites = []

    def add_prerequisite(self, course):
        self.__prerequisites.append(course)

    @property
    def semester(self):
        return self.__semester

    @property
    def course_code(self):
        return self.__course_code

    @property
    def credit(self):
        return self.__credit

    @property
    def prerequisites(self):
        return self.__prerequisites

    def __str__(self) -> str:
        return ' '.join([
            # type(self).__name__,
            self.__course_code,
            self.__name,
            # 'semester:', str(self.__semester),
            # 'credit:', str(self.__credit),
            # 'prerequisites:', str(self.__prerequisites)
        ])

    def __repr__(self) -> str:
        return self.__str__()


class CompulsoryCourse(Course):
    def __init__(self, course_code, name, semester, credit):
        super().__init__(course_code, name, semester, credit)


class FacultyElective(Course):
    def __init__(self, course_code, name, semester):
        super().__init__(course_code, name, semester, 5)


class TechnicalElective(Course):
    def __init__(self, course_code, name, semester):
        super().__init__(course_code, name, semester, 5)


class NonTechnicalElective(Course):
    def __init__(self, course_code, name, semester):
        super().__init__(course_code, name, semester, 3)
