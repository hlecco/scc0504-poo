package Model;

import java.io.Serializable;


/*
Classe para as imagens que contêm o número restante de vidas do Bomberman.
 */
public class Life extends Element implements Serializable {

    public Life(int pNumLife) {
        super("life" + Integer.toString(pNumLife) + ".png");
        this.bTransposable = false;
    }

    @Override
    public void selfDraw() {
        super.selfDraw();
    }

}
