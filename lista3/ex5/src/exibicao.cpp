#include <iostream>
#include <string>
#include <exibicao.hpp>

using namespace std;

ExibicaoTexto::ExibicaoTexto(string pTexto) {
  texto = pTexto;
}

void ExibicaoTexto::ExibeTexto() {
  cout << texto << " -> sem formatacao" << endl;
}

void ExibicaoTexto::ExibeTexto(int pFormatacao) {
  switch (pFormatacao) {
  case 0:
	cout << texto << " -> itálico" << endl;
	break;
  case 1:
	cout << texto << " -> negrito" << endl;
	break;
  case 2:
	cout << texto << " -> sublinhado" << endl;
	break;
  case 3:
	cout << texto << " -> itálico e negrito" << endl;
	break;
  case 4:
	cout << texto << " -> itálico e sublinhado" << endl;
	break;
  case 5:
	cout << texto << " -> negrito e sublinhado" << endl;
	break;
  case 6:
	cout << texto << " -> itálico, negrito e sublinhado" << endl;
	break;
  default:
	cout << "Formatacao nao encontrada" << endl;
	break;
  }
} 

void ExibicaoTexto::ExibeTexto(string pFonte) {
  cout << texto << " -> com fonte " << pFonte << endl; 
}

void ExibicaoTexto::ExibeTexto(char pCor) {
  switch (pCor) {
  case 'r':
	cout << texto << " -> na cor vermelho." << endl;
	break;
  case 'b':
	cout << texto << " -> na cor azul." << endl;
	break;
  case 'g':
	cout << texto << " -> na cor verde." << endl;
	break;
  case 'y':
	cout << texto << " -> na cor amarelo." << endl;
	break;
  default:
	cout << "Cor nao encontrada." << endl;
	break;
  }
} 

void ExibicaoTexto::ExibeTexto(int pFormatacao, string pFonte) {
  switch (pFormatacao) {
  case 0:
	cout << texto << " -> itálico, com fonte " << pFonte << endl;
	break;
  case 1:
	cout << texto << " -> negrito, com fonte " << pFonte << endl;
	break;
  case 2:
	cout << texto << " -> sublinhado, com fonte " << pFonte << endl;
	break;
  case 3:
	cout << texto << " -> itálico e negrito, com fonte " << pFonte << endl;
	break;
  case 4:
	cout << texto << " -> itálico e sublinhado, com fonte " << pFonte << endl;
	break;
  case 5:
	cout << texto << " -> negrito e sublinhado, com fonte " << pFonte << endl;
	break;
  case 6:
	cout << texto << " -> itálico, negrito e sublinhado, com fonte " << pFonte << endl;
	break;
  default:
	cout << "Formatacao nao encontrada." << endl;
	break;
  }
}

void ExibicaoTexto::ExibeTexto(int pFormatacao, char pCor) {
  switch (pFormatacao) {
  case 0:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> itálico, na cor vermelho." << endl;
	  break;
	case 'b':
	  cout << texto << " -> itálico, na cor azul." << endl;
	  break;
	case 'g':
	  cout << texto << " -> itálico, na cor verde." << endl;
	  break;
	case 'y':
	  cout << texto << " -> itálico, na cor amarelo." << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  case 1:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> negrito, na cor vermelho." << endl;
	  break;
	case 'b':
	  cout << texto << " -> negrito, na cor azul." << endl;
	  break;
	case 'g':
	  cout << texto << " -> negrito, na cor verde." << endl;
	  break;
	case 'y':
	  cout << texto << " -> negrito, na cor amarelo." << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  case 2:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> sublinhado, na cor vermelho." << endl;
	  break;
	case 'b':
	  cout << texto << " -> sublinhado, na cor azul." << endl;
	  break;
	case 'g':
	  cout << texto << " -> sublinhado, na cor verde." << endl;
	  break;
	case 'y':
	  cout << texto << " -> sublinhado, na cor amarelo." << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  case 3:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> itálico e negrito, na cor vermelho." << endl;
	  break;
	case 'b':
	  cout << texto << " -> itálico e negrito, na cor azul." << endl;
	  break;
	case 'g':
	  cout << texto << " -> itálico e negrito, na cor verde." << endl;
	  break;
	case 'y':
	  cout << texto << " -> itálico e negrito, na cor amarelo." << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  case 4:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> itálico e sublinhado, na cor vermelho." << endl;
	  break;
	case 'b':
	  cout << texto << " -> itálico e sublinhado, na cor azul." << endl;
	  break;
	case 'g':
	  cout << texto << " -> itálico e sublinhado, na cor verde." << endl;
	  break;
	case 'y':
	  cout << texto << " -> itálico e sublinhado, na cor amarelo." << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  case 5:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> negrito e sublinhado, na cor vermelho." << endl;
	  break;
	case 'b':
	  cout << texto << " -> negrito e sublinhado, na cor azul." << endl;
	  break;
	case 'g':
	  cout << texto << " -> negrito e sublinhado, na cor verde." << endl;
	  break;
	case 'y':
	  cout << texto << " -> negrito e sublinhado, na cor amarelo." << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  case 6:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> itálico, negrito e sublinhado, na cor vermelho." << endl;
	  break;
	case 'b':
	  cout << texto << " -> itálico, negrito e sublinhado, na cor azul." << endl;
	  break;
	case 'g':
	  cout << texto << " -> itálico, negrito e sublinhado, na cor verde." << endl;
	  break;
	case 'y':
	  cout << texto << " -> itálico, negrito e sublinhado, na cor amarelo." << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  default:
	cout << "Formatacao nao encontrada." << endl;
	break;
  }
}

