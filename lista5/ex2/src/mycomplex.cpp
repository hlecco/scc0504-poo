#include <iostream>
#include <math.h>
#include <mycomplex.hpp>

using namespace std;

MyComplex::MyComplex(double real, double imag) {
	this->real = real;
	this->imag = imag;
}

MyComplex MyComplex::soma(const MyComplex &z) const {
	MyComplex result;
	result.real = this->real + z.real;
	result.imag = this->imag + z.imag;
	return result;
}

MyComplex MyComplex::subtracao(const MyComplex &z) const {
	MyComplex result;
	result.real = this->real - z.real;
	result.imag = this->imag - z.imag;
	return result;
}

MyComplex MyComplex::multiplicacao(const MyComplex &z) const {
	MyComplex result;
	result.real = this->real * z.real - this->imag * z.imag;
	result.imag = this->real * z.imag + this->imag * z.real;
	return result;
}

double MyComplex::modulo() const {
	double result;
	result = this->real * this->real;
	result += this->imag * this->imag;
	return sqrt(result);
}

void MyComplex::print() {
	if (imag >= 0) {
		cout << real << " + " << imag << "i" << endl;
		return;
	} else {
		cout << real << " - " << -imag << "i" << endl;
		return;
	}	
}
