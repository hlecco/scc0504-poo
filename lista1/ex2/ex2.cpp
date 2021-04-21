#include <iostream>

using namespace std;

class drivers {
private:
  string nome;
  bool status;
public:
  void ligaDispositivo();
  void verificaStatus();
  void executaTeste();
  
  void alteraNome(string);
  void mostraNome();
};

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

class driverVideo: public drivers {
private:
  int brilhoExibicao;
public:
  void alteraBrilhoDeExibicao(int);
  void mostraBrilho();
};

void driverVideo::alteraBrilhoDeExibicao(int novoBrilho) {
  brilhoExibicao = novoBrilho;
}

void driverVideo::mostraBrilho() {
  cout << brilhoExibicao << endl;
}

int main(int argc, char* argv[]) {
  drivers d;
  driverVideo nvidia;
  string nome = "bla";

  nvidia.alteraBrilhoDeExibicao(13);
  d.alteraNome(nome);
  d.mostraNome();
  nvidia.mostraBrilho();
  
  return 0;
}
