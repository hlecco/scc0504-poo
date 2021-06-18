package Modelo;

import Auxiliar.Desenhador;

public class SpeedUp extends Elemento {
    
    public SpeedUp() {
        super("speedup.png");
        this.bTransponivel = true;
        this.bDestrutivel = false;
    }
    
    public void touchHero(Hero h) {
        h.speedUp();
        Desenhador.getTelaDoJogo().removeElemento(this);
    }
}

