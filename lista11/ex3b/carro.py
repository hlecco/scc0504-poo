from carbono import Pegada_de_Carbono

class Carro(Pegada_de_Carbono):

    def __init__(self, cor, combustivel, eletrico, hibrido):
        self.cor = cor
        self.combustivel = combustivel
        self.eletrico = eletrico
        self.hibrido = hibrido

    def get_pegada_de_carbono(self):
        if self.eletrico is True:
            print("Carro elétrico. Carbono:", 10)
        elif self.hibrido is True:
            print("Carro híbrido. Carbono:", 15)
        elif self.combustivel == "alcool":
            print("Carro a álcool. Carbono:", 20)
        elif self.combustivel == "gasolina":
            print("Carro a gasolina. Carbono:", 25)
        else:
            print("Carro a gás ou diesel. Carbono:", 30)
        return
