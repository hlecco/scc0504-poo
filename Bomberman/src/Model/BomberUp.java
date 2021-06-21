package Model;

import Auxiliar.Draw;

/*
Classe para o objeto BomberUp, que faz com que o Bomberman consiga lançar
mais de uma bomba de cada vez.
*/
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