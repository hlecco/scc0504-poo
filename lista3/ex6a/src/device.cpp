#include <iostream>
#include <string>
#include <device.hpp>

// Device
Device::Device(std::string n) {
    name = n;
    status = 0;
}
void Device::turnOn() {
    status = 1;
}
void Device::turnOff() {
    status = 0;
}
int Device::getStatus() {
    return status;
}

// Printer
Printer::Printer(std::string n) : Device(n) {}
void Printer::calibrate() {
    std::cout << "Impressora calibrada com sucesso" << std::endl; 
}

// Mouse
Mouse::Mouse(std::string n) : Device(n) {}
void Mouse::calibrate() {
    std::cout << "Mouse calibrado com sucesso" << std::endl; 
}

// Keyboard 
Keyboard::Keyboard(std::string n) : Device(n) {}
void Keyboard::calibrate() {
    std::cout << "Teclado calibrado com sucesso" << std::endl; 
}
