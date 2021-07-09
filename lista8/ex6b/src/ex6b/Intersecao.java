package ex6b;

import java.util.ArrayList;

public class Intersecao implements Vetor {

    private ArrayList<Integer> vec;

    @Override
    public void mostra() {
        System.out.println(this.vec);
    }
    
    public void Operacao(ArrayList<Integer> v1, ArrayList<Integer> v2) {
        this.vec = new ArrayList();
        
        for (int x : v1) {
            if (v2.contains(x)) {
                this.vec.add(x);
            }
        }
        
    }

}
