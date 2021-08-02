import abc

from carbono import Pegada_de_Carbono

class Predio(Pegada_de_Carbono, metaclass=abc.ABCMeta):

    def __init__(self, num_andares, elevador, garagem):
        self.num_andares = num_andares
        self.elevador = elevador
        self.garagem = garagem

    def get_pegada_de_carbono(self):
        pass
