#include <iostream>

using namespace std;

enum CodOperacao {
  Soma = 0,
  Subtracao = 1,
  Produto = 2,
  Quociente = 3
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
  Data *dataFoto;
public:
  Usuario(string, int, string, Data *);
  string getNome();
};

class Operacao {
private:
  Usuario *usuario;
  Data *data;
  CodOperacao cod_operacao;
  double op1;
  double op2;
  double soma();
  double subtracao();
  double produto();
  double quociente();
  double realizaCalculo();
public:
  Operacao(Usuario *, Data *, double, double, CodOperacao);
  void mostra();
};
