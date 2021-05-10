#include <iostream>
#include <string>
#include <textformat.hpp>

// Text
Text::Text(std::string t) {
    content = t;
}

std::string Text::getContent() {
    return content;
}

int Text::getLen() {
    return content.size();
}

void Text::edit(std::string t) {
    content = t;
}

void Text::append(std::string t) {
    content.append(t);
}

// FormattedText
FormattedText::FormattedText(std::string t) : Text(t) {
    font = new Font("");
    color = new Color("");
    style = new Style("");
}

std::string FormattedText::show() {
    std::string fmt;
    if (!style->empty()) {
	if (!fmt.empty()) {
	    fmt.append(", ");
	}
	fmt.append(style->show());
    }
    if (!font->empty()) {
	if (!fmt.empty()) {
	    fmt.append(", ");
	}
	fmt.append(font->show());
    }
    if (!color->empty()) {
	if (!fmt.empty()) {
	    fmt.append(", ");
	}
	fmt.append(color->show());
    }
    if (fmt.empty()) {
	fmt = "sem formatação";
    }

    std::string text_format = "\"";
    text_format.append(getContent());
    text_format.append(" -> ");
    text_format.append(fmt);
    text_format.append("\"");

    return text_format;
}

void FormattedText::setFont(std::string f) {
    font->set(f);
}
void FormattedText::setColor(int f) {
    color->set(f);
}
void FormattedText::setStyle(int f) {
    style->set(f);
}
