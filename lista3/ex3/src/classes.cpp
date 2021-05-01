#include <classes.hpp>
#include <iostream>

Predio::Predio(int pNumAndares, bool pElevador, bool pGaragem) {
  numAndares = pNumAndares;
  elevador = pElevador;
  garagem = pGaragem;
}

double Predio::getPegadaDeCarbono() {
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
  } else if (hibrido) {
	return 15;
  } else if (combustivel == "alcool") {
	return 20;
  } else if (combustivel == "gasolina") {
	return 25;
  }
}

Bicicleta::Bicicleta(int pNumMarchas, double pTamanhoAro, bool pEletrica) {
  numMarchas = pNumMarchas;
  tamanhoAro = pTamanhoAro;
  eletrica = pEletrica;
}

double Bicicleta::getPegadaDeCarbono() {
  if (eletrica) {
	return 5;
  } else {
	return 1;
  }
}
