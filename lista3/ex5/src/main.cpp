#include <iostream>
#include <string>
#include <exibicao.hpp>

using namespace std;

int main(int argc, char* argv[]) {
  ExibicaoTexto teste("blabla");

  teste.ExibeTexto();
  teste.ExibeTexto(0);
  teste.ExibeTexto("Iosevka");
  teste.ExibeTexto('r');
  teste.ExibeTexto(1, "Comic Sans");
  teste.ExibeTexto(2, 'r');
  teste.ExibeTexto("Iosevka", 'g');
  teste.ExibeTexto(3, "Comic Sans", 'b');
  
  return 0;
}
