from simulation import *

def main():
    simulation = Simulation(
        RandomStudentCreator(),
        RandomAdvisorCreator(),
        CourseLoader(),
    )
    simulation.start_simulation()


if __name__ == "__main__":
    main()
