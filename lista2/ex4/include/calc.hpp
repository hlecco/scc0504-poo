#include <iostream>

using namespace std;

enum CodOperacoes {
  Soma,
  Subtracao,
  Produto,
  Quociente  
};

class Data {
private:
  int dia;
  int mes;
  int ano;
public:
  Data(int, int, int);
  string getData();
};

class Usuario {
private:
  string nome;
  int idade;
  string foto;
  Data dataFoto;
public:
  Usuario(string, int, string, Data);
  string getNome();
};

class Operacao {
private:
  Usuario *usuario;
  Data *data;
  double op1;
  double op2;
  double soma();
  double subtracao();
  double produto();
  double quociente();
public:
  Operacao(*Usuario, *Data, double, double, int);
  void realizaCalculo();
};
