#include <carbono.hpp>
#include <iostream>
#include <vector>

using namespace std;

int main(int argc, char* argv[]) {
  Casa k1(3, true, true);
  Casa k2(1, false, false);

  Escola e1(1, false, false);
  Escola e2(10, true, true);

  Carro c1("vermelho", "", true, false);
  Carro c2("amarelo", "gasolina", false, false);

  Bicicleta b1(27, 29, false);
  Bicicleta b2(24, 26, true);

  vector<PegadaDeCarbono*> vetor;
  vetor.push_back(&k1);
  vetor.push_back(&k2);
  vetor.push_back(&e1);
  vetor.push_back(&e2);
  vetor.push_back(&c1);
  vetor.push_back(&c2);
  vetor.push_back(&b1);
  vetor.push_back(&b2);

  vector<PegadaDeCarbono*>::iterator it;
  
  for (it = vetor.begin(); it != vetor.end(); it++) {
	cout << (*it)->getPegadaDeCarbono() << endl;
  }
  
  return 0;
}
