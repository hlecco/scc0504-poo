package Modelo;

import static java.lang.Integer.max;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clock {
    private int speed;
    private int duration;
    private Runnable onStep;
    private Runnable onEnd;
    private boolean restart;

    private int timeLeft;
    private int tick;
    
    Clock(int duration, int speed, Runnable onStep, Runnable onEnd, boolean restart) {
        this.duration = max(1, duration);
        this.speed = max(1,speed);
        this.onStep = onStep;
        this.onEnd = onEnd;
        this.timeLeft = this.duration;
        this.restart = restart;
        this.tick = this.speed;
    }
    
    public boolean step() {
        if (this.tick > 0) {
            this.tick--;
            return false;
        }
        this.tick = this.speed;
        try {
            if (this.onStep != null){
                this.onStep.run();
            }
        } catch (Exception ex) {
            Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (timeLeft < 1) {
            try {
                if (this.onEnd != null) {
                    this.onEnd.run();
                }
            } catch (Exception ex) {
                Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (!this.restart) {
                    return true;
                }
            }
        }
        else {
            timeLeft--;
        }
        return false;
    }
        
    public void end() {
        timeLeft = 0;
        try {
            onEnd.run();
        } catch (Exception ex) {
            Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
