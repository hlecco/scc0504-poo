#include <abb.hpp>
#include <iostream>

using namespace std;

int main() {
	ABB<int> abb;
	abb.imprime();
	abb.insere(4);
	abb.insere(3);
	abb.insere(5);
	abb.imprime();
	if (abb.busca(2) != nullptr) {
		cout << abb.busca(2)->getValor() << endl;
	} else {
		cout << "Nao encontrado." << endl;
	}
	if (abb.busca(3) != nullptr) {
		cout << abb.busca(3)->getValor() << endl;
	} else {
		cout << "Nao encontrado." << endl;
	}
	abb.remove(3);
	abb.imprime();
	abb.destroi();
	abb.imprime();

	return 0;
}
