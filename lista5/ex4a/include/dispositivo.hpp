#ifndef DISPOSITIVO
#define DISPOSITIVO

#include <string>

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
    std::string getHora();
    void setHora(int);
    std::string getAlarme();
    void setAlarme(int);
private:
    int hora;
    int alarme;
protected:
    std::string hour_to_string(int);
};

class RadioRelogio: public Relogio, public Radio {
public:
    RadioRelogio();
    void setAlarme(int, float);
    std::string getAlarme();
private:
    float estacao_alarme;
};


#endif
