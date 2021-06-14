package Modelo;

import java.awt.Graphics;
import java.io.Serializable;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Controler.Tela;


public class MuroDestrutivel extends Elemento implements Serializable {
    
    public MuroDestrutivel(int pHidden) {
        super("splitwall.png");
        this.bTransponivel = false;
        this.bDestrutivel = true;
        this.hidden = pHidden;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }
    
}