package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import Controler.ControleDeJogo;
import Controler.Tela;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;


public class Bat extends Elemento implements Serializable {
    
    private boolean isDead;
    private boolean isFlying;
    
    public Bat() {
        super("bat_down.png", 3, 2, 4, -1, -1);
        this.priority = 1;
        this.bMortal = true;
        this.stop();
    }

    public void voltaAUltimaPosicao() {
        this.pPosicao.volta();
    }
    
    private void jump() {
        this.isFlying = true;
        this.addClock(4, 2, this.sprite::cycle, this::stop, false);
    }
    
    private void stop() {
        this.isFlying = false;
        this.addClock(8, 2, null, this::jump, false);
    }
    
    public void die() {
        this.remove();
    }
    
    public void touchFire() {
        if (!this.isFlying) {
            this.die();
        }
    }
    
    public void touchAnother(Elemento e) {
        e.touchEnemy();
    }
}    

