class Simulation:
    def __init__(self, json_file_name, **kwargs):
        self.__json_file_name = json_file_name
        self.__subsystems = kwargs
        self.__students = []
        self.__advisors = []
        self.__courses = []

    def start_simulation(self):
        self.__start_subsystems()
        self.__load_data_from_subsystems()
        self.__assign_advisors()


        for c in self.__courses: print(c)
        for s in self.__students: print(s)
        for a in self.__advisors: print(a)

    def __start_subsystems(self):
        for subsystem in self.__subsystems.values():
            subsystem.start(self.__json_file_name)

    def __load_data_from_subsystems(self):
        self.__courses = self.__subsystems['course_loader'].loaded_data
        self.__students = self.__subsystems['student_creator'].loaded_data
        self.__advisors = self.__subsystems['advisor_creator'].loaded_data

    def __assign_advisors(self):
        no_of_advisors = len(self.__advisors)
        for i, student in enumerate(self.__students):
            student.advisor = self.__advisors[i % no_of_advisors]
