package Model;

import java.io.Serializable;

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
