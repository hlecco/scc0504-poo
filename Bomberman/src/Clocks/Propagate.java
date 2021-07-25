package Clocks;

import Model.Fire;
import Model.SerializableRunnable;

public class Propagate implements SerializableRunnable {
    
    private final Fire fire;
    
    public Propagate(Fire f) {
        this.fire = f;
        System.out.println(this.fire.getPotency());
    }

    @Override
    public void run() {
        fire.propagate();
    }
    
}
