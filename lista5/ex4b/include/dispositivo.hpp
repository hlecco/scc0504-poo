#ifndef _DISPOSITIVO_H_
#define _DISPOSITIVO_H_

#include <iostream>
#include <string>

class Dispositivo {
public:
	Dispositivo();
	virtual void Liga_Desliga() = 0;
	std::string PegaFabricante();
	void DefineFabricante(std::string);
private:
	std::string fabricante;
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
	std::string getHora();
    void setHora(int);
	std::string getAlarme();
    void setAlarme(int);
    void Liga_Desliga();
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
    void Liga_Desliga();
private:
    float estacao_alarme;
};

#endif
