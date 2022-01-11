from abc import ABC as AbstractBaseClass, abstractmethod


class Subsystem(AbstractBaseClass):
    @abstractmethod
    def start(self, json_file_name):
        raise NotImplementedError()

    @abstractmethod
    def loaded_data(self):
        raise NotImplementedError()
