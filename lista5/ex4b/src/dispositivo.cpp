#include <dispositivo.hpp>

// Dispositivo
Dispositivo::Dispositivo() {
    fabricante = "null";
    ligado = false;
}

void Dispositivo::DefineFabricante(string f) {
    fabricante = f;
}

string Dispositivo::PegaFabricante() {
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
	cout << "Rádio desligado" << endl;
    }
    else {
	ligado = true;
	cout << "Rádio ligado" << endl;
    }
}



// Relogio
Relogio::Relogio() {
    hora = 0;
    alarme = 0;
}

string Relogio::getHora() {
    return this->hour_to_string(this->hora);
}

string Relogio::getAlarme() {
    return this->hour_to_string(this->alarme);
}

void Relogio::setHora(int h) {
    // Input no formato HHMM
    if (h < 0) {
	cout << "Horário deve ser positivo" << endl;
	return;
    }
    if (h % 100 > 59) {
	cout << "Minuto inválido" << endl;
	return;
    }
    if (h / 100 > 23) {
	cout << "Hora inválida" << endl;
	return;
    }

    hora = h;
}

void Relogio::setAlarme(int h) {
    // Input no formato HHMM
    if (h < 0) {
	cout << "Horário deve ser positivo" << endl;
	return;
    }
    if (h % 100 > 59) {
	cout << "Minuto inválido" << endl;
	return;
    }
    if (h / 100 > 23) {
	cout << "Hora inválida" << endl;
	return;
    }

    alarme = h;
}
    

string Relogio::hour_to_string(int hora_int) {
    int m = hora_int % 100;
    int h = hora_int / 100;
    string horastr = to_string(h)
			  + "h"
			  + to_string(m)
			  + "m";
    return horastr;
}

void Relogio::Liga_Desliga() {
    if (ligado) {
	ligado = false;
	cout << "Relógio desligado" << endl;
    }
    else {
	ligado = true;
	cout << "Relógio ligado" << endl;
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

string RadioRelogio::getAlarme() {
    string estacao_str = to_string(estacao_alarme);
    string hora_str = Relogio::getAlarme();
    string alarme_str = hora_str 
	                     + " "
		             + estacao_str
		             + "Hz";
    return alarme_str;
}

void RadioRelogio::Liga_Desliga() {
    if (Relogio::ligado) {
	Relogio::ligado = false;
	cout << "Rádio-Relógio desligado" << endl;
    }
    else {
	Relogio::ligado = true;
	cout << "Rádio-Relógio ligado" << endl;
    }
}

