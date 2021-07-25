package Model;

import Clocks.SetFrame;
import java.io.Serializable;

public abstract class Selector extends Element implements Serializable, Runnable {

    private boolean state;

    public Selector(String filename, boolean state) {
        super(filename, 4, 1, 2, -2, 0);
        this.priority = 10;
        this.bMortal = true;
        this.bTransposable = true;
        this.state = state;
        SetFrame sF = new SetFrame(this);
        this.addClock(10, 1, sF::run, null, true);
    }

    public void cycle() {
        state = !state;
    }

    public boolean isSelected() {
        return state;
    }

}
