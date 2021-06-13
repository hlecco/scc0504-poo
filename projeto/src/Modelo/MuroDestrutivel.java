package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class MuroDestrutivel extends Elemento implements Serializable {    
    public MuroDestrutivel(int powerUp) {
        super("splitwall.png");
        this.bTransponivel = false;
        this.bDestrutivel = true;
        this.powerUp = powerUp;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }
    
    public void destroi(Elemento m) {
        Desenhador.getTelaDoJogo().removeElemento(m);
    }
}