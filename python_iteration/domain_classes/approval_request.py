class ApprovalRequest:
    def __init__(self):
        self.__current_courses = []
        self.__is_approved = False

    def add_current_course(self, course):
        self.__current_courses.append(course)

    def __str__(self):
        return ' '.join([
            type(self).__name__,
            'current courses:', str(self.__current_courses),
            'is approved:', str(self.__is_approved)
        ])

    def __repr__(self):
        return self.__str__()