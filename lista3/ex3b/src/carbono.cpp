#include <carbono.hpp>
#include <string>
#include <iostream>

using namespace std;

Casa::Casa(int pNumAndares, bool pElevador, bool pGaragem) {
  numAndares = pNumAndares;
  elevador = pElevador;
  garagem = pGaragem;
}

double Casa::getPegadaDeCarbono() {
  return 25;
}

Escola::Escola(int pNumAndares, bool pElevador, bool pGaragem) {
  numAndares = pNumAndares;
  elevador = pElevador;
  garagem = pGaragem;
}

double Escola::getPegadaDeCarbono() {
  if (numAndares < 5 && elevador == false) {
	return 50;
  } else if (numAndares < 5 && elevador == true) {
	return 65;
  } else if (numAndares >= 5 && numAndares <= 10) {
	return 80;
  } else {
	return 100;
  }
}

Carro::Carro(string pCor, string pCombustivel, bool pEletrico, bool pHibrido) {
  cor = pCor;
  combustivel = pCombustivel;
  eletrico = pEletrico;
  hibrido = pHibrido;
}

double Carro::getPegadaDeCarbono() {

  if (eletrico) {
	return 10;
  }
  
  if (hibrido) {
	return 15;
  }

  if (combustivel == "alcool") {
	return 20;
  }

  if (combustivel == "gasolina") {
	return 25;
  }

  return 30;
}

Bicicleta::Bicicleta(int pNumMarchas, double pTamanhoAro, bool pEletrica) {
  numMarchas = pNumMarchas;
  tamanhoAro = pTamanhoAro;
  eletrica = pEletrica;
}

double Bicicleta::getPegadaDeCarbono() {
  if (eletrica) {
	return 2;
  } else {
	return 0.5;
  }
}
