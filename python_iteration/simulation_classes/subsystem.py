from abc import ABC as AbstractBaseClass, abstractmethod


class Subsystem(AbstractBaseClass):
    @abstractmethod
    def start(self):
        raise NotImplementedError()

    @abstractmethod
    def loaded_data(self):
        raise NotImplementedError()
