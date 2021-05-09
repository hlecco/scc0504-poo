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

// Style
Style::Style(std::string f) : FormattingOption(f) {}

// Color
Font::Font(std::string f) : FormattingOption(f) {
    format_preffix = "com fonte";
}
