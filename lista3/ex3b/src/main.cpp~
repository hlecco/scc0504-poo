#include <carbono.hpp>
#include <iostream>
#include <vector>

using namespace std;

int main(int argc, char* argv[]) {
  Predio p1(4, false, true);
  Predio p2(20, true, true);

  Carro c1("vermelho", "", true, false);
  Carro c2("amarelo", "gasolina", false, false);

  Bicicleta b1(27, 29, false);
  Bicicleta b2(24, 26, true);

  vector<PegadaDeCarbono*> vetor;
  vetor.push_back(&p1);
  vetor.push_back(&p2);
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
