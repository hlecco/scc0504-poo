package Model;

import java.io.Serializable;

import Auxiliar.Consts;
import Auxiliar.Draw;


public class Transition extends Element implements Serializable {
    
    public Transition(String filename) {
        super(filename, Consts.RES, Consts.RES, 1, 0, 0);
        this.priority = 20;
    }
    
    public void end() {
        Draw.getScreen().close();
    }
    
}