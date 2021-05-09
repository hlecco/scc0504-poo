#ifndef TEXTFMT
#define TEXTFMT

#include <iostream>
#include <string>

class FormattingOption {
public:
    FormattingOption(std::string);
    void set(std::string);
    bool empty();
    std::string show();
protected:
    std::string format_preffix;
private:
    std::string format;
};

class Color : public FormattingOption {
public:
    Color(std::string);
};

class Style : public FormattingOption {
public:
    Style(std::string);
};

class Font : public FormattingOption {
public:
    Font(std::string);
};

class Text {
public:
    Text(std::string);
    std::string getContent();
    int getLen();
    void edit(std::string);
    void append(std::string);
private:
    std::string content;
};

class FormattedText : public Text {
public:
    FormattedText(std::string);
    std::string show();
    void setFont(std::string);
    void setColor(std::string);
    void setStyle(std::string);
private:
    Font *font;
    Style *style;
    Color *color;
};

#endif
