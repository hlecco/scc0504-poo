#include <calc.hpp>
#include <iostream>
#include <ctime>

using namespace std;

int main(int argc, char* argv[]) {
  time_t now = time(0);
  tm *ltm = localtime(&now);
  Data hoje(ltm->tm_mday, 1 + ltm->tm_mon, 1900 + ltm->tm_year);
  Usuario caio("caio", 24, "/home/caio/Imagens", &hoje);
  Operacao operacao(&caio, &hoje, 1, 2, Soma);
  
  operacao.mostra();
  
  return 0;
}
