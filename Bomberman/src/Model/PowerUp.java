package Model;

import Auxiliar.Draw;


/*
Classe para o objeto FireUp, que faz com que a potência das bombas aumente.
*/
public class PowerUp extends Element {
    
    PowerUp(String filename) {
        super(filename);
        this.bTransposable = true;
        this.bDestroyable = false;
    }
    
    @Override
    public void touchFire() {
        this.remove();
    }
    
}