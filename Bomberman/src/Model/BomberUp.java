package Model;

import java.io.Serializable;

/*
Classe para o objeto BomberUp, que faz com que o Bomberman consiga lançar
mais de uma bomba de cada vez.
*/
public class BomberUp extends PowerUp implements Serializable {
    
    public BomberUp() {
        super("bomberup.png");
    }
    
    @Override
    public void touchBomberman(Bomberman h) {
        h.bomberUp();
        this.remove();
    }
    
}