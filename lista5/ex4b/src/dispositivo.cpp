#include <iostream>
#include <dispositivo.hpp>

// Dispositivo
Dispositivo::Dispositivo() {
    fabricante = "null";
    ligado = false;
}

void Dispositivo::DefineFabricante(std::string f) {
    fabricante = f;
}

std::string Dispositivo::PegaFabricante() {
    return fabricante;
}

// Radio
Radio::Radio() {
    volume = 0;
    estacao = 0;
    fm = true;
}

void Radio::setVolume(int v) {
    if (v < 0) volume = 0;
    else if (v > 100) volume = 100;
    else volume = v;
}

void Radio::setEstacao(float e) {
    if (e < 0.0) {
	estacao = 0.0;
    }
    else estacao = e;
}

float Radio::getEstacao() {
    return estacao;
}

void Radio::setFM() {
    fm = true;
}

void Radio::setAM() {
    fm = false;
}

void Radio::Liga_Desliga() {
    if (ligado) {
	ligado = false;
	std::cout << "Rádio desligado" << std::endl;
    }
    else {
	ligado = true;
	std::cout << "Rádio ligado" << std::endl;
    }
}



// Relogio
Relogio::Relogio() {
    hora = 0;
    alarme = 0;
}

std::string Relogio::getHora() {
    return this->hour_to_string(this->hora);
}

std::string Relogio::getAlarme() {
    return this->hour_to_string(this->alarme);
}

void Relogio::setHora(int h) {
    // Input no formato HHMM
    if (h < 0) {
	std::cout << "Horário deve ser positivo" << std::endl;
	return;
    }
    if (h % 100 > 59) {
	std::cout << "Minuto inválido" << std::endl;
	return;
    }
    if (h / 100 > 23) {
	std::cout << "Hora inválida" << std::endl;
	return;
    }

    hora = h;
}

void Relogio::setAlarme(int h) {
    // Input no formato HHMM
    if (h < 0) {
	std::cout << "Horário deve ser positivo" << std::endl;
	return;
    }
    if (h % 100 > 59) {
	std::cout << "Minuto inválido" << std::endl;
	return;
    }
    if (h / 100 > 23) {
	std::cout << "Hora inválida" << std::endl;
	return;
    }

    alarme = h;
}
    

std::string Relogio::hour_to_string(int hora_int) {
    int m = hora_int % 100;
    int h = hora_int / 100;
    std::string horastr = std::to_string(h)
			  + "h"
			  + std::to_string(m)
			  + "m";
    return horastr;
}

void Relogio::Liga_Desliga() {
    if (ligado) {
	ligado = false;
	std::cout << "Relógio desligado" << std::endl;
    }
    else {
	ligado = true;
	std::cout << "Relógio ligado" << std::endl;
    }
}



// RadioRelogio

RadioRelogio::RadioRelogio() {
    estacao_alarme = 0.0;
}

void RadioRelogio::setAlarme(int h, float e) {
    Relogio::setAlarme(h);
    if (e > 0) estacao_alarme = e;
}

std::string RadioRelogio::getAlarme() {
    std::string estacao_str = std::to_string(estacao_alarme);
    std::string hora_str = Relogio::getAlarme();
    std::string alarme_str = hora_str 
	                     + " "
		             + estacao_str
		             + "Hz";
    return alarme_str;
}

void RadioRelogio::Liga_Desliga() {
    if (Relogio::ligado) {
	Relogio::ligado = false;
	std::cout << "Rádio-Relógio desligado" << std::endl;
    }
    else {
	Relogio::ligado = true;
	std::cout << "Rádio-Relógio ligado" << std::endl;
    }
}

