package Model;

import Auxiliar.Draw;


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