package exercicio4.Exercicio4;

import java.util.ArrayList;


public class Pilha {
    private ArrayList<String> dados;
    private int tamanho;
    private int topo;
    
    Pilha(int pTamanho) {
        dados = new ArrayList();
        tamanho = pTamanho;
        topo = -1;
    }
    
    public void push(String elemento) throws PilhaCheia {
        if (tamanho == topo + 1) {
            throw new PilhaCheia("Pilha Cheia.");
        } else {
            dados.add(elemento);
            topo++;
        }
    }
    
    public String pop() throws PilhaVazia {
        if (topo == -1) {
            throw new PilhaVazia("Pilha Vazia.");
        } else {
            topo--;
            return dados.remove(topo+1);
        }
    }
}
