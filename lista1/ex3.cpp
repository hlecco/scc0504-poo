class Polinomio {
public:
  void criaPolinomio(int);
  void adicionaTermo();
  void mostraPolinomio();
  float calculaPolinomio(float);
};

class Termo: class Polinomio {
private:
  float coeficiente;
  int grau;
public:
  void adicionaTermo(float, int);
};
