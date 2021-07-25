package Controler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Autosave extends Thread {

    private final Screen sc;
    private final int timer;
    private boolean exit;

    Autosave(Screen sc, int timer) {
        this.sc = sc;
        this.timer = timer;
    }

    @Override
    public void run() {
        while (!exit) {
            sc.save();
            try {
                Thread.sleep(timer);
            } catch (InterruptedException ex) {
                Logger.getLogger(Autosave.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void terminate() {
        exit = true;
    }

}
