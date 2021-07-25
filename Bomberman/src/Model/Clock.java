package Model;

import java.io.Serializable;
import static java.lang.Integer.max;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Classe responsável por executar funções especificadas por outros objetos após
determinado período de tempo, como por exemplo a bomba explodir e soltar fogo.
 */
public class Clock implements Serializable {

    private final int duration; // ticks até o final do clock
    private final int speed; // frames por tick
    private int tick; // frame atual no tick (começa em zero)
    private int timeLeft; // ticks que faltam até o final
    private final SerializableRunnable onStep; // função a ser executada uma vez a cada tick
    private final SerializableRunnable onEnd; // função a ser executada a cada tick
    private final boolean restart; // começa de novo quando termina

    Clock(int duration, int speed, SerializableRunnable onStep, SerializableRunnable onEnd, boolean restart) {
        this.duration = max(1, duration);
        this.speed = max(1, speed);
        this.onStep = onStep;
        this.onEnd = onEnd;
        this.timeLeft = this.duration;
        this.restart = restart;
        this.tick = this.speed;
    }

    /*
    Método que será executado a cada frame. Retorna true se o clock tiver terminado
    e false se ainda não houver terminado.
     */
    public boolean step() {
        if (this.tick > 0) {
            this.tick--;
            return false;
        }

        this.tick = this.speed;
        try {
            if (this.onStep != null) {
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
        } else {
            timeLeft--;
        }

        return false;
    }

    /*
    Método que executa a função passada ao final do tempo decorrido. É usada
    pelo Bomberman para (re)iniciar uma fase ou reiniciar o jogo, por exemplo.
     */
    public void end() {
        timeLeft = 0;

        try {
            onEnd.run();
        } catch (Exception ex) {
            Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
