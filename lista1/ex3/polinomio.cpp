#include <iostream>
#include <list>
#include <math.h>

using namespace std;

class Termo {
private:
  float coeficiente;
  int grau;
public:
  Termo(float, int);
  string mostraTermo();
  int mostraGrau();
  float mostraCoeficiente();
  void alteraCoeficiente(float);
};

Termo::Termo(float pcoef, int pgrau) {
  coeficiente = pcoef;
  grau = pgrau;
}

string Termo::mostraTermo() {
  return to_string(coeficiente) + "x^" + to_string(grau);
}

int Termo::mostraGrau() {
  return grau;
}

float Termo::mostraCoeficiente() {
  return coeficiente;
}

void Termo::alteraCoeficiente(float novoCoef) {
  coeficiente = novoCoef;
}

class Polinomio {
private:
  list<Termo> termos;
public:
  Polinomio(int);
  int adicionaTermo(Termo);
  void mostraPolinomio();
  float calculaPolinomio(float);
};

Polinomio::Polinomio(int pgrau) {
  Termo lider(1.0, pgrau);
  termos.push_back(lider);
}

void Polinomio::mostraPolinomio() {
  list<Termo>::iterator it;
  for (it = termos.begin(); it != termos.end(); it++) {
	cout << (*it).mostraTermo();
	if (next(it,1) != termos.end()) {
	  cout << " + ";
	}
  }
  cout << endl;
}

int Polinomio::adicionaTermo(Termo novo) {
  if (novo.mostraGrau() > termos.front().mostraGrau()
	  || novo.mostraGrau() < 0) {
	return -1;
  }
  
  list<Termo>::iterator it;
  
  for (it = termos.begin(); it != termos.end(); ++it) {
	if ((*it).mostraGrau() == novo.mostraGrau()) {
	  (*it).alteraCoeficiente((*it).mostraCoeficiente()
							  + novo.mostraCoeficiente());
	  return 0;
	  }
  }
  
  termos.push_back(novo);

  return 0;
}

float Polinomio::calculaPolinomio(float x) {
  list<Termo>::iterator it;
  float soma = 0.0;
  
  for (it = termos.begin(); it != termos.end(); it++) {
	soma += (*it).mostraCoeficiente() * pow(x,(*it).mostraGrau());
  }
  
  return soma;
}
