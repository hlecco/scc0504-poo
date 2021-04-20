#include <iostream>
#include <list>
#include <math.h>
#include <polinomio.hpp>

using namespace std;

int main(int arc, char* argv[]) {
  Polinomio quadratico(2);
  Termo novo(3.0, 1);
  
  quadratico.mostraPolinomio();
  if (quadratico.adicionaTermo(novo) == -1) {
	cout << "Erro ao adicionar termo." << endl;
	return -1;
  }
  quadratico.mostraPolinomio();
  cout << quadratico.calculaPolinomio(2.0) << endl;
  
  return 0;
}
