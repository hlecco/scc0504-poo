package Modelo;

import Auxiliar.Desenhador;

public class FireUp extends Elemento {
    
    FireUp() {
        super("fireup.png");
        this.bTransponivel = true;
        this.bDestrutivel = false;
        this.hidden = 2;
    }
    
    public void touchHero(Hero h) {
        h.powerUpPotencia();
        Desenhador.getTelaDoJogo().removeElemento(this);
    }
    
}
