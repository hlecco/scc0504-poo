package Clocks;

import Model.Bomberman;
import Model.SerializableRunnable;

public class RestoreMovement implements SerializableRunnable {

    @Override
    public void run() {
        Bomberman.getInstance().allowMovement();
    }

}
