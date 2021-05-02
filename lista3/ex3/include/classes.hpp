#include <string>

using namespace std;

class PegadaDeCarbono {
public:
  virtual double getPegadaDeCarbono() = 0;
};

class Predio: public PegadaDeCarbono {
private:
  int numAndares;
  bool elevador;
  bool garagem;
public:
  Predio(int, bool, bool);
  double getPegadaDeCarbono();
};

class Carro: public PegadaDeCarbono {
private:
  string cor;
  string combustivel;
  bool eletrico;
  bool hibrido;
public:
  Carro(string, string, bool, bool);
  double getPegadaDeCarbono();
};

class Bicicleta: public PegadaDeCarbono {
private:
  int numMarchas;
  double tamanhoAro;
  bool eletrica;
public:
  Bicicleta(int, double, bool);
  double getPegadaDeCarbono();
};
