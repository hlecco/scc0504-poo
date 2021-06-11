package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class MuroDestrutivel extends Elemento implements Serializable {    
    public MuroDestrutivel(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }    
}