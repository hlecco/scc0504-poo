package Model;

import java.io.Serializable;


public abstract class Selector extends Element implements Serializable, Runnable {
    private boolean state;

    public Selector(String filename, boolean state) {
        super(filename, 4, 1, 2, -2, 0);
        this.priority = 10;
        this.bMortal = true;
        this.bTransposable = true;
        this.state = state;
        this.addClock(10, 1, this::setFrame, null, true);
    }
    
    public void setFrame() {
        if (this.state) {
            this.sprite.setFrame(1);
        }
        else {
            this.sprite.setFrame(0);
        }
    }

    public void cycle() {
        state = state ? false : true;
    }
    
    public boolean isSelected() {
        return state;
    }    
    
}
