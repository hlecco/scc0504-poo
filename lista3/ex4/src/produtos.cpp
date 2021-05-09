#include <iostream>
#include <iomanip>
#include <string>
#include <vector>
#include <produtos.hpp>

using namespace std;

Produto::Produto(int pCodigo, double pPreco, string pDescricao, int pQtdeEmEstoque) {
  codigo = pCodigo;
  preco = pPreco;
  descricao = pDescricao;
  qtdeEmEstoque = pQtdeEmEstoque;
}

void Produto::setPreco(double pPreco) {
  preco = pPreco;
}

void Produto::setDescricao(string pDescricao) {
  descricao = pDescricao;
}

int Produto::getCodigo() {
  return codigo;
}

double Produto::getPreco() {
  return preco;
}

string Produto::getDescricao() {
  return descricao;
}

int Produto::getQtdeEmEstoque() {
  return qtdeEmEstoque;
}

void Produto::acrescentaEstoque(int qtdeAcrescentar) {
  qtdeEmEstoque += qtdeAcrescentar;
}

int Produto::retiraEstoque(int qtdeRetirar) {
  if (qtdeRetirar > qtdeEmEstoque) {
	return qtdeEmEstoque;
  }
  return qtdeRetirar;
}

void Produto::imprimeProduto() {
  cout << "Produto " << codigo << ", " << descricao << "custo de R$"
	   << preco << ", quantidade em estoque: " << qtdeEmEstoque << endl;
}

void ProdutoPerecivel::setDataValidade(int pDia, int pMes, int pAno) {
  tm tmp = tm();
  tmp.tm_mday = pDia;
  tmp.tm_mon = pMes - 1;
  tmp.tm_year = pAno - 1900;
  dataValidade = tmp;
}

tm ProdutoPerecivel::getDataValidade() {
  return dataValidade;
}

int ProdutoPerecivel::retiraEstoque(int qtdeRetirar, tm diaCorrente) {
  double sec = difftime(mktime(&dataValidade), mktime(&diaCorrente));
  long days = static_cast<long>(sec / (60 * 60 * 24));
  if (days <= 30) {
	qtdeEmEstoque = 0;
	return 0;
  } else if (qtdeEmEstoque < qtdeRetirar) {
	qtdeEmEstoque = 0;
	return qtdeEmEstoque;
  } else {
	qtdeEmEstoque -= qtdeRetirar;
	return qtdeRetirar;
  }  
}

bool ProdutoPerecivel::acrescentaEstoque(int qtdeAcrescentar) {
  if (qtdeEmEstoque != 0) {
	cout << "Nao foi possivel inserir, pois o estoque nao esta vazio." << endl;
	return false;
  }
  qtdeEmEstoque = qtdeAcrescentar;
  return true;
}

void ProdutoPerecivelEspecial::imprimeNota() {
  tm data = getDataValidade();
  cout << "Codigo do produto: " << codigo << endl
	   << "Descricao: " << descricao << endl
	   << "Quantidade em estoque: " << qtdeEmEstoque << endl
	   << "Data de validade: "
	   << setw(2) << setfill('0') << data.tm_mday
	   << "/" << setw(2) << setfill('0') << 1 + data.tm_mon
	   << "/" << 1900 + data.tm_year << endl;
}

int ProdutoNaoPerecivel::getGarantia() {
  return garantia;
}

void Estoque::cadastraProduto(Produto *p) {
  listaDeProdutos.push_back(p);
}

Produto *Estoque::consultaProduto(int pCodigo) {
  vector<Produto*>::iterator it;

  for (it = listaDeProdutos.begin(); it != listaDeProdutos.end(); it++) {
	if ((*it)->getCodigo() == pCodigo) {
	  return *it;
	}
  }

  cout << "Nao foi possivel encontrar tal produto." << endl;
  
  return NULL;
}

Produto *Estoque::retiraProduto(int pCodigo) {
  for (int i = 0; i < listaDeProdutos.size(); i++) {
	if (listaDeProdutos[i]->getCodigo() == pCodigo) {
	  Produto *retirado = listaDeProdutos[i];
	  listaDeProdutos.erase(listaDeProdutos.begin() + i);
	  return retirado;
	}
  }

  cout << "Produto nao encontrado" << endl;
  return NULL;
}

double Estoque::custoTotalEstoque() {
  vector<Produto*>::iterator it;
  double soma;
  
  for (it = listaDeProdutos.begin(); it != listaDeProdutos.end(); it++) {
	soma += (*it)->getPreco() * (*it)->getQtdeEmEstoque();
  }

  return soma;
}

void Estoque::descricaoProdutos() {
  vector<Produto*>::iterator it;

  cout << "Produtos em Estoque:" << endl;
  for (it = listaDeProdutos.begin(); it != listaDeProdutos.end(); it++) {
	cout << (*it)->getDescricao() << endl;
  }
}

tm converteData(int pDia, int pMes, int pAno) {
  tm tmp = tm();
  tmp.tm_mday = pDia;
  tmp.tm_mon = pMes - 1;
  tmp.tm_year = pAno - 1900;
  return tmp;
}
