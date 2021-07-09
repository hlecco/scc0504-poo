package ex6a;

import java.util.ArrayList;


/*
Calcula conjuntoUm \ conjuntoDois
 */
public class Diferenca implements FuncoesMeusVetores {

    @Override
    public ArrayList Operacao(MeusVetores vecs) {
        ArrayList<Integer> diferenca = new ArrayList();

        for (int x : vecs.conjuntoUm) {
            if (!vecs.conjuntoDois.contains(x)) {
                diferenca.add(x);
            }
        }

        return diferenca;
    }

}
