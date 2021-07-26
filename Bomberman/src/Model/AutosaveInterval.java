package Model;

import Auxiliar.Consts;
import Controller.GameControl;
import Controller.SaveAndLoad;
import Controller.Screen;
import java.awt.event.KeyEvent;
import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class AutosaveInterval extends Selector {
    private int interval;
    
    public AutosaveInterval(boolean state) {
        super("autosave", state);
        interval = Consts.TIMER/1000;
        this.selectorText = "autosave " + Integer.toString(this.interval) + "s";
    }
    
    @Override
    public void Event(int key, GameControl c, Screen t) {
        switch (key) {
            case Consts.LEFT:
                this.interval--;
                break;
            case Consts.RIGHT:
                this.interval++;
                break;
            default:
                return;
        }
        this.interval = max(min(999, this.interval), 1);
        this.selectorText = "autosave " + Integer.toString(this.interval) + "s";
        SaveAndLoad.getInstance().setTimer(this.interval*1000);
        this.showText();
    }

    @Override
    public void run() {
    }
}
