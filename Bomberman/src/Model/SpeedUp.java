package Model;

import java.io.Serializable;


/*
Classe para o objeto SpeedUp, que faz com que a velocidade do Bomberman aumente.
 */
public class SpeedUp extends PowerUp implements Serializable {

    public SpeedUp() {
        super("speedup.png");
    }

    @Override
    public void touchBomberman(Bomberman h) {
        h.speedUp();
        this.remove();
    }

}
