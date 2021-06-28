package Model;

import java.io.Serializable;


public class UndestroyableWall extends Element implements Serializable {
    
    public UndestroyableWall() {
        super("wall.png");
        this.bTransposable = false;
    }

    @Override
    public void selfDraw() {
        super.selfDraw();
    }
    
}