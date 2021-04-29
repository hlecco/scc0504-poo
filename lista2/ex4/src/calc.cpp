#include <calc.hpp>
#include <iostream>
#include <string>
#include <ctime>

using namespace std;

void Data::setData(int pDia, int pMes, int pAno) {
  dia = pDia;
  mes = pMes;
  ano = pAno;
}

string Data::getData() {
  return to_string(dia) + "/" + to_string(mes) + "/" + to_string(ano);
}

void Operacao::realizaCalculo(Usuario u, string nomeOp) {

  if (nomeOp != "soma" || nomeOp != "produto" || nomeOp != "subtracao" || nomeOp != "quociente") {
	cout << "Operacao invalida." << endl;
	return;
  }
  
  Data data;
  time_t now = time(0);
  tm *ltm = localtime(&now);
  
  data.setData(ltm->tm_mday, 1 + ltm->tm_mon, 1900 + ltm->tm_year);

  cout << u.getNome() << nomeOp << data.getData() << endl;
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
