#include <iostream>
#include <math.h>
#include <mycomplex.hpp>

using namespace std;

MyComplex::MyComplex(double real, double imag) {
	this->real = real;
	this->imag = imag;
}

MyComplex MyComplex::operator+(const MyComplex &c) const {
	MyComplex result;
	result.real = this->real + c.real;
	result.imag = this->imag + c.imag;
	return result;
}

MyComplex MyComplex::operator-(const MyComplex &c) const {
	MyComplex result;
	result.real = this->real - c.real;
	result.imag = this->imag - c.imag;
	return result;
}

MyComplex MyComplex::operator*(const MyComplex &c) const {
	MyComplex result;
	result.real = this->real * c.real - this->imag * c.imag;
	result.imag = this->real * c.imag + this->imag * c.real;
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
	} else {
		cout << real << " - " << -imag << "i" << endl;
	}
}
