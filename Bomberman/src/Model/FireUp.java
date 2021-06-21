package Model;


/*
Classe para o objeto FireUp, que faz com que a potência das bombas aumente.
*/
public class FireUp extends PowerUp {
    
    FireUp() {
        super("fireup.png");
    }
    
    @Override
    public void touchBomberman(Bomberman h) {
        h.fireUp();
        this.remove();
    }
    
}