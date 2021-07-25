package Clocks;

import Model.Radio;
import Model.SerializableRunnable;

public class Check implements SerializableRunnable {

    private final Radio radio;

    public Check(Radio r) {
        this.radio = r;
    }

    @Override
    public void run() {
        this.radio.check();
    }

}
