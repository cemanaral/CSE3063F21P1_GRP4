from simulation_classes.course_loader import CourseLoader
from simulation_classes.random_advisor_creator import RandomAdvisorCreator
from simulation_classes.random_student_creator import RandomStudentCreator
from simulation_classes.simulation import Simulation


def main():
    simulation = Simulation(
        student_creator=RandomStudentCreator(json_file_name='input.json'),
        advisor_creator=RandomAdvisorCreator(json_file_name='input.json'),
        course_loader=CourseLoader(json_file_name='input.json'),
    )
    simulation.start_simulation()


if __name__ == "__main__":
    main()
