package Model;

import Auxiliar.Draw;
import Controler.Fase;


public class Door extends Element {
    
    public Door() {
        super("door.png");
        this.bTransposable = true;
        this.bDestroyable = false;
    }
    
    @Override
    public void touchBomberman(Bomberman h) {
        h.touchDoor();
    }
 
}