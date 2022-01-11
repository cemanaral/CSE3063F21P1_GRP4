import json
import random

from domain_classes.person import Advisor
from simulation_classes.subsystem import Subsystem


class RandomAdvisorCreator(Subsystem):
    def __init__(self):
        self.__first_names = []
        self.__last_names = []
        self.__no_of_advisors = 0
        self.__created_advisors = []

    def start(self, json_file_name):
        self.__load_json_file(json_file_name)
        self.__create_advisors()

    @property
    def loaded_data(self):
        return self.__created_advisors

    def __load_json_file(self, json_file_name):
        with open(json_file_name, 'r') as file_in:
            data = json.load(file_in)
        self.__first_names = data['person']['first_names']
        self.__last_names = data['person']['last_names']
        self.__no_of_advisors = data['person']['no_of_advisors']

    def __create_advisors(self):
        for i in range(self.__no_of_advisors):
            self.__create_single_advisor()

    def __create_single_advisor(self):
        first_name = random.choice(self.__first_names)
        last_name = random.choice(self.__last_names)
        self.__created_advisors.append(
            Advisor(first_name, last_name)
        )
