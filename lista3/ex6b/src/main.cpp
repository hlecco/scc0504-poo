#include <iostream>
#include <string>
#include <vector>
#include <device.hpp>
#include <keyboard.hpp>

int main() {
    Printer *impressora = new Printer("EPSILON W34TC");
    impressora->calibrate();

    Mouse *mouse = new Mouse("LOJATECH G600");
    mouse->calibrate();

    Keyboard *teclado = new Keyboard("LAZER PRO", 105);
    teclado->calibrate();

    MechanicalKeyboard *teclado_mecanico = new MechanicalKeyboard("UHK 61", 61);
    std::vector<int> macro{11, 15, 11};
    teclado_mecanico->setMacro(macro);

    NumPad *numpad = new NumPad("GENERIC NUMPAD", 15);
    numpad->remapKey(11, 12);
    numpad->calibrate();

    BacklitKeyboard *laptop_keyboard = new BacklitKeyboard("DÉU 7580", 80);
    laptop_keyboard->remapKey(11, 12);
    laptop_keyboard->setBacklight(100);

    delete impressora;
    delete mouse;
    delete teclado;
    delete teclado_mecanico;
    delete numpad;
    delete laptop_keyboard;

    return 0;
}
