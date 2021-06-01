#ifndef MYCOMPLEX_H
#define MYCOMPLEX_H

#include <iostream>
#include <list>
#include <math.h>

using namespace std;

class MyComplex {
private:
	double real, imag;
public:
	MyComplex(double real = 0.0, double imag = 0.0);
	MyComplex soma(const MyComplex &) const;
	MyComplex subtracao(const MyComplex &) const;
	MyComplex multiplicacao(const MyComplex &) const;
	double modulo() const;
	void print();
};

#endif
