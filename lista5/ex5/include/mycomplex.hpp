#ifndef _MYCOMPLEX_H_
#define _MYCOMPLEX_H_

#include <iostream>
#include <vector>
#include <math.h>

using namespace std;

class MyComplex {
private:
	double real, imag;
public:
	MyComplex(double real = 0.0, double imag = 0.0);
	MyComplex operator+(const MyComplex &) const;
	MyComplex operator-(const MyComplex &) const;
	MyComplex operator*(const MyComplex &) const;
	double abs() const;
	void print() const;
};

#endif
