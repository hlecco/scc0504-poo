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

    std::string style;
    std::cout << "Estilo: ";
    std::getline(std::cin, style);
    text->setStyle(style);

    std::string color;
    std::cout << "Cor: ";
    std::getline(std::cin, color);
    text->setColor(color);

    std::cout << text->show() << std::endl;

    return 0;
}
