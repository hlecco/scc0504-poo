package ex3;

public class ArvoreBinaria<T extends Comparable> {

    private No<T> raiz;

    ArvoreBinaria() {
        raiz = null;
    }

    ArvoreBinaria(T valor) {
        raiz = new No<>(valor, null, null);
    }

    private No<T> insereRec(No<T> atual, T valor) {

        if (atual == null) {
            atual = new No<>(valor, null, null);
        } else {
            if (atual.getValor().compareTo(valor) > 0) {
                atual.setEsq(insereRec(atual.getEsq(), valor));
            } else {
                atual.setDir(insereRec(atual.getDir(), valor));
            }
        }

        return atual;
    }

    public void insere(T valor) {
        raiz = insereRec(raiz, valor);
    }

    private No<T> buscaRec(No<T> atual, T valor) {
        if (atual == null) {
            return null;
        } else {
            if (atual.getValor().compareTo(valor) == 0) {
                return atual;
            } else if (atual.getValor().compareTo(valor) > 0) {
                return buscaRec(atual.getEsq(), valor);
            } else {
                return buscaRec(atual.getDir(), valor);
            }
        }
    }

    public No<T> busca(T valor) {
        return buscaRec(raiz, valor);
    }

    private void imprimeRec(No<T> atual) {
        if (atual != null) {
            imprimeRec(atual.getEsq());
            System.out.println(atual.getValor());
            imprimeRec(atual.getDir());
        }
    }

    public void imprime() {
        imprimeRec(raiz);
    }

    private No<T> getMinimo(No<T> atual) {
        while (atual.getEsq() != null) {
            atual = atual.getEsq();
        }
        return atual;
    }

    public void deleta(T valor) {
        No<T> pai = null;
        No<T> atual = raiz;

        // essencialmente estamos fazendo uma busca de novo, mas não podemos
        // usar o algoritmo de busca feito acima, pois também precisamos
        // descobrir quem é o pai do nó que queremos deletar, caso tal nó exista
        while (atual != null && atual.getValor() != valor) {
            pai = atual;
            if (atual.getValor().compareTo(valor) > 0) {
                atual = atual.getEsq();
            } else {
                atual = atual.getDir();
            }
            // essencialmente atualizamos o pai a cada iteração e verificamos se
            // o elemento que estamos buscando está para a esquerda ou direita
        }

        // se não encontrou o valor, retorna nulo
        if (atual == null) {
            return;
        }

        // Agora iremos analisar três casos: o nó que queremos deletar
        // não possui filhos, possui um único filho e possui dois filhos.
        // Se não possuir filhos, entra no próximo if:
        if (atual.getEsq() == null && atual.getDir() == null) {
            if (atual != raiz) {
                if (pai.getEsq() == atual) {
                    pai.setEsq(null);
                } else {
                    pai.setDir(null);
                }
            } else {
                raiz = null;
            }
        } else if (atual.getEsq() != null && atual.getDir() != null) {
            // essa condição é quando o nó que queremos remover possui dois
            // filhos. primeiro iremos encontrar o menor valor na subárvore da
            // direita do nó atual e em seguida iremos subtituí-lo pelo nó atual
            // para que assim possamos apagá-lo da árvore, já que ao trocarmos
            // caíremos no caso em que estamos em um nó sem filhos.
            No<T> troca = getMinimo(atual.getDir());
            T temp = troca.getValor();
            deleta(troca.getValor());
            atual.setValor(temp);
        } else {
            // caso em que o nó possui apenas um filho.
            No<T> filho;
            if (atual.getEsq() != null) {
                filho = atual.getEsq();
            } else {
                filho = atual.getDir();
            }

            if (atual != raiz) {
                if (atual == pai.getEsq()) {
                    pai.setEsq(filho);
                } else {
                    pai.setDir(filho);
                }
            } else {
                raiz = filho;
            }
        }
    }

}
