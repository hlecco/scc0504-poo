package Model;

import java.io.Serializable;
import java.util.Random;

import Auxiliar.Consts;


public class Bat extends Element implements Serializable {
    
    private boolean isDead;
    private boolean isFlying;
    
    public Bat() {
        super("bat_down.png", 3, 2, 4, -1, -1);
        this.priority = 1;
        this.bMortal = true;
        this.bTransposable = true;
        this.stop();
    }

    public void voltaAUltimaPosicao() {
        this.position.goBack();
    }
    
    private void jump() {
        Runnable movement = null;
        switch (this.randomDirection()) {
            case Consts.DOWN:
                movement = this::moveDown;
                this.sprite.changeSheet("bat_down.png");
                break;
            case Consts.UP:
                movement = this::moveUp;
                this.sprite.changeSheet("bat_up.png");
                break;
            case Consts.LEFT:
                movement = this::moveLeft;
                this.sprite.changeSheet("bat_left.png");
                break;
            case Consts.RIGHT:
                movement = this::moveRight;
                this.sprite.changeSheet("bat_right.png");
                break;
        }
        this.isFlying = true;
        this.addClock(8, 6, this.sprite::cycle, this::stop, false);
        this.addClock(4, 12, movement, null, false);
    }
    
    private void stop() {
        this.sprite.changeSheet("bat_idle.png");
        this.isFlying = false;
        this.addClock(16, 6, this.sprite::cycle, this::jump, false);
    }
    
    public void die() {
        this.remove();
    }
    
    @Override
    public void touchFire() {
        if (!this.isFlying) {
            this.die();
        }
    }
    
    @Override
    public void touchAnother(Element e) {
        e.touchEnemy();
    }
    
    private int randomDirection() {
        Random ran = new Random();
        switch (ran.nextInt(4)) {
            case 0:
                return Consts.UP;
            case 1:
                return Consts.DOWN;
            case 2:
                return Consts.LEFT;
            case 3:
                return Consts.RIGHT;
        }
        return Consts.DOWN;
    }
    
}