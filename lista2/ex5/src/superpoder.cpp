#include <iostream>
#include <list>
#include <herois.hpp>

using namespace std;

SuperPoder::SuperPoder(int c, string n) {
  categoria = c;
  nome = n;
}

int SuperPoder::getCategoria() {
  return categoria;
}

string SuperPoder::getNome() {
  return nome;
}

void SuperPoder::setCategoria(int pCategoria) {
  categoria = pCategoria;
}

void SuperPoder::setNome(string pNome) {
  nome = pNome;
}