void ExibicaoTexto::ExibeTexto(string pFonte, char pCor) {
  switch (pCor) {
  case 'r':
	cout << texto << " -> com fonte " << pFonte << "na cor vermelho." << endl;
	break;
  case 'b':
	cout << texto << " -> com fonte " << pFonte << "na cor azul." << endl;
	break;
  case 'g':
	cout << texto << " -> com fonte " << pFonte << "na cor verde." << endl;
	break;
  case 'y':
	cout << texto << " -> com fonte " << pFonte << "na cor amarelo." << endl;
	break;
  default:
	cout << "Cor nao encontrada." << endl;
	break;
  }
}

void ExibicaoTexto::ExibeTexto(int pFormatacao, string pFonte, char pCor) {
  switch (pFormatacao) {
  case 0:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> itálico, na cor vermelho, com fonte " << pFonte << endl;
	  break;
	case 'b':
	  cout << texto << " -> itálico, na cor azul, com fonte " << pFonte << endl;
	  break;
	case 'g':
	  cout << texto << " -> itálico, na cor verde, com fonte " << pFonte << endl;
	  break;
	case 'y':
	  cout << texto << " -> itálico, na cor amarelo, com fonte " << pFonte << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  case 1:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> negrito, na cor vermelho, com fonte " << pFonte << endl;
	  break;
	case 'b':
	  cout << texto << " -> negrito, na cor azul, com fonte " << pFonte << endl;
	  break;
	case 'g':
	  cout << texto << " -> negrito, na cor verde, com fonte " << pFonte << endl;
	  break;
	case 'y':
	  cout << texto << " -> negrito, na cor amarelo, com fonte " << pFonte << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  case 2:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> sublinhado, na cor vermelho, com fonte " << pFonte << endl;
	  break;
	case 'b':
	  cout << texto << " -> sublinhado, na cor azul, com fonte " << pFonte << endl;
	  break;
	case 'g':
	  cout << texto << " -> sublinhado, na cor verde, com fonte " << pFonte << endl;
	  break;
	case 'y':
	  cout << texto << " -> sublinhado, na cor amarelo, com fonte " << pFonte << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  case 3:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> itálico e negrito, na cor vermelho, com fonte " << pFonte << endl;
	  break;
	case 'b':
	  cout << texto << " -> itálico e negrito, na cor azul, com fonte " << pFonte << endl;
	  break;
	case 'g':
	  cout << texto << " -> itálico e negrito, na cor verde, com fonte " << pFonte << endl;
	  break;
	case 'y':
	  cout << texto << " -> itálico e negrito, na cor amarelo, com fonte " << pFonte << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  case 4:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> itálico e sublinhado, na cor vermelho, com fonte " << pFonte << endl;
	  break;
	case 'b':
	  cout << texto << " -> itálico e sublinhado, na cor azul, com fonte " << pFonte << endl;
	  break;
	case 'g':
	  cout << texto << " -> itálico e sublinhado, na cor verde, com fonte " << pFonte << endl;
	  break;
	case 'y':
	  cout << texto << " -> itálico e sublinhado, na cor amarelo, com fonte " << pFonte << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  case 5:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> negrito e sublinhado, na cor vermelho, com fonte " << pFonte << endl;
	  break;
	case 'b':
	  cout << texto << " -> negrito e sublinhado, na cor azul, com fonte " << pFonte << endl;
	  break;
	case 'g':
	  cout << texto << " -> negrito e sublinhado, na cor verde, com fonte " << pFonte << endl;
	  break;
	case 'y':
	  cout << texto << " -> negrito e sublinhado, na cor amarelo, com fonte " << pFonte << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  case 6:
	switch (pCor) {
	case 'r':
	  cout << texto << " -> itálico, negrito e sublinhado, na cor vermelho, com fonte " << pFonte << endl;
	  break;
	case 'b':
	  cout << texto << " -> itálico, negrito e sublinhado, na cor azul, com fonte " << pFonte << endl;
	  break;
	case 'g':
	  cout << texto << " -> itálico, negrito e sublinhado, na cor verde, com fonte " << pFonte << endl;
	  break;
	case 'y':
	  cout << texto << " -> itálico, negrito e sublinhado, na cor amarelo, com fonte " << pFonte << endl;
	  break;
	default:
	  cout << "Cor nao encontrada." << endl;
	  break;
	}
	break;
  default:
	cout << "Formatacao nao encontrada." << endl;
	break;
  }  
}
