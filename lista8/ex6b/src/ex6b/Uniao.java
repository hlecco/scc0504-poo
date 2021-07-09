package ex6b;

import java.util.ArrayList;

public class Uniao implements Vetor {
    
    private ArrayList<Integer> vec;

    @Override
    public void mostra() {
        System.out.println(this.vec);
    }
    
    public void Operacao(ArrayList<Integer> v1, ArrayList<Integer> v2) {
        this.vec = new ArrayList();
        
        for (int x : v1) {
            this.vec.add(x);
        }
        
        for (int y : v2) {
            if (!this.vec.contains(y)) {
                this.vec.add(y);
            }
        }
        
    }

}
