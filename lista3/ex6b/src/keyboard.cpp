#include <iostream>
#include <string>
#include <vector>
#include <device.hpp>
#include <keyboard.hpp>


// Keyboard 
Keyboard::Keyboard(std::string n, int s) : Device(n) {
    number_of_keys = s;
    for (int i=0; i<s; i++) {
	keymap.push_back(i);
    }
}

void Keyboard::calibrate() {
    std::cout << "Teclado calibrado com sucesso" << std::endl; 
}

void Keyboard::remapKey(int true_key, int fake_key) {
    if ((true_key > -1) && (true_key < number_of_keys)
        && (fake_key > -1) && (fake_key < number_of_keys)) {
	keymap[true_key] = fake_key;
    }
}


// BacklitKeyboard
BacklitKeyboard::BacklitKeyboard(std::string n, int s) : Keyboard(n, s) {
    backlight = 0;
}
void BacklitKeyboard::setBacklight(int light) {
    if (light > -1) {
	if (light < 256) {
	    backlight = light;
	}
	else {
	    backlight = 255;
	}
    }
    else {
	backlight = 0;
    }
}

// MechanicalKeyboard
MechanicalKeyboard::MechanicalKeyboard(std::string n, int s) : Keyboard(n, s) {}
void MechanicalKeyboard::setMacro(std::vector<int> new_macro) {
    macro = new_macro;
}

// NumPad
NumPad::NumPad(std::string n, int s) : Keyboard(n, s) {};
void NumPad::remapKey(int x, int y) {
    std::cout << "Can't remap keys on a numpad" << std::endl;
}
