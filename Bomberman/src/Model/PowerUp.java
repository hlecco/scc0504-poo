package Model;

import java.io.Serializable;


/*
Classe para o objeto FireUp, que faz com que a potência das bombas aumente.
 */
public class PowerUp extends Element implements Serializable {

    PowerUp(String filename) {
        super(filename);
        this.bTransposable = true;
        this.bDestroyable = false;
    }

    @Override
    public void touchFire() {
        this.remove();
    }

}
