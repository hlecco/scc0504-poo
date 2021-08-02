from carbono import Pegada_de_Carbono

class Bicicleta(Pegada_de_Carbono):

    def __init__(self, num_marchas, tam_aro, eletrica):
        self.num_marchas = num_marchas
        self.tam_aro = tam_aro
        self.eletrica = eletrica
    
    def get_pegada_de_carbono(self):
        if self.eletrica is True:
            print("Bicicleta elétrica. Carbono:", 2)
        else:
            print("Bicicleta não elétrica. Carbono:", 0.5)
        return
