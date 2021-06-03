#include <dispositivo.hpp>

using namespace std;

int main() {
	Relogio relogio;
	Radio radio;
	Dispositivo *disp;

	disp = &relogio;
	disp->Liga_Desliga();
	disp->DefineFabricante("Rolex");
	cout << relogio.PegaFabricante() << endl; // verifica que de fato o objeto relógio foi alterado

	disp = &radio;
	disp->Liga_Desliga();
	disp->DefineFabricante("Philips");
	cout << radio.PegaFabricante() << endl; // verifica que de fato o objeto radio foi alterado

    return 0;
}
