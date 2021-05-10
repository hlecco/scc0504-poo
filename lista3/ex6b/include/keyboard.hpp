#ifndef KEYBOARDHPP
#define KEYBOARDHPP

#include <string>
#include <vector>
#include <device.hpp>

class Keyboard : public Device {
public:
    Keyboard(std::string, int);
    void calibrate();
    void remapKey(int, int);
private:
    int number_of_keys;
    std::vector<int> keymap;
};

class BacklitKeyboard : public Keyboard {
public:
    BacklitKeyboard(std::string, int);
    void setBacklight(int);
private:
    int backlight;
};

class MechanicalKeyboard : public Keyboard {
public:
    MechanicalKeyboard(std::string, int);
    void setMacro(std::vector<int>);
private:
    std::vector<int> macro;
};

class NumPad : public Keyboard {
public:
    NumPad(std::string, int);
    void remapKey(int, int);
};
#endif
