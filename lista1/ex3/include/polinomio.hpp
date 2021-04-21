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

class Polinomio {
    private:
	list<Termo> termos;
    public:
	Polinomio(int);
	int adicionaTermo(Termo);
	void mostraPolinomio();
	float calculaPolinomio(float);
};

