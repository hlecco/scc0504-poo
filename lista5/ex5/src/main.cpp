#include <mycomplex.hpp>
#include <iostream>
#include <vector>
#include <stdlib.h>

int main() {
	int n;
	vector<MyComplex*> vec;
	MyComplex *soma = new MyComplex;

	cout << "Digite o tamanho do vetor: ";
	cin >> n;
	srand (time(NULL));
	
	for (int i = 0; i < n; i++) {
		double real = rand() % 100;
		double imag = rand() % 100;
		vec.push_back(new MyComplex(real, imag));
	}

	vector<MyComplex*>::iterator it;
	for (it = vec.begin(); it != vec.end(); it++) {
		*soma = **it + *soma;
	}

	soma->print();
	
	for (it = vec.begin(); it != vec.end(); it++) {
		delete *it;
	}
	
	delete soma;

	return 0;
}
