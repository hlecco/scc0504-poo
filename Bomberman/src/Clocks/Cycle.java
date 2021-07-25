package Clocks;

import Model.SerializableRunnable;
import Model.Sprite;

public class Cycle implements SerializableRunnable {

    transient private final Sprite s;

    public Cycle(Sprite s) {
        this.s = s;
    }

    @Override
    public void run() {
        if (s != null) {
            if (s.getFrame() == s.getNFrames() - 1) {
                s.setFrame(0);
            } else {
                s.setFrame(s.getFrame() + 1);
            }
        }
    }

}
