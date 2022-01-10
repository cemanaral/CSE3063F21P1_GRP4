class Simulation:
    def __init__(self, *subsystems):
        self.__subsystems = subsystems

    def start_simulation(self):
        for subsystem in self.__subsystems:
            subsystem.start()


class RandomStudentCreator:
    def start(self):
        print("RandomStudentCreator is executing")


class RandomAdvisorCreator:
    def start(self):
        print("RandomAdvisorCreator is executing")


class CourseLoader:
    def start(self):
        print("CourseLoader is executing")
