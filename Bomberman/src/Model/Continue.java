package Model;

import Controller.SaveAndLoad;

public class Continue extends Selector {

    public Continue(boolean state) {
        super("continue", state);
    }

    public void run() {
        SaveAndLoad.getInstance().load();
    }
}
