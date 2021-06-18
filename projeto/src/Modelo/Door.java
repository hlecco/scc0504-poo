package Modelo;

import Auxiliar.Desenhador;

public class Door extends Elemento {
    
    public Door() {
        super("door.png");
        this.bTransponivel = true;
        this.bDestrutivel = false;
    }
    
    public void touchHero(Hero h) {
        this.addClock(1, 1, null, Desenhador.getTelaDoJogo()::nextStage, false);
    }
    
}
