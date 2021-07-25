package Clocks;

import Model.Radio;
import Model.SerializableRunnable;

public class Move implements SerializableRunnable {
    
    private final Radio radio;
    
    public Move (Radio r) {
        this.radio = r;
    }
    
    @Override
    public void run() {
        this.radio.move();
    }

}
