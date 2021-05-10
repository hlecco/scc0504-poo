#include <iostream>
#include <string>
#include <device.hpp>

int main() {
    Printer *impressora = new Printer("EPSILON W34TC");
    impressora->calibrate();

    Mouse *mouse = new Mouse("HOJETECH G600");
    mouse->calibrate();

    Keyboard *teclado = new Keyboard("LAZER PRO 61");
    teclado->calibrate();

    return 0;
}
