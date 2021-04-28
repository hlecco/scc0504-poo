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

  while (coringa.getVida() > 0 || batman.getVida() > 0) {
	int poderbatman = rand() % (batman.poderes.size());
	double intensidadebatman (double) rand() % 51;
	batman.atacar(intensidadebatman, batman.poderes[poderbatman], &coringa);
	
	int podercoringa = rand() % (coringa.poderes.size());
	double intensidadecoringa (double) rand() % 51;
	coringa.atacar(intensidadecoringa, coringa.poderes[podercoringa], &batman);
  }
  
  return 0;
}
