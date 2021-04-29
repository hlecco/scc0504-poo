#include <calc.hpp>
#include <iostream>
#include <ctime>

using namespace std;

int main(int argc, char* argv[]) {
  Data hoje;
  hoje.setData(20, 11, 2010);
  Usuario caio("caio", 24, "/home/caio/Imagens", hoje);
  
  
  cout << hoje.getData() << endl;
  cout << caio.getNome() << endl;
  realizaCalculo(caio, "soma");
  
  return 0;
}
