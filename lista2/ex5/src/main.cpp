#include <iostream>
#include <herois.hpp>
#include <list>

using namespace std;

int main(int arc, char* argv[]) {
  Vilao coringa("Coringa", "???", 25);
  SuperHeroi batman("Batman", "Bruce Wayne", 30);
  SuperPoder batsoco(0, "Batsoco");
  SuperPoder batchute(0, "Batchute");
  SuperPoder gargalhada(1, "Gargalhada");
  SuperPoder defesa(2, "Defesa");
  
  coringa.setNome("coringa");
  coringa.setAnosDePrisao(15);
  batman.setNome("batman");
  batman.setNomeVidaReal("bruce wayne");
  
  batman.adicionaSuperPoder(&batsoco);
  batman.adicionaSuperPoder(&batchute);
  batman.adicionaSuperPoder(&defesa);
  coringa.adicionaSuperPoder(&gargalhada);
  coringa.adicionaSuperPoder(&defesa);

  while (coringa.getVida() > 0 || batman.getVida() > 0) {
	int poderbatman = rand() % batman.getTotalPoder();
	double intensidadebatman = rand() % 10;
	batman.atacar(intensidadebatman, batman.getSuperPoder(poderbatman)->getNome(), &coringa);
	
	int podercoringa = rand() % coringa.getTotalPoder();
	double intensidadecoringa =  rand() % 51;
	coringa.atacar(intensidadecoringa, coringa.getSuperPoder(podercoringa)->getNome(), &batman);
  }
  
  return 0;
}
