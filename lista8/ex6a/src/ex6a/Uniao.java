package ex6a;

import java.util.ArrayList;

public class Uniao implements FuncoesMeusVetores {

    @Override
    public ArrayList Operacao(MeusVetores vecs) {
        ArrayList<Integer> uniao = new ArrayList();
        
        for (int x : vecs.conjuntoUm) {
            uniao.add(x);
        }
        
        for (int y : vecs.conjuntoDois) {
            if (!uniao.contains(y)) {
                uniao.add(y);
            }
        }
        
        return uniao;
    }

}
