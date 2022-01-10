class Student:
    def __init__(self, first_name, last_name, student_no, semester, advisor=None) -> None:
        self.__first_name = first_name
        self.__last_name = last_name
        self.__student_no = student_no
        self.__semester = semester
        self.__advisor = advisor


    def __str__(self) -> str:
        return ' '.join([
            type(self).__name__,
            self.__first_name,
            self.__last_name,
            str(self.__student_no),
            'semester:', str(self.__semester),
            'advisor:', str(self.__advisor)
        ])
    
    def __repr__(self) -> str:
        return self.__str__()