#ifndef TIMESHPP
#define TIMESHPP

#include <string>

// Constantes
#define VANTAGEM_CASA 20
#define PROB_GOL 30
#define PROB_CARTAO_A 10
#define PROB_CARTAO_V 7

class TimeFutebol {
public:
    TimeFutebol(std::string);
    int getGolsSofridos();
    int getGolsMarcados();
    int getSaldoDeGols();
    int getCartoesVermelhos();
    int getCartoesAmarelos();
    int getVitorias();
    int getDerrotas();
    int getEmpates();
    int getPontos();
    void marcaGol();
    void sofreGol();
    void vence();
    void perde();
    void empata();
    void levaCartaoAmarelo();
    void levaCartaoVermelho();
    std::string getNome();
private:
    int gols_marcados;
    int gols_sofridos;
    int vitorias;
    int empates;
    int derrotas;
    int cartoes_vermelhos;
    int cartoes_amarelos;
    std::string nome;
};

bool operator < (TimeFutebol, TimeFutebol);
bool operator > (TimeFutebol, TimeFutebol);
bool operator == (TimeFutebol, TimeFutebol);

class JogoFutebol {
public:
    JogoFutebol(TimeFutebol *, TimeFutebol *);
    void jogo();
    void passaMinuto();
    void encerra();
    void gol();
    void cartaoAmarelo();
    void cartaoVermelho();
    bool encerrado;
private:
    TimeFutebol *time_A;
    TimeFutebol *time_B;
    int saldo_gols;
    int saldo_cartoes;
    int tempo;
};

#endif
