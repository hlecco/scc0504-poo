#include <calc.hpp>
#include <iostream>
#include <string>
#include <ctime>

using namespace std;

void Data::setData() {
  dia = tm_mday;
  mes = tm_month;
  ano = tm_year;
}

void Data::getData() {
  return to_string(dia) + "/" + to_string(mes) + "/" to_string(ano);
}

Resultado Operacao::realizaCalculo(Usuario u, string nomeOp) {
  Data data;
  data.setData();
  
  return setResultado(u, nomeOp, data);
}

void Resultado::imprimeResultado() {
  cout << "Usuario: " << usuario.getNome() << endl << "Operacao: "		\
	   << operacao << endl << "Data: " << dataOperacao.getData() << endl;
}

Usuario::Usuario(string pNome, int pIdade, string pFoto, Data pDataFoto) {
  nome = pNome;
  idade = pIdade;
  foto = pFoto;
  dataFoto = pDataFoto;
}

string Usuario::getNome() {
  return nome;
}

// double Operacao::soma(double op1, double op2) {
//   return op1 + op2;
// }

// double Operacao::subtracao(double op1, double op2) {
//   return op1 - op2;
// }

// double Operacao::produto(double op1, double op2) {
//   return op1 * op2;
// }

// double Operacao::quociente(double op1, double op2) {
//   return op1 / op2;
// }
