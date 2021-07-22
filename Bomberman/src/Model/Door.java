package Model;

import java.io.Serializable;

public class Door extends Element implements Serializable {

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
