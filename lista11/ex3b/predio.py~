from carbono import Pegada_de_Carbono

class Predio(Pegada_de_Carbono):

    def __init__(self, num_andares, elevador, garagem):
        self.num_andares = num_andares
        self.elevador = elevador
        self.garagem = garagem

    def get_pegada_de_carbono(self):
        if self.num_andares < 5 and self.elevador is False:
            print("Prédio com menos de 5 andares sem elevador. Carbono:", 50)
        elif self.num_andares < 5 and self.elevador is True:
            print("Prédio com menos de 5 andares com elevador. Carbono:", 65)
        elif self.num_andares >= 5 and self.num_andares <= 10:
            print("Prédio com 5 andares ou mais e menos de 11. Carbono:", 80)
        else:
            print("Prédio com mais de 10 andares. Carbono:", 100)
