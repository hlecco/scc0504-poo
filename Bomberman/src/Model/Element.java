package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Auxiliar.Consts;
import Auxiliar.Draw;
import Auxiliar.Position;
import Controler.GameControl;
import Controler.Screen;

public abstract class Element implements Serializable {

    protected Screen screen;
    transient protected Sprite sprite;
    protected Position position;
    protected boolean bTransposable; // Pode passar por cima?
    protected boolean bMortal; // Se encostar, morre?
    protected boolean bDestroyable; // Destrutível com fogo
    transient ArrayList<Clock> clocks;
    int priority; // Objetos com maior prioridade são desenhados antes. Default = 0.
    protected boolean defeats; // Mata inimigos?

    protected Element(String sNomeImagePNG) {
        this(sNomeImagePNG, 1, 1, 1, 0, 0);
    }

    protected Element(String sNomeImagePNG, int nFrames) {
        this(sNomeImagePNG, 1, 1, nFrames, 0, 0);
    }

    protected Element(String sNomeImagePNG, int hSize, int vSize, int nFrames,
            int hOffset, int vOffset) {
        this.position = new Position(1, 1);
        this.bTransposable = true;
        this.bMortal = false;
        this.clocks = new ArrayList<Clock>();
        this.sprite = new Sprite(sNomeImagePNG, hSize, vSize, nFrames, hOffset, vOffset);
        this.priority = 0;
        this.defeats = false;
    }

    public int getPriority() {
        return this.priority;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isbTransposable() {
        return bTransposable;
    }

    public void setbTransposable(boolean bTransposable) {
        this.bTransposable = bTransposable;
    }

    public boolean isbDestroyable() {
        return bDestroyable;
    }

    public boolean isbDefeats() {
        return this.defeats;
    }

    public boolean isbMortal() {
        return this.bMortal;
    }

    public void setbDestroyable(boolean pDest) {
        this.bDestroyable = pDest;
    }

    public boolean setPosition(int coluna, int linha) {
        boolean test = position.setPosition(coluna, linha);
        Draw.getScreen().moveElement(this);
        for (Element e : Draw.getScreen().searchElement(this.getPosition())) {
            this.touchAnother(e);
        }
        return test;
    }

    public boolean moveUp() {
        boolean test = this.position.moveUp();
        Draw.getScreen().moveElement(this);
        for (Element e : Draw.getScreen().searchElement(this.getPosition())) {
            this.touchAnother(e);
            if (e.isbDefeats()) {
                this.touchFire();
            }
            if (e.isbMortal()) {
                this.touchEnemy();
            }
        }
        return test;
    }

    public boolean moveDown() {
        boolean test = this.position.moveDown();
        Draw.getScreen().moveElement(this);
        for (Element e : Draw.getScreen().searchElement(this.getPosition())) {
            this.touchAnother(e);
            if (e.isbDefeats()) {
                this.touchFire();
            }
            if (e.isbMortal()) {
                this.touchEnemy();
            }
        }
        return test;
    }

    public boolean moveRight() {
        boolean test = this.position.moveRight();
        Draw.getScreen().moveElement(this);
        for (Element e : Draw.getScreen().searchElement(this.getPosition())) {
            this.touchAnother(e);
            if (e.isbDefeats()) {
                this.touchFire();
            }
            if (e.isbMortal()) {
                this.touchEnemy();
            }
        }
        return test;
    }

    public boolean moveLeft() {
        boolean test = this.position.moveLeft();
        Draw.getScreen().moveElement(this);
        for (Element e : Draw.getScreen().searchElement(this.getPosition())) {
            this.touchAnother(e);
            if (e.isbDefeats()) {
                this.touchFire();
            }
            if (e.isbMortal()) {
                this.touchEnemy();
            }
        }
        return test;
    }

    /*
    Métodos que movem verificando a validade da posição.
     */
    public boolean moveUpCheck() {
        if (Draw.getScreen().isValidPosition(this.getPosition().offset(0, -1))) {
            return this.moveUp();
        }
        return false;
    }

    public boolean moveDownCheck() {
        if (Draw.getScreen().isValidPosition(this.getPosition().offset(0, 1))) {
            return this.moveDown();
        }
        return false;
    }

    public boolean moveLeftCheck() {
        if (Draw.getScreen().isValidPosition(this.getPosition().offset(-1, 0))) {
            return this.moveLeft();
        }
        return false;
    }

    public boolean moveRightCheck() {
        if (Draw.getScreen().isValidPosition(this.getPosition().offset(1, 0))) {
            return this.moveRight();
        }
        return false;
    }

    /*
    Método que move sem verificar a validade.
     */
    public boolean moveDirection(int direction) {
        switch (direction) {
            case Consts.UP:
                return this.moveUp();
            case Consts.DOWN:
                return this.moveDown();
            case Consts.LEFT:
                return this.moveLeft();
            case Consts.RIGHT:
                return this.moveRight();
        }
        return false;
    }

    public boolean moveDirectionCheck(int direction) {
        switch (direction) {
            case Consts.UP:
                return this.moveUpCheck();
            case Consts.DOWN:
                return this.moveDownCheck();
            case Consts.LEFT:
                return this.moveLeftCheck();
            case Consts.RIGHT:
                return this.moveRightCheck();
        }
        return false;
    }

    public void addClock(int duration, int speed, Runnable onStep,
            Runnable onEnd, boolean restart) {
        clocks.add(new Clock(duration, speed, onStep, onEnd, restart));
    }
    
    public void cleanClocks() {
        for (Clock c : (ArrayList<Clock>) this.clocks.clone()) {
                clocks.remove(c);
        }
    }

    public void selfDraw() {
        this.sprite.draw(position.getCol(), position.getRow());
    }

    /*
    Método que chama o método step de todos os clocks do elemento.
     */
    public void step() {
        for (Clock c : (ArrayList<Clock>) this.clocks.clone()) {
            if (c.step()) {
                clocks.remove(c);
            }
        }
    }

    public void remove() {
        Draw.getScreen().removeElement(this);
    }

    /*
    Método que determina o que acontece com o elemento quando toca a porta.
     */
    protected void touchDoor() {
    }

    /*
    Método que determina o que acontece com o elemento quando toca o fogo.
     */
    protected void touchFire() {
    }

    /*
    Método que determina o que acontece com o elemento quando toca o Bomberman.
     */
    protected void touchBomberman(Bomberman h) {
    }

    /*
    Método que determina o que acontece com o elemento quando toca um inimigo.
     */
    protected void touchEnemy() {
    }

    /*
    Método que determina o que acontece com o elemento passado por parâmetro
    quando este objeto o toca.
     */
    protected void touchAnother(Element e) {
    }

    public void Event(int key, GameControl c, Screen t) {
    }

}
