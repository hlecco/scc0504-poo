#include <iostream>
#include <list>
#include <stdlib.h>

using namespace std;

class SuperPoder {
private:
  int categoria;
  string nome;
public:
  SuperPoder(int, string);
  int getCategoria();
  string getNome();
  void setCategoria(int);
  void setNome(string);
};

class Personagem {
private:
  string nome;
  string nomeVidaReal;
  list<SuperPoder *> poderes;
  double vida;
public:
  Personagem(string, string, double);
  string getNome();
  string getNomeVidaReal();
  int getTotalPoder();
  void setNome(string);
  void setNomeVidaReal(string);
  double getVida();
  void setVida(double pVida);
  void adicionaSuperPoder(SuperPoder *);
  void atacar(double, string, Personagem *);
  SuperPoder * getSuperPoder(int);
};

class SuperHeroi: public Personagem {
public:
    SuperHeroi(string, string, double);
};

class Vilao: public Personagem {
private:
  int anosDePrisao;
public:
  Vilao(string, string, double);
  int getAnosDePrisao();
  void setAnosDePrisao(int);
};
