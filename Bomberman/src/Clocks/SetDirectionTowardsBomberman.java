package Clocks;

import Model.Radio;
import Model.SerializableRunnable;

public class SetDirectionTowardsBomberman implements SerializableRunnable {
    
    private final Radio radio;
    
    public SetDirectionTowardsBomberman(Radio r) {
        this.radio = r;
    }

    @Override
    public void run() {
        this.radio.setDirectionTowardsBomberman();
    }
    
}
