package ex6a;

import java.util.ArrayList;

public class Ex6a {

    public static void main(String[] args) {
        
        ArrayList<Integer> v1 = new ArrayList();
        ArrayList<Integer> v2 = new ArrayList();
        ArrayList<Integer> v;
        
        v1.add(1);
        v1.add(2);
        v1.add(3);
        v2.add(3);
        v2.add(4);
        v2.add(5);
        
        MeusVetores vecs = new MeusVetores(v1, v2);
        
        v = vecs.realizaOperacao(new Uniao());
        System.out.println(v);
        v = vecs.realizaOperacao(new Intersecao());
        System.out.println(v);
        v = vecs.realizaOperacao(new Diferenca());
        System.out.println(v);
        
    }
    
}
