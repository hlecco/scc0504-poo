#include <iostream>
#include <string>
#include <vector>
#include <ctime>

using namespace std;

class Produto {
protected:
  int codigo;
  double preco;
  string descricao;
  int qtdeEmEstoque;
public:
  Produto(int, double, string, int);
  void setPreco(double);
  void setDescricao(string);
  int getCodigo();
  double getPreco();
  string getDescricao();
  int getQtdeEmEstoque();
  void acrescentaEstoque(int);
  int retiraEstoque(int);
  void imprimeProduto();
};

class ProdutoPerecivel: public Produto {
protected:
  tm dataValidade;
public:
  ProdutoPerecivel(int, double, string, int);
  void setDataValidade(int, int, int); // parâmetros: dia, mês e ano
  tm getDataValidade();
  bool acrescentaEstoque(int);
  int retiraEstoque(int, tm);
};

class ProdutoPerecivelEspecial: public ProdutoPerecivel {
public:
  ProdutoPerecivelEspecial(int, double, string, int);
  void imprimeNota();
};

class ProdutoNaoPerecivel: public Produto {
private:
  int garantia;
public:
  ProdutoNaoPerecivel(int pCodigo, double pPreco, string pDescricao, int pQtdeEmEstoque, int pGarantia) : Produto(pCodigo, pPreco, pDescricao, pQtdeEmEstoque), garantia(pGarantia) {}
  int getGarantia();
};

class Estoque {
private:
  vector<Produto*> listaDeProdutos;
public:
  void cadastraProduto(Produto *);
  Produto *consultaProduto(int);
  Produto *retiraProduto(int); // faz a retirada buscando pelo código
  double custoTotalEstoque();
  void descricaoProdutos();
};
