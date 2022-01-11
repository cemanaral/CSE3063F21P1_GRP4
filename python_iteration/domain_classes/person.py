from abc import ABC as AbstractBaseClass


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

    def __str__(self) -> str:
        return ' '.join([
            type(self).__name__,
            self.first_name,
            self.last_name,
            str(self.__student_no),
            'semester:', str(self.__semester),
            'advisor:', str(self.__advisor)
        ])


class Advisor(Person):
    def __init__(self, first_name, last_name):
        super().__init__(first_name, last_name)

    def __str__(self) -> str:
        return ' '.join([
            type(self).__name__,
            self.first_name,
            self.last_name,
        ])
