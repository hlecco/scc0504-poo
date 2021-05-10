#ifndef DEVICEHPP
#define DEVICEHPP

#include <string>

class Device {
public:
    Device(std::string);
    void turnOn();
    void turnOff();
    int getStatus();
    virtual void calibrate() = 0;
private:
    std::string name;
    int status;
};

class Printer : public Device {
public:
    Printer(std::string);
    void calibrate();
};

class Mouse : public Device {
public:
    Mouse(std::string);
    void calibrate();
};

#endif
