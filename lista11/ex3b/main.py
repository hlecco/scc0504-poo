import carro
import bicicleta
import casa
import escola

def main():
    k1 = casa.Casa(3, True, True)
    k2 = casa.Casa(1, False, False)
    e1 = escola.Escola(1, False, True)
    e2 = escola.Escola(11, True, True)
    c1 = carro.Carro("vermelho", "", True, False)
    c2 = carro.Carro("amarelo", "gasolina", False, False)
    b1 = bicicleta.Bicicleta(27, 29, False)
    b2 = bicicleta.Bicicleta(24, 26, True)

    lista = [k1, k2, e1, e2, c1, c2, b1, b2]

    for elem in lista:
        elem.get_pegada_de_carbono()

main()
