package Modelo;

import java.awt.Graphics;
import java.io.Serializable;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Controler.Tela;


public class MuroIndestrutivel extends Elemento implements Serializable {
    
    public MuroIndestrutivel() {
        super("wall.png");
        this.bTransponivel = false;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }
    
}