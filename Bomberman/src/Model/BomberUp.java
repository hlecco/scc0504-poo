package Model;

import Auxiliar.Draw;


public class BomberUp extends Element {
    
    public BomberUp() {
        super("bomberup.png");
        this.bTransposable = true;
        this.bDestroyable = false;
    }
    
    @Override
    public void touchBomberman(Bomberman h) {
        h.bomberUp();
        Draw.getScreen().removeElement(this);
    }
    
}