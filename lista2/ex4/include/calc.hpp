#include <iostream>

using namespace std;

class Data {
private:
  int dia;
  int mes;
  int ano;
public:
  void setData(int, int, int);
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
  double op1;
  double op2;
public:
  // double soma(double, double);
  // double subtracao(double, double);
  // double produto(double, double);
  // double quociente(double, double);
  void realizaCalculo(Usuario, string);
};
