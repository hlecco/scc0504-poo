package Clocks;

import Model.Selector;
import Model.SerializableRunnable;

public class SetFrame implements SerializableRunnable {

    private final Selector s;
    
    public SetFrame(Selector s) {
        this.s = s;
    }
    
    @Override
    public void run() {
        if (s.isSelected()) {
            s.getSprite().setFrame(1);
        } else {
            s.getSprite().setFrame(0);
        }
    }
        
}
