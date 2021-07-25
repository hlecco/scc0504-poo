package Clocks;

import Model.Radio;
import Model.SerializableRunnable;

public class SetDirectionRandom implements SerializableRunnable {
    
    private final Radio radio;
    
    public SetDirectionRandom(Radio r) {
        this.radio = r;
    }

    @Override
    public void run() {
        this.radio.setDirectionRandom();
    }
    
}
