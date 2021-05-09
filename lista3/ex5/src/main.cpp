#include <iostream>
#include <string>
#include <textformat.hpp>


int main() {
    FormattedText *text;
    std::string text_txt;
    std::cout << "Texto: ";
    std::getline(std::cin, text_txt);
    text = new FormattedText(text_txt);

    std::string font;
    std::cout << "Fonte: ";
    std::getline(std::cin, font);
    text->setFont(font);

    int style;
    std::cout << "Estilo (3-bit, negrito, sublinhado e itálico)" << std::endl;
    std::cout << "Digite um número entre 0 e 7: ";
    std::cin >> style;
    std::cin.clear();
    text->setStyle(style);

    int color;
    std::cout << "Cor (3-bit, RGB)" << std::endl;
    std::cout << "Digite um número entre 0 e 7: ";
    std::cin >> color;
    std::cin.clear();
    text->setColor(color);

    std::cout << text->show() << std::endl;

    return 0;
}
