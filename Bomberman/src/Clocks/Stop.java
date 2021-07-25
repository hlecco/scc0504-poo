package Clocks;

import Model.Bat;
import Model.SerializableRunnable;

public class Stop implements SerializableRunnable {
    
    private final Bat bat;
    
    public Stop(Bat bat) {
        this.bat = bat;
    }

    @Override
    public void run() {
        bat.stop();
    }
    
}
