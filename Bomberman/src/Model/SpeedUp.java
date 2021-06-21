package Model;

import Auxiliar.Draw;


/*
Classe para o objeto SpeedUp, que faz com que a velocidade do Bomberman aumente.
 */
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
