#ifndef _MYCOMPLEX_H_
#define _MYCOMPLEX_H_

#include <iostream>
#include <math.h>

using namespace std;

class MyComplex {
private:
	double real, imag;
public:
	MyComplex(double real = 0.0, double imag = 0.0);
	MyComplex sum(const MyComplex &) const;
	MyComplex sub(const MyComplex &) const;
	MyComplex mul(const MyComplex &) const;
	double abs() const;
	void print() const;
};

#endif
