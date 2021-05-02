#include <iostream>
#include <string>

using namespace std;

class ExibicaoTexto {
private:
  string texto;
public:
  ExibicaoTexto(string);
  void ExibeTexto();
  void ExibeTexto(int); // parâmetro: um inteiro para imprimir o texto em italico, negrito ou sublinhado. As opções são: 0 para itálico, 1 para negrito, 2 para sublinhado, 3 para itálico e negrito, 4 para itálico e sublinhado, 5 para negrito e sublinhado, 6 para itálico, negrito e sublinhado.
  void ExibeTexto(string); // parâmetro: uma string com o nome da fonte com que o texto deve ser exibido.
  void ExibeTexto(char);  // parâmetro: um char representando a cor com que o texto deve ser exibido. As opções de cores são: 'r' para vermelho; 'b' para azul; 'g' para verde; 'y' para amarelo.
  void ExibeTexto(int, string); // parâmetros: um inteiro para imprimir o texto em itálico, negrito ou sublinhado (ver as opções na segunda função) e uma string com o nome da fonte com que o texto deve ser exibido.
  void ExibeTexto(int, char); // parâmetros: um inteiro para imprimir o texto em itálico, negrito ou sublinhado (ver as opções na segunda função) e um char representando a cor com que o texto deve ser exibido (ver as opções na quarta função).
  void ExibeTexto(string, char); // parâmetros: a string com o nome da fonte com que o texto deve ser exibido e um char representando a cor com que o texto deve ser exibido (ver as opções na quarta função).
  void ExibeTexto(int, string, char); // parâmetros: um inteiro para imprimir o texto em itálico, negrito ou sublinhado (ver as opções na segunda função), a string com o nome da fonte com que o texto deve ser exibido e um char representando a cor com que o texto deve ser exibido (ver as opções na quarta função).
};
