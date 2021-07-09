package ex6a;

import java.util.ArrayList;

public class Intersecao implements FuncoesMeusVetores {

    @Override
    public ArrayList Operacao(MeusVetores vecs) {
        ArrayList<Integer> intersecao = new ArrayList();
        
        for (int x : vecs.conjuntoUm) {
            if (vecs.conjuntoDois.contains(x)) {
                intersecao.add(x);
            }
        }
        
        return intersecao;
    }

}
