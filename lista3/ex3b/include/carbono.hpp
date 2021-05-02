#include <string>
#include <iostream>

using namespace std;

class PegadaDeCarbono {
public:
  virtual double getPegadaDeCarbono() = 0;
};

class Predio: public PegadaDeCarbono {
protected:
  int numAndares;
  bool elevador;
  bool garagem;
public:
  virtual double getPegadaDeCarbono() = 0;
};

class Casa: public Predio {
public:
  Casa(int, bool, bool);
  double getPegadaDeCarbono();
};

class Escola: public Predio {
public:
  Escola(int, bool, bool);
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
