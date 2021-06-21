package Model;

import Auxiliar.Draw;


/*
Classe para o objeto SpeedUp, que faz com que a velocidade do Bomberman aumente.
 */
public class SpeedUp extends PowerUp {

    public SpeedUp() {
        super("speedup.png");
    }

    @Override
    public void touchBomberman(Bomberman h) {
        h.speedUp();
        this.remove();
    }

}
