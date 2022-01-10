from simulation import *


def main():
    simulation = Simulation(
        student_creator=RandomStudentCreator(json_file_name='input.json'),
        advisor_creator=RandomAdvisorCreator(),
        course_loader=CourseLoader(json_file_name='input.json'),
    )
    simulation.start_simulation()


if __name__ == "__main__":
    main()
