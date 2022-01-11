class ApprovalRequest:
    def __init__(self):
        self.__current_courses = []
        self.__is_approved = None

    def add_current_course(self, course):
        self.__current_courses.append(course)

    # from information expert
    def check_credit_limit(self):
        if self.total_credits() > 40:
            return False  # should not be approved
        return True  # no problem

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
            type(self).__name__,
            'current courses:', str(self.__current_courses),
            'is approved:', str(self.__is_approved)
        ])

    def __repr__(self):
        return self.__str__()
