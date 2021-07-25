package Clocks;

import Model.Bat;
import Model.SerializableRunnable;

public class Jump implements SerializableRunnable {

    private final Bat bat;

    public Jump(Bat bat) {
        this.bat = bat;
    }

    @Override
    public void run() {
        bat.jump();
    }

}
