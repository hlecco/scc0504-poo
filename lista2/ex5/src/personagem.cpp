#include <iostream>
#include <list>
#include <herois.hpp>

using namespace std;

Personagem::Personagem(string n, string real, double v) {
    nome = n;
    nomeVidaReal = nomeVidaReal;
    vida = v;
    list<SuperPoder *> poderes;
}

string Personagem::getNome() {
  return nome;
}

string Personagem::getNomeVidaReal() {
  return nomeVidaReal;
}

int Personagem::getTotalPoder() {
    return poderes.size();
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

SuperPoder * Personagem::getSuperPoder(int num_poder) {
    return poderes[num_poder];
}

void Personagem::setVida(double pVida) {
  if (pVida > 0) {
    vida = pVida;
  }
  else {
    vida = 0;
  }
}

void Personagem::adicionaSuperPoder(SuperPoder *pSuperPoder) {
  poderes.push_back(pSuperPoder);
}

void Personagem::atacar(double intensidade, string nomeSuperPoder, Personagem *p) {
  list<SuperPoder *>::iterator it;
  int tmp = 0;

  for (it = poderes.begin(); it != poderes.end(); ++it) {
	if (nomeSuperPoder == (*it)->getNome()) {
		tmp = 1;
	}
  }

  if (tmp == 0) {
	cout << "Superpoder não existe." << endl;
	return;
  }

  int aleatorio = rand() % 50;
  cout << "aleatorio " << aleatorio << endl;

  if (aleatorio < 50) {
	p->setVida(p->getVida() - intensidade);
  }
}

SuperHeroi::SuperHeroi(string n, string real, double v) : Personagem(n, real, v) {
}

Vilao::Vilao(string n, string real, double v) : Personagem(n, real, v) {
    anosDePrisao = 0;
}

void Vilao::setAnosDePrisao(int pAnosDePrisao) {
  anosDePrisao = pAnosDePrisao;
}

int Vilao::getAnosDePrisao() {
  return anosDePrisao;
}
