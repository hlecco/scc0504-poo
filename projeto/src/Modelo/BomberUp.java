package Modelo;

import Auxiliar.Desenhador;

public class BomberUp extends Elemento {
    
    public BomberUp() {
        super("bomberup.png");
        this.bTransponivel = true;
        this.bDestrutivel = false;
        this.hidden = 1;
    }
    
    public void touchHero(Hero h) {
        h.powerUpBomba();
        Desenhador.getTelaDoJogo().removeElemento(this);
    }
}
