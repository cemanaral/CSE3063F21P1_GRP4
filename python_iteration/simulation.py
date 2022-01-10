class Simulation:
    def __init__(self, **kwargs):
        self.__subsystems = kwargs

    def start_simulation(self):
        for subsystem in self.__subsystems.values():
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
