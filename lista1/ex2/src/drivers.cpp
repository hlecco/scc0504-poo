#include <iostream>
#include <drivers.hpp>

using namespace std;

void drivers::alteraNome(string pnome) {
  nome = pnome;
}

void drivers::mostraNome() {
  cout << nome << endl;
}

class driverRede: public drivers {
  int tamPacote;
public:
  void enviaPacoteDeDados();
};

class driverImpressao: public drivers {
private:
  int numPaginas;
public:
  void imprimePaginas();
};

void driverVideo::alteraBrilhoDeExibicao(int novoBrilho) {
  brilhoExibicao = novoBrilho;
}

void driverVideo::mostraBrilho() {
  cout << brilhoExibicao << endl;
}
