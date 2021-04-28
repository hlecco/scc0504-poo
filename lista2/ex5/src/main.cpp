#include <iostream>
#include <jogo.hpp>
#include <list>

using namespace std;

int main(int arc, char* argv[]) {
  Vilao coringa;
  SuperHeroi batman;
  Superpoder batsoco;
  Superpoder batchute;
  Superpoder gargalhada;
  Superpoder defesa;
  
  coringa.setNome("coringa");
  coringa.setAnosDePrisao(15);
  batman.setNome("batman");
  batman.setNomeVidaReal("bruce wayne");
  
  batsoco.setCategoria(3);
  batsoco.setNome("batsoco");
  batchute.setCategoria(5);
  batchute.setNome("batchute");
  
  gargalhada.setCategoria(4);
  gargalhada.setNome("gargalhada");

  defesa.setCategoria(4);
  defesa.setNome("defesa");
  
  batman.setSuperpoder(batsoco);
  batman.setSuperpoder(batchute);
  batman.setSuperpoder(defesa);
  coringa.setSuperpoder(gargalhada);
  coringa.setSuperpoder(defesa);

  batman.setVida(100);
  coringa.setVida(100);
  
  cout << coringa.getTotalPoder() << endl;
  cout << batman.getTotalPoder() << endl;
  
  cout << coringa.getVida() << endl;
  batman.atacar(20.0, "batchute", &coringa);
  cout << coringa.getVida() << endl;
  
  return 0;
}
