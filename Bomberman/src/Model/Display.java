package Model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;



public class Display extends Text implements Serializable, Observer {
    // Displays bomberman status on screen

    @Override
    public void update(Observable o, Object arg) {
        Bomberman temp = (Bomberman) o;
        String fire = Integer.toString(temp.getFire());
        String life = Integer.toString(temp.getLife());
        String bombs = Integer.toString(temp.getBombs());
        String speed = Integer.toString(temp.getSpeed());
        this.write("$b" + bombs + "$f" + fire + "$s" + speed + "$B" + life);
    }

}
