#ifndef _DISPOSITIVO_
#define _DISPOSITIVO_

#include <iostream>
#include <string>

using namespace std;

class Radio {
public:
    Radio();
    void setVolume(int);
    void setEstacao(float);
    float getEstacao();
    void setFM();
    void setAM();
private:
    int volume;
    float estacao;
    bool fm;
};

class Relogio {
public:
    Relogio();
    string getHora();
    void setHora(int);
    string getAlarme();
    void setAlarme(int);
private:
    int hora;
    int alarme;
protected:
    string hour_to_string(int);
};

class RadioRelogio: public Relogio, public Radio {
public:
    RadioRelogio();
    void setAlarme(int, float);
    string getAlarme();
private:
    float estacao_alarme;
};

#endif
