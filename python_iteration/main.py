from simulation import *


def main():
    simulation = Simulation(
        student_creator=RandomStudentCreator(),
        advisor_creator=RandomAdvisorCreator(),
        course_loader=CourseLoader(),
    )
    simulation.start_simulation()


if __name__ == "__main__":
    main()
