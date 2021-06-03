#include <dispositivo.hpp>

using namespace std;

int main() {
    auto relogio = new Relogio;
    auto radio = new Radio;
    auto radio_relogio = new RadioRelogio;

    relogio->setHora(1030);
    cout << "Agora são " << relogio->getHora() << endl;
    relogio->setHora(1030);

    radio->setEstacao(96.5);
    cout << "Escutando a estação " << radio->getEstacao() << endl;

    radio_relogio->setAlarme(1130, 93.5);
    radio_relogio->setHora(1045);
    radio_relogio->setEstacao(70.1);
    radio_relogio->setFM();
    cout << "Alarme definido: " << radio_relogio->getAlarme() << endl;
    cout << "Escutando estação " << radio_relogio->getEstacao() << endl;
    cout << "Agora são " << radio_relogio->getHora() << endl;

    delete relogio;
    delete radio;
    delete radio_relogio;

    return 0;
}
