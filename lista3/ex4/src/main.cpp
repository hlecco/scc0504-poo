#include <iostream>
#include <string>
#include <vector>
#include <produtos.hpp>

using namespace std;

int main(int argc, char* argv[]) {
  Produto arroz(0, 10.50, "arroz", 20);
  Produto feijao(1, 6.75, "feijao", 30);
  ProdutoPerecivel requeijao(2, 9.99, "requeijao", 10);
  requeijao.setDataValidade(01, 01, 2022);
  ProdutoPerecivel maionese(3, 6.25, "maionese", 9);
  maionese.setDataValidade(22, 01, 2022);
  ProdutoPerecivelEspecial abacaxi(4, 10.0, "abacaxi", 200);
  abacaxi.setDataValidade(1, 6, 2021);
  ProdutoPerecivelEspecial melancia(5, 19.85, "melancia", 400);
  melancia.setDataValidade(1, 8, 2021);
  ProdutoNaoPerecivel lampada(6, 14.23, "lampada", 50, 20);
  ProdutoNaoPerecivel vassoura(7, 15.32, "vassoura", 10, 30);

  melancia.imprimeNota();
  melancia.acrescentaEstoque(40); // nao deve retirar, pois estoque diferente de 0
  time_t t = time(0); // data de hoje no formato time_t
  melancia.retiraEstoque(400, *gmtime(&t)); // a função gmtime converte time_t para tm, que é o formato que estamos usando
  melancia.imprimeNota(); // imprime com estoque 0
  melancia.acrescentaEstoque(40);
  melancia.imprimeNota(); // imprime com estoque 40
  melancia.setDataValidade(31, 5, 2021);
  melancia.imprimeNota(); // imprime com a data de validade alterada
  melancia.retiraEstoque(0, *gmtime(&t)); // deve zerar o estoque por conta da validade
  melancia.imprimeNota();
  
  Estoque e;
  e.cadastraProduto(&arroz);
  e.cadastraProduto(&feijao);
  e.cadastraProduto(&requeijao);
  e.cadastraProduto(&maionese);
  e.cadastraProduto(&abacaxi);
  e.cadastraProduto(&melancia);
  e.cadastraProduto(&lampada);
  e.cadastraProduto(&vassoura);

  cout << e.consultaProduto(3)->getDescricao() << endl;
  cout << "Custo total em estoque: " << e.custoTotalEstoque() << endl;
  e.descricaoProdutos();
  e.retiraProduto(1);
  e.retiraProduto(3);
  e.retiraProduto(5);
  e.retiraProduto(7);
  e.descricaoProdutos();
  
  return 0;
}
