package Modelo;

public class Door extends Elemento {
    
    public Door() {
        super("door.png");
        this.bTransponivel = true;
        this.bDestrutivel = false;
        super.setHidden(3);
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
    }
    
}
