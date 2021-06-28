package Model;

import java.io.Serializable;

import Auxiliar.Consts;
import Auxiliar.Draw;


/*
Classe cujos objetos serão as imagens de transição de fases e a imagem final.
*/
public class Transition extends Element implements Serializable {
    
    public Transition(String filename) {
        super(filename, Consts.RES, Consts.RES, 1, 0, 0);
        this.priority = 20;
    }
    
    /*
    Método que fechará a tela ao chegar na imagem final.
    */
    public void end() {
        Draw.getScreen().close();
    }
    
}