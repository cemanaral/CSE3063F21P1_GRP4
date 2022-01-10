from abc import ABC as AbstractBaseClass, abstractmethod

class Simulation:
    def __init__(self, **kwargs):
        self.__subsystems = kwargs
        self.__students = []
        self.__advisors = []
        self.__courses = []

    def start_simulation(self):
        for subsystem in self.__subsystems.values():
            subsystem.start()

class Subsystem(AbstractBaseClass):
    @abstractmethod
    def start(self):
        raise NotImplementedError()

class RandomStudentCreator(Subsystem):
    def start(self):
        print("RandomStudentCreator is executing")


class RandomAdvisorCreator(Subsystem):
    def start(self):
        print("RandomAdvisorCreator is executing")


class CourseLoader(Subsystem):
    def start(self):
        print("CourseLoader is executing")
