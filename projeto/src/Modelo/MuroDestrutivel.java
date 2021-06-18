package Modelo;

import java.awt.Graphics;
import java.io.Serializable;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Controler.Tela;


public class MuroDestrutivel extends Elemento implements Serializable {
    private int hidden;
    
    public MuroDestrutivel(int pHidden) {
        super("splitwall.png");
        this.bTransponivel = false;
        this.bDestrutivel = true;
        this.hidden = pHidden;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }
    
    public void touchFire() {
        Desenhador.getTelaDoJogo().removeElemento(this);
        Elemento hidden_element = null;
        switch (hidden) {
            case 0:
                break;
            case 1:
                hidden_element = new BomberUp();
                break;
            case 2:
                hidden_element = new FireUp();
                break;
            case 3:
                hidden_element = new Door();
                break;
            case 4:
                hidden_element = new SpeedUp();
        }
        if (hidden_element != null) {
            hidden_element.setPosicao(this.getPosicao().getColuna(), this.getPosicao().getLinha());
            Desenhador.getTelaDoJogo().addElemento(hidden_element);
        }
    }
}