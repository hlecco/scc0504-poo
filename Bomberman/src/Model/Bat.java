package Model;

import java.io.Serializable;
import java.util.Random;

import Auxiliar.Consts;
import Clocks.Cycle;
import Clocks.Jump;
import Clocks.Stop;

public class Bat extends Enemy implements Serializable {

    private boolean isFlying;

    public Bat() {
        super("bat_down.png", 3, 2, 4, -1, -1);
        this.stop();
    }

    
    @Override
    public void touchFire() {
        if (!this.isFlying) {
            this.die();
        }
    }

    public void goBack() {
        this.position.goBack();
    }

    /*
    Método que faz com que o morcego fique parado por um tempo e em seguida
    voe (usando a função jump).
     */
    public void stop() {
        this.sprite.changeSheet("bat_idle.png");
        this.isFlying = false;
        Cycle c = new Cycle(this.sprite);
        Jump j = new Jump(this);
        this.addClock(16, 6, c::run, j::run, false);
    }

    /*
    Método que faz com que o morcego voe para uma direção aleatória.
     */
    public void jump() {
        SerializableRunnable movement = null;
        switch (this.randomDirection()) {
            case Consts.DOWN:
                movement = this::moveDown;
                this.getSprite().changeSheet("bat_down.png");
                break;
            case Consts.UP:
                movement = this::moveUp;
                this.getSprite().changeSheet("bat_up.png");
                break;
            case Consts.LEFT:
                movement = this::moveLeft;
                this.getSprite().changeSheet("bat_left.png");
                break;
            case Consts.RIGHT:
                movement = this::moveRight;
                this.getSprite().changeSheet("bat_right.png");
                break;
        }
        this.isFlying = true;
        Cycle c = new Cycle(this.sprite);
        Stop s = new Stop(this);
        this.addClock(8, 6, c::run, s::run, false);
        this.addClock(4, 12, movement, null, false);
    }

    public int randomDirection() {
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
