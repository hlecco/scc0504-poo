#include <string>

class drivers {
private:
  std::string nome;
  bool status;
public:
  void ligaDispositivo();
  void verificaStatus();
  void executaTeste();
  void alteraNome(std::string);
  void mostraNome();
};

class driverVideo: public drivers {
private:
  int brilhoExibicao;
public:
  void alteraBrilhoDeExibicao(int);
  void mostraBrilho();
};
