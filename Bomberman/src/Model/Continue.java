package Model;

import Controller.SaveAndLoad;

public class Continue extends Selector {

    public Continue(boolean state) {
        super("continue.png", state);
    }

    public void run() {
        SaveAndLoad.getInstance().load();
        return;
    }
}
