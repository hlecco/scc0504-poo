package ex6b;

import java.util.ArrayList;

public class Ex6b {

    public static void main(String[] args) {
        
        ArrayList<Integer> v1 = new ArrayList();
        ArrayList<Integer> v2 = new ArrayList();
        
        v1.add(1);
        v1.add(2);
        v1.add(3);
        v2.add(3);
        v2.add(4);
        v2.add(5);
        
        Vetor vec = FuncoesMeusVetores.getVetor(v1, v2, "uniao");
        vec.mostra();
        
        vec = FuncoesMeusVetores.getVetor(v1, v2, "intersecao");
        vec.mostra();
        
        vec = FuncoesMeusVetores.getVetor(v1, v2, "diferenca");
        vec.mostra();
        
    }
    
}
