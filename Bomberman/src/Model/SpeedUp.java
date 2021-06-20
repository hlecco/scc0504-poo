package Model;

import Auxiliar.Draw;


public class SpeedUp extends Element {
    
    public SpeedUp() {
        super("speedup.png");
        this.bTransposable = true;
        this.bDestroyable = false;
    }
    
    @Override
    public void touchBomberman(Bomberman h) {
        h.speedUp();
        Draw.getScreen().removeElement(this);
    }
    
}