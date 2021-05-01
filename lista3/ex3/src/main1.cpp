#include <classes.hpp>
#include <list>
#include <iostream>

using namespace std;

int main(int argc, char* argv[]) {
  Predio p1(4, false, true);
  Predio p2(20, true, true);

  Carro c1("vermelho", "", true, false);
  Carro c2("amarelo", "gasolina", false, false);

  Bicicleta b1(27, 29, false);
  Bicicleta b2(24, 26, true);

  list<PegadaDeCarbono> lista;
  lista.push_back(p1);
  lista.push_back(p2);
  lista.push_back(c1);
  lista.push_back(c2);
  lista.push_back(b1);
  lista.push_back(b2);

  list<PegadaDeCarbono>::iterator it;
  
  for (it = lista.begin(); it != lista.end(); it++) {
	cout << (*it).getPegadaDeCarbono() << endl;
  }
  
  return 0;
}
