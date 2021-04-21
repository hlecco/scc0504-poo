#include <iostream>
#include <drivers.hpp>

using namespace std;

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
