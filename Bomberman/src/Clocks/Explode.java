package Clocks;

import Model.Bomb;
import Model.SerializableRunnable;

public class Explode implements SerializableRunnable {
    
    private final Bomb bomb;
    
    public Explode(Bomb b) {
        this.bomb = b;
    }

    @Override
    public void run() {
        bomb.explode();
    }
    
}
