from predio import Predio

class Casa(Predio):

    def __init__(self, num_andares, elevador, garagem):
        super().__init__(num_andares, elevador, garagem)

    def get_pegada_de_carbono(self):
        print("Casa. Carbono:", 25)
