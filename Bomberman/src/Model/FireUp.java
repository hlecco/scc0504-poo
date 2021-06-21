package Model;

import Auxiliar.Draw;


/*
Classe para o objeto FireUp, que faz com que a potência das bombas aumente.
*/
public class FireUp extends Element {
    
    FireUp() {
        super("fireup.png");
        this.bTransposable = true;
        this.bDestroyable = false;
    }
    
    @Override
    public void touchBomberman(Bomberman h) {
        h.fireUp();
        Draw.getScreen().removeElement(this);
    }
    
}