#include <iostream>
#include <dispositivo.hpp>

using namespace std;

int main() {
    auto relogio = new Relogio;
    auto radio = new Radio;
    auto radio_relogio = new RadioRelogio;

    radio_relogio->Liga_Desliga();
    radio_relogio->Liga_Desliga();

    relogio->Liga_Desliga();
    relogio->Liga_Desliga();

    radio->Liga_Desliga();
    radio->Liga_Desliga();

    delete relogio;
    delete radio;
    delete radio_relogio;

    return 0;
}
