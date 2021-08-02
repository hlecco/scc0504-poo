from predio import Predio

class Escola(Predio):

    def __init__(self, num_andares, elevador, garagem):
        super().__init__(num_andares, elevador, garagem)

    def get_pegada_de_carbono(self):
        if self.num_andares < 5 and self.elevador is False:
            print("Escola com menos de 5 andares sem elevador. Carbono:", 50)
        elif self.num_andares < 5 and self.elevador is True:
            print("Escola com menos de 5 andares com elevador. Carbono:", 65)
        elif self.num_andares >= 5 and self.num_andares <= 10:
            print("Escola 5 andares ou mais e menos de 11. Carbono:", 80)
        else:
            print("Escola mais mais de 10 andares. Carbono:", 100)
        return
