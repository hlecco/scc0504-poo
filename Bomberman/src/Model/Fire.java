package Model;

import java.util.ArrayList;

import Auxiliar.Consts;
import Auxiliar.Draw;
import Auxiliar.Position;
import Controler.Screen;
import java.io.Serializable;

public class Fire extends Element implements Serializable {

    private int potency;
    private int direction;

    public Fire() {
        super("explosao.png", 1, 1, 6, 0, 0);
        this.potency = 1;
        this.direction = 0;
        this.bMortal = true;
        this.defeats = true;
        this.vanish();
    }

    public void setPotency(int p) {
        if (p > -1) {
            this.potency = p;
        }
    }

    public void setDirection(int d) {
        this.direction = d;
    }

    /*
    Cria outro fogo na direção determinada com potência menor.
     */
    public void propagate() {
        Screen t = Draw.getScreen();

        if (potency > 1) {
            Position offset = this.getPosition().offset(0, 0);
            switch (this.direction) {
                case Consts.UP:
                    offset.moveUp();
                    break;
                case Consts.DOWN:
                    offset.moveDown();
                    break;
                case Consts.LEFT:
                    offset.moveLeft();
                    break;
                case Consts.RIGHT:
                    offset.moveRight();
                    break;
            }

            if (t.isValidPosition(offset)) {
                Fire newFire = new Fire();
                newFire.setPotency(this.potency - 1);
                newFire.setDirection(this.direction);
                newFire.setPosition(offset.getCol(), offset.getRow());
                this.addClock(1, 1, null, newFire::propagate, false);
                t.addElement(newFire);
            }

            ArrayList<Element> elements = (ArrayList<Element>) t.searchElement(offset).clone();
            for (Element e : elements) {
                e.touchFire();
            }
        }
    }

    public void vanish() {
        clocks.add(new Clock(6, 2, this.sprite::cycle, this::remove, false));
        clocks.add(new Clock(3, 2, null, this::turnHarmless, false));
    }

    /*
    Faz com que o fogo não mate o Bomberman quando ele estiver "menor" na tela.
     */
    public void turnHarmless() {
        this.bMortal = false;
    }

    @Override
    public void touchAnother(Element e) {
        e.touchFire();
        if (this.bMortal) {
            e.touchEnemy();
        }
    }

}
