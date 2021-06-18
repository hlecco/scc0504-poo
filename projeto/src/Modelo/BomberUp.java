package Modelo;

import Auxiliar.Desenhador;

public class BomberUp extends Elemento {
    
    public BomberUp() {
        super("bomberup.png");
        this.bTransponivel = true;
        this.bDestrutivel = false;
    }
    
    public void touchHero(Hero h) {
        h.powerUpBomba();
        Desenhador.getTelaDoJogo().removeElemento(this);
    }
}
