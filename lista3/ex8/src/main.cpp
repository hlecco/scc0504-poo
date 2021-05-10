#include <iostream>
#include <ctime>
#include <vector>
#include <string>
#include <algorithm>
#include <times.hpp>

int main() {
    std::srand(std::time(nullptr));
    std::vector<TimeFutebol *> times;

    times.push_back(new TimeFutebol("América-MG"));
    times.push_back(new TimeFutebol("América-PR"));
    times.push_back(new TimeFutebol("Atlético-GO"));
    times.push_back(new TimeFutebol("Atlético-MG"));
    times.push_back(new TimeFutebol("Bahia"));
    times.push_back(new TimeFutebol("Bragantino"));
    times.push_back(new TimeFutebol("Bragantino"));
    times.push_back(new TimeFutebol("Ceará"));
    times.push_back(new TimeFutebol("Chapecoense"));
    times.push_back(new TimeFutebol("Corinthians"));
    times.push_back(new TimeFutebol("Cuiabá"));
    times.push_back(new TimeFutebol("Flamengo"));
    times.push_back(new TimeFutebol("Fluminense"));
    times.push_back(new TimeFutebol("Fortaleza"));
    times.push_back(new TimeFutebol("Grêmio"));
    times.push_back(new TimeFutebol("Internacional"));
    times.push_back(new TimeFutebol("Juventude"));
    times.push_back(new TimeFutebol("Palmeiras"));
    times.push_back(new TimeFutebol("Santos"));
    times.push_back(new TimeFutebol("São Paulo"));
    times.push_back(new TimeFutebol("Sport"));

    JogoFutebol *jogo;
    for (int i=0; i<times.size(); i++) {
	for (int j=i+1; j<times.size(); j++) {
	    jogo = new JogoFutebol(times[i], times[j]);
	    jogo->jogo();
	    std::cout << std::endl;
	    delete jogo;
	}
    }

    std::sort(times.begin(), times.end(),
	      [](TimeFutebol *a, TimeFutebol *b) { return *a > *b; });

    std::cout << "Classificação final" << std::endl;
    for (int i=0; i<times.size(); i++) {
	std::cout << times[i]->getNome() << " " << times[i]->getPontos()
	          << " pontos" << std::endl;
	delete times[i];
    }
	    

    return 0;
}
