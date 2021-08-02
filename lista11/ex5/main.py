from classe import *

def main():
    print("Escreva o texto: ")
    text = input()
    ft = FormattedText(text)

    print("Escolha a fonte: ")
    font = input()
    ft.setFont(font)

    print("Estilos: negrito, sublinhado e itálico. \n\
Digite um número entre 0 e 7:")
    style = int(input())
    ft.setStyle(style)

    print("Escolha a cor: ")
    cor = input()
    ft.setColor(cor)

    print(ft.show())

    return

main()
