package Model;

import java.io.Serializable;
import java.util.Random;

import Auxiliar.Consts;

public class Enemy extends Element implements Serializable {

    private boolean isDead;

    public Enemy(String filename, int hSize, int vSize, int frames, int hOffset, int vOffset) {
        super(filename, hSize, vSize, frames, hOffset, vOffset);
        this.priority = 1;
        this.isDead = false;
        this.bMortal = true;
        this.bTransposable = true;
    }

    public void die() {
        if (!this.isDead) {
            isDead = true;
            this.remove();
        }
    }

    @Override
    public void touchFire() {
            this.die();
    }

    @Override
    public void touchAnother(Element e) {
        e.touchEnemy();
    }
}