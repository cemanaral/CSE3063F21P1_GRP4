from .course import FacultyElective, TechnicalElective


class ApprovalRequest:
    def __init__(self):
        self.__current_courses = []
        self.__is_approved = True

    def add_current_course(self, course):
        self.__current_courses.append(course)

    # from information expert
    def check_credit_limit(self):
        if self.total_credits() > 40:
            return False  # should not be approved
        return True  # no problem

    # from information expert
    def check_fte_in_fall(self, student):
        student_semester = student.semester
        if student_semester == 7:
            for course in self.__current_courses:
                if isinstance(course, FacultyElective):
                    return False  # should not be approved
        return True  # no problem

    # from information expert
    def check_two_te_in_fall(self, student):
        no_of_te = 0
        if student.semester == 7:
            for course in self.__current_courses:
                if isinstance(course, TechnicalElective):
                    no_of_te += 1

        return False if no_of_te > 2 else True

    def total_credits(self):
        total_credits = 0
        for course in self.__current_courses:
            total_credits += course.credit
        return total_credits

    @property
    def is_approved(self):
        return self.__is_approved

    @is_approved.setter
    def is_approved(self, approval):
        self.__is_approved = approval

    @property
    def current_courses(self):
        return self.__current_courses

    def __str__(self):
        return ' '.join([
            'is approved:', str(self.__is_approved),
            'total credits:', str(self.total_credits()),
            'current courses:', str(self.__current_courses),
        ])

    def __repr__(self):
        return self.__str__()
