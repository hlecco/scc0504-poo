package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class MuroIndestrutivel extends Elemento implements Serializable {
    private int iContaFrames;
    
    public MuroIndestrutivel(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }    
}
