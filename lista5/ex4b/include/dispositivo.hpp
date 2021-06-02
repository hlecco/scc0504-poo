#ifndef _DISPOSITIVO_H_
#define _DISPOSITIVO_H_

#include <iostream>
#include <string>

using namespace std;

class Dispositivo {
public:
   Dispositivo();
   virtual void Liga_Desliga() = 0;
   string PegaFabricante();
   void DefineFabricante(string);
private:
   string fabricante;
protected:
   bool ligado;
};

class Radio: public Dispositivo {
public:
    Radio();
    void setVolume(int);
    void setEstacao(float);
    float getEstacao();
    void setFM();
    void setAM();
    void Liga_Desliga();
private:
    int volume;
    float estacao;
    bool fm;
};

class Relogio: public Dispositivo {
public:
    Relogio();
    string getHora();
    void setHora(int);
    string getAlarme();
    void setAlarme(int);
    void Liga_Desliga();
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
    void Liga_Desliga();
private:
    float estacao_alarme;
};

#endif
