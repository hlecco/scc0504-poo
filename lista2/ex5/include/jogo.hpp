#include <iostream>
#include <list>
#include <stdlib.h>

using namespace std;

class Superpoder {
private:
  int categoria;
  string nome;
public:
  int getCategoria();
  string getNome();
  void setCategoria(int pCategoria);
  void setNome(string pNome);
};

class Personagem {
private:
  string nome;
  string nomeVidaReal;
  list<Superpoder> poderes;
  double vida;
public:
  string getNome();
  string getNomeVidaReal();
  int getTotalPoder();
  void setNome(string);
  void setNomeVidaReal(string);
  double getVida();
  void setVida(double pVida);
  void setSuperpoder(Superpoder pSuperpoder);
  void atacar(double intensidade, string nomeSuperPoder, Personagem *p);
};

class SuperHeroi: public Personagem {
};

class Vilao: public Personagem {
private:
  int anosDePrisao;
public:
  int getAnosDePrisao();
  void setAnosDePrisao(int);
};
