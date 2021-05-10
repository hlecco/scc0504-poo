#include <cstdlib>
#include <iostream>
#include <string>
#include <times.hpp>


// TimeFutebol
TimeFutebol::TimeFutebol(std::string nome_time) {
    nome = nome_time;
    gols_marcados = 0;
    gols_sofridos = 0;
    vitorias = 0;
    derrotas = 0;
    empates = 0;
    cartoes_vermelhos = 0;
    cartoes_amarelos= 0;
}
int TimeFutebol::getGolsSofridos() {
    return gols_sofridos;
}
int TimeFutebol::getGolsMarcados() {
    return gols_marcados;
}
int TimeFutebol::getSaldoDeGols() {
    return gols_marcados - gols_sofridos;
}
int TimeFutebol::getCartoesVermelhos() {
    return cartoes_vermelhos;
}
int TimeFutebol::getCartoesAmarelos() {
    return cartoes_amarelos;
}
int TimeFutebol::getVitorias() {
    return vitorias;
}
int TimeFutebol::getDerrotas() {
    return derrotas;
}
int TimeFutebol::getEmpates() {
    return derrotas;
}
int TimeFutebol::getPontos() {
    return 3*vitorias+empates;
}
void TimeFutebol::marcaGol() {
    gols_marcados += 1;
}
void TimeFutebol::sofreGol() {
    gols_sofridos -= 1;
}
void TimeFutebol::vence() {
    vitorias += 1;
}
void TimeFutebol::perde() {
    derrotas += 1;
}
void TimeFutebol::empata() {
    empates += 1;
}
void TimeFutebol::levaCartaoAmarelo() {
    cartoes_amarelos += 1;
}
void TimeFutebol::levaCartaoVermelho() {
    cartoes_vermelhos += 1;
}
std::string TimeFutebol::getNome() {
    return nome;
}

bool operator < (TimeFutebol t1, TimeFutebol t2) {
    if (t1.getPontos() < t2.getPontos()) {
	return true;
    }
    if (t1.getPontos() > t2.getPontos()) {
	return false;
    }

    if (t1.getVitorias() < t2.getVitorias()) {
	return true;
    }
    if (t1.getVitorias() > t2.getVitorias()) {
	return false;
    }

    if (t1.getSaldoDeGols() < t2.getSaldoDeGols()) {
	return true;
    }
    if (t1.getSaldoDeGols() > t2.getSaldoDeGols()) {
	return false;
    }

    if (t1.getGolsMarcados() < t2.getGolsMarcados()) {
	return true;
    }
    if (t1.getGolsMarcados() > t2.getGolsMarcados()) {
	return false;
    }

    if (t1.getCartoesVermelhos() > t2.getCartoesVermelhos()) {
	return true;
    }
    if (t1.getCartoesVermelhos() < t2.getCartoesVermelhos()) {
	return false;
    }

    if (t1.getCartoesAmarelos() > t2.getCartoesAmarelos()) {
	return true;
    }
    if (t1.getCartoesAmarelos() < t2.getCartoesAmarelos()) {
	return false;
    }

    if (std::rand() % 2 == 0) {
	return true;
    }
    return false;
}

bool operator > (TimeFutebol t1, TimeFutebol t2) {
    // não ser menor que é, nesse caso, o mesmo que ser maior que
    // nunca é igual, pois não há empate 
    return (t2 < t1);
}

bool operator == (TimeFutebol t1, TimeFutebol t2) {
    return false;
}


// JogoFutebol
JogoFutebol::JogoFutebol(TimeFutebol *A, TimeFutebol *B) {
    time_A = A;
    time_B = B;
    tempo = 0;
    saldo_gols = 0;
    saldo_cartoes = 0;
    encerrado = false;
}

void JogoFutebol::jogo() {
    std::cout << "Começa o jogo entre " << time_A->getNome()
	      << " e " << time_B->getNome() << std::endl;
    while (!encerrado) {
	passaMinuto();
    }
    tempo++;
}

void JogoFutebol::passaMinuto() {
    if (tempo == 90) {
	encerra();
    }
    if (encerrado) {
	return;
    }
    if (std::rand() % 1000 < PROB_GOL) {
	gol();
    }
    if (std::rand() % 1000 < PROB_CARTAO_A) {
	cartaoAmarelo();
    }
    if (std::rand() % 1000 < PROB_CARTAO_V) {
	cartaoVermelho();
    }
    tempo++;
}

void JogoFutebol::encerra() {
    std::cout << "Jogo encerrado!" << std::endl;
    if (saldo_gols > 0) {
	std::cout << "Vitória de " << time_A->getNome() << std::endl;
	time_A->vence();
	time_B->perde();
    }
    else if (saldo_gols < 0) {
	std::cout << "Vitória de " << time_B->getNome() << std::endl;
	time_A->perde();
	time_B->vence();
    }
    else {
	std::cout << "Empate" << std::endl;
	time_A->empata();
	time_B->empata();
    }
    encerrado = true;
}

void JogoFutebol::gol() {
    // A equipe com menos cartoes tem mais chance de marcar
    if (std::rand() % 1000 < 500+VANTAGEM_CASA-saldo_cartoes*100) {
       time_A->marcaGol();
       saldo_gols++;
       std::cout << tempo << "' " << time_A->getNome()
	    << " marca gol!" << std::endl;
    }
    else {
	time_B->marcaGol();
	saldo_gols--;
	std::cout << tempo << "' " << time_B->getNome()
	<< " marca gol!" << std::endl;
    }
}

void JogoFutebol::cartaoAmarelo() {
    // A equipe com mais cartoes é mais cautelosa
    if (std::rand() % 1000 < 500+VANTAGEM_CASA+saldo_cartoes*100) {
       time_A->levaCartaoAmarelo();
       saldo_cartoes++;
       std::cout << tempo << "' " << time_A->getNome()
	    << " leva cartão amarelo" << std::endl;
    }
    else {
	time_B->levaCartaoAmarelo();
	saldo_cartoes--;
	std::cout << tempo << "' " << time_B->getNome()
	<< " leva cartão amarelo" << std::endl;
    }
}

void JogoFutebol::cartaoVermelho() {
    // A equipe com mais cartoes é mais cautelosa
    if (std::rand() % 1000 < 500+VANTAGEM_CASA+saldo_cartoes*100) {
       time_A->levaCartaoVermelho();
       saldo_cartoes += 2;
       std::cout << tempo << "' " << time_A->getNome()
	    << " leva cartão vermelho" << std::endl;
    }
    else {
	time_B->levaCartaoVermelho();
	saldo_cartoes -= 2;
	std::cout << tempo << "' " << time_B->getNome()
	<< " leva cartão vermelho" << std::endl;
    }
}
