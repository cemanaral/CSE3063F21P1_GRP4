from simulation_classes.course_loader import CourseLoader
from simulation_classes.random_advisor_creator import RandomAdvisorCreator
from simulation_classes.random_student_creator import RandomStudentCreator
from simulation_classes.registrationsystem import RegistrationSystem


def main():
    simulation = RegistrationSystem(
        'input.json',
        student_creator=RandomStudentCreator(),
        advisor_creator=RandomAdvisorCreator(),
        course_loader=CourseLoader(),
    )
    simulation.start_simulation()


if __name__ == "__main__":
    main()
