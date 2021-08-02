#ifndef _MYCLASS_H_
#define _MYCLASS_H_

#include <iostream>
using namespace std;

template <class T>
class No {
private:
	T valor;
	No<T> *esq;
	No<T> *dir;
public:
	No(T);
	void setValor(T);
	T getValor();
	void setEsq(No<T> *);
	void setDir(No<T> *);
	No *getEsq();
	No *getDir();
};


template <class T>
class ABB {
private:
	No<T> *raiz;
public:
	ABB();
	ABB(T);
	void insere(T);
	No<T> *busca(T);
	void imprime();
	No<T> *getMin(No<T> *);
	void remove(T);
	void destroi();
};

template <class T>
No<T>::No(T valor) {
	this->valor = valor;
	this->esq = nullptr;
	this->dir = nullptr;
}

template <class T>
void No<T>::setValor(T valor) {
	this->valor = valor;
}

template <class T>
T No<T>::getValor() {
	return this->valor;
}

template <class T>
void No<T>::setEsq(No<T> *esq) {
	this->esq = esq;
}

template <class T>
void No<T>::setDir(No<T> *dir) {
	this->dir = dir;
}

template <class T>
No<T> *No<T>::getEsq() {
	return this->esq;
}

template <class T>
No<T> *No<T>::getDir() {
	return this->dir;
}

template <class T>
ABB<T>::ABB() {
	raiz = nullptr;
}

template <class T>
ABB<T>::ABB(T valor) {
	this->raiz = new No<T>(valor);
}

template <class T>
No<T> *insereRec(No<T> *atual, T valor) {
	if (atual == nullptr) {
		atual = new No<T>(valor);
	} else {
		if (atual->getValor() > valor) {
			atual->setEsq(insereRec(atual->getEsq(), valor));
		} else {
			atual->setDir(insereRec(atual->getDir(), valor));
		}
	}
	return atual;
}

template <class T>
void ABB<T>::insere(T valor) {
	this->raiz = insereRec(this->raiz, valor);
}

template <class T>
No<T> *buscaRec(No<T> *atual, T valor) {
	if (atual == nullptr) {
		return nullptr;
	} else {
		if (atual->getValor() == valor) {
			return atual;
		} else if (atual->getValor() > valor) {
			return buscaRec(atual->getEsq(), valor);
		} else {
			return buscaRec(atual->getDir(), valor);
		}
	}
}

template <class T>
No<T> *ABB<T>::busca(T valor) {
	return buscaRec(this->raiz, valor);
}

template <class T>
void imprimeRec(No<T> *atual) {
	if (atual != nullptr) {
		imprimeRec(atual->getEsq());
		std::cout << atual->getValor() << std::endl;
		imprimeRec(atual->getDir());
	}
}

template <class T>
void ABB<T>::imprime() {
	if (this->raiz == nullptr) {
		std::cout << "Arvore nao existe" << endl;
	} else {
		std::cout << "Imprimindo a arvore:" << endl;
		imprimeRec(this->raiz);
	}
}

template <class T>
No<T> *ABB<T>::getMin(No<T> *atual) {
	while (atual->getEsq() != nullptr) {
		atual = atual->getEsq();
	}
	return atual;
}

template <class T>
void ABB<T>::remove(T valor) {
	No<T> *pai = nullptr;
	No<T> *atual = raiz;

	// essencialmente estamos fazendo uma busca de novo, mas não podemos
	// usar o algoritmo de busca feito acima, pois também precisamos
	// descobrir quem é o pai do nó que queremos deletar, caso tal nó exista
	while (atual != nullptr && atual->getValor() != valor) {
		pai = atual;
		if (atual->getValor() > valor) {
			atual = atual->getEsq();
		} else {
			atual = atual->getDir();
		}
		// essencialmente atualizamos o pai a cada iteração e verificamos se
		// o elemento que estamos buscando está para a esquerda ou direita
	}

	// se não encontrou o valor, retorna nulo
	if (atual == nullptr) {
		return;
	}

	// Agora iremos analisar três casos: o nó que queremos deletar
	// não possui filhos, possui um único filho e possui dois filhos.
	// Se não possuir filhos, entra no próximo if:
	if (atual->getEsq() == nullptr && atual->getDir() == nullptr) {
		if (atual != raiz) {
			if (pai->getEsq() == atual) {
				pai->setEsq(nullptr);
				free(atual);
			} else {
				pai->setDir(nullptr);
				free(atual);
			}
		} else {
			raiz = nullptr;
		}
	} else if (atual->getEsq() != nullptr && atual->getDir() != nullptr) {
		// essa condição é quando o nó que queremos remover possui dois
		// filhos. primeiro iremos encontrar o menor valor na subárvore da
		// direita do nó atual e em seguida iremos subtituí-lo pelo nó atual
		// para que assim possamos apagá-lo da árvore, já que ao trocarmos
		// caíremos no caso em que estamos em um nó sem filhos.
		No<T> *troca = getMin(atual->getDir());
		T temp = troca->getValor();
		remove(troca->getValor());
		atual->setValor(temp);
	} else {
		// caso em que o nó possui apenas um filho.
		No<T> *filho;
		if (atual->getEsq() != nullptr) {
			filho = atual->getEsq();
		} else {
			filho = atual->getDir();
		}

		if (atual != raiz) {
			if (atual == pai->getEsq()) {
				pai->setEsq(filho);
			} else {
				pai->setDir(filho);
			}
		} else {
			raiz = filho;
		}
	}
}

template <class T>
void destroiRec(No<T> *atual) {
	if (atual != nullptr) {
		destroiRec(atual->getEsq());
		destroiRec(atual->getDir());
		free(atual);
	}
}

template <class T>
void ABB<T>::destroi() {
	if (this->raiz != nullptr) {
		destroiRec(this->raiz);
		this->raiz = nullptr;
	}
}

#endif
