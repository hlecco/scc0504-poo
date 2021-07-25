package Clocks;

import Model.Fire;
import Model.SerializableRunnable;

public class TurnHarmless implements SerializableRunnable {

    private final Fire fire;
    
    public TurnHarmless(Fire f) {
        this.fire = f;
    }
    
    @Override
    public void run() {
        this.fire.turnHarmless();
    }
    
}
