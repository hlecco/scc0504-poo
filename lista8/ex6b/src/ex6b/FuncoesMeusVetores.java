package ex6b;

import java.util.ArrayList;

public class FuncoesMeusVetores {

    public static Vetor getVetor(ArrayList<Integer> v1, ArrayList<Integer> v2,
            String operacao) {

        if (operacao == "uniao") {
            Uniao vetor = new Uniao();
            vetor.Operacao(v1, v2);
            return vetor;
        } else if (operacao == "intersecao") {
            Intersecao vetor = new Intersecao();
            vetor.Operacao(v1, v2);
            return vetor;
        } else {
            Diferenca vetor = new Diferenca();
            vetor.Operacao(v1, v2);
            return vetor;
        }

    }

}
