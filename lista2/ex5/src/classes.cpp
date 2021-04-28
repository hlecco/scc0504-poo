#include <iostream>
#include <list>
#include <jogo.hpp>

using namespace std;

int Superpoder::getCategoria() {
  return categoria;
}

string Superpoder::getNome() {
  return nome;
}

void Superpoder::setCategoria(int pCategoria) {
  categoria = pCategoria;
}

void Superpoder::setNome(string pNome) {
  nome = pNome;
}

string Personagem::getNome() {
  return nome;
}

string Personagem::getNomeVidaReal() {
  return nomeVidaReal;
}

int Personagem::getTotalPoder() {
  list<Superpoder>::iterator it;
  int total = 0;
  
  for (it = poderes.begin(); it != poderes.end(); ++it) {
	total += (*it).getCategoria();
  }
  
  return total;
}

void Personagem::setNome(string pNome) {
  nome = pNome;
}

void Personagem::setNomeVidaReal(string pNomeVidaReal) {
  nomeVidaReal = pNomeVidaReal;
}

double Personagem::getVida() {
  return vida;
}

void Personagem::setVida(double pVida) {
  vida = pVida;
}

void Personagem::setSuperpoder(Superpoder pSuperpoder) {
  poderes.push_back(pSuperpoder);
}

void Personagem::atacar(double intensidade, string nomeSuperPoder, Personagem *p) {
  list<Superpoder>::iterator it;
  int tmp = 0;
  
  for (it = poderes.begin(); it != poderes.end(); ++it) {
	if (nomeSuperPoder == (*it).getNome()) {
		tmp += 1;
	  }
  }

  if (tmp == 0) {
	cout << "Superpoder não existe." << endl;
	return;
  }

  double pVida = (*p).getVida();
  int aleatorio = rand() % 50;
  cout << "aleatorio " << aleatorio << endl;
  
  if (aleatorio < 50) {
	(*p).setVida(pVida - intensidade);
  }
}

void Vilao::setAnosDePrisao(int pAnosDePrisao) {
  anosDePrisao = pAnosDePrisao;
}

int Vilao::getAnosDePrisao() {
  return anosDePrisao;
}
