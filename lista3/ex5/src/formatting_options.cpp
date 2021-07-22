#include <iostream>
#include <string>
#include <textformat.hpp>

// FormattingOption
FormattingOption::FormattingOption(std::string f) {
    format = f;
}
void FormattingOption::set(std::string f) {
    format = f;
}
bool FormattingOption::empty() {
    return format.empty();
}
std::string FormattingOption::show() {
    std::string str_show;
    str_show.append(format_preffix);
    if (!str_show.empty()) {
		str_show.append(" ");
    }
    str_show.append(format);

    return str_show;
}

// Color
Color::Color(std::string f) : FormattingOption(f) {
    format_preffix = "na cor";
}
void Color::set(int x) {
    switch (x) {
	case 0:
	    FormattingOption::set("preta");
	    break;
	case 1:
	    FormattingOption::set("azul");
	    break;
	case 2:
	    FormattingOption::set("verde");
	    break;
	case 3:
	    FormattingOption::set("ciano");
	    break;
	case 4:
	    FormattingOption::set("vermelho");
	    break;
	case 5:
	    FormattingOption::set("magenta");
	    break;
	case 6:
	    FormattingOption::set("amarelo");
	    break;
	case 7:
	    FormattingOption("branco");
	    break;
    }
}

// Style
Style::Style(std::string f) : FormattingOption(f) {}
void Style::set(int x) {
    switch (x) {
	case 0:
	    break;
	case 1:
	    FormattingOption::set("sublinhado");
	    break;
	case 2:
	    FormattingOption::set("itálico");
	    break;
	case 3:
	    FormattingOption::set("sublinhado e itálico");
	    break;
	case 4:
	    FormattingOption::set("negrito");
	    break;
	case 5:
	    FormattingOption::set("negrito e sublinhado");
	    break;
	case 6:
	    FormattingOption::set("negrito e itálico");
	    break;
	case 7:
	    FormattingOption::set("negrito, sublinhado e itálico");
	    break;
    }
}
// Font
Font::Font(std::string f) : FormattingOption(f) {
    format_preffix = "com fonte";
}
