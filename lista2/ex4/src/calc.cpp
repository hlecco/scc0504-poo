#include <iostream>
#include <string>
#include <ctime>
#include <calc.hpp>

using namespace std;

// Data
Data::Data(int pDia, int pMes, int pAno) {
  dia = pDia;
  mes = pMes;
  ano = pAno;
}

string Data::getData() {
  return to_string(dia) + "/" + to_string(mes) + "/" + to_string(ano);
}

// Operacao
double Operacao::realizaCalculo() {
  double resultado;
  switch(cod_operacao) {
      case Soma:
	  resultado = soma();
	  break;
      case Subtracao:
	  resultado = subtracao();
	  break;
      case Produto:
	  resultado = produto();
	  break;
      case Quociente:
	  resultado = quociente();
	  break;
  }
  return resultado;
}

void Operacao::mostra() {
  cout << "Operação realizada por: " << usuario->getNome() << endl
       << "Em: " << data->getData() << endl
       << "--------------------------------\n"
       << "Valor: " << realizaCalculo() << endl;
}

Operacao::Operacao(Usuario *u, Data *d, double x, double y, CodOperacao cod) {
    usuario = u;
    data = d;
    op1 = x;
    op2 = y;
    cod_operacao = cod;
}

double Operacao::soma() {
   return op1 + op2;
}

double Operacao::subtracao() {
  return op1 - op2;
}

double Operacao::produto() {
  return op1 * op2;
}

double Operacao::quociente() {
  return op1 / op2;
}

// Usuario
Usuario::Usuario(string pNome, int pIdade, string pFoto, Data *pDataFoto) {
  nome = pNome;
  idade = pIdade;
  foto = pFoto;
  dataFoto = pDataFoto;
}

string Usuario::getNome() {
  return nome;
}
