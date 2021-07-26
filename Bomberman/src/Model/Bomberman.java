package Model;

import java.io.Serializable;

import Auxiliar.Consts;
import Auxiliar.Draw;
import Controller.GameControl;
import Controller.Screen;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Bomberman extends Element implements Serializable {

    private int numAllowedBombs;
    private int numSettedBombs;
    private int bombermanPotency;
    private int speed;
    private int numLife;
    private boolean allowMovement;
    private boolean isDead;
    private static Bomberman instance;

    private Bomberman(int pNumLife) {
        super("bomberman_down.png", 1, 2, 8, 0, -1);
        this.numSettedBombs = 0;
        this.numAllowedBombs = 1;
        this.bombermanPotency = 1;
        this.priority = 2;
        this.speed = 1;
        this.numLife = pNumLife;
        this.allowMovement = true;
        this.setObservable();
    }

    private Bomberman() {
        this(3);
    }

    public static Bomberman getInstance() {
        if (instance == null) {
            instance = new Bomberman();
        }
        return instance;
    }

    /*
    Ao andar, o movimento do Bomberman será restringido pelo tempo determinado
    pelo atributo delay.
     */
    private void restrictMovement() {
        this.allowMovement = false;
        this.addClock(5-this.speed, 1, null, this::allowMovement, false);
    }

    public void allowMovement() {
        this.allowMovement = true;
    }

    public void goBack() {
        this.position.goBack();
    }

    public void createBomb(final GameControl c, final Screen t) {
        if (!Draw.getScreen().isValidPosition(position)) {
            return;
        }

        final Bomb bomb = new Bomb();
        numSettedBombs++;

        bomb.setPosition(this.position.getCol(), this.position.getRow());
        bomb.setPotency(this.bombermanPotency);
        bomb.setUp();
        Draw.getScreen().addElement(bomb);

        this.addClock(Consts.BOMB_TIMER, 8, null, this::recharge, false);
        this.setChanged();
    }

    public void recharge() {
        numSettedBombs--;
        this.setChanged();
    }
    
    public int getBombs() {
        return (this.numAllowedBombs - this.numSettedBombs);
    }
    public int getSpeed() {
        return this.speed;
    }
    public int getLife() {
        return this.numLife;
    }
    public int getFire() {
        return this.bombermanPotency;
    }
    
    public static void load(Bomberman b) {
        Bomberman.instance = b;
    }

    public void die() {
        if (!isDead) {
            numLife--;
            isDead = true;
            this.sprite.changeSheet("bomberman_dead.png");
            if (numLife > 0) {
                this.addClock(30, 2, this::spriteCycle, this::resetStage, false);
            } else {
                this.addClock(30, 2, this::spriteCycle, this::resetGame, false);
            }
        }
    }
    
    private void resetStage() {
        this.restore();
        Draw.getScreen().resetStage();
    }
    
    private void resetGame() {
        this.restore();
        Draw.getScreen().resetGame();
    }

    private void restore() {
        this.cleanClocks();
        if (numLife > 0) {
            Bomberman.instance = new Bomberman(numLife);
        } else {
            Bomberman.instance = new Bomberman();
        }
        this.setChanged();
    }

    @Override
    public void touchEnemy() {
        this.die();
    }

    public void bomberUp() {
        if (numAllowedBombs < Consts.MAX_BOMBS) {
            numAllowedBombs++;
        }
        this.setChanged();
    }

    public void fireUp() {
        if (bombermanPotency < Consts.MAX_POWER) {
            bombermanPotency++;
        }
        this.setChanged();
    }

    public void speedUp() {
        this.speed++;
        this.setChanged();
    }

    @Override
    public void touchAnother(Element e) {
        e.touchBomberman(this);
    }

    @Override
    public void touchDoor() {
        this.addClock(1, 1, null, this::nextStage, false);
    }

    public void nextStage() {
        this.setPosition(1, 1);
        Draw.getScreen().nextStage();
    }

    @Override
    public void Event(int key, GameControl c, Screen t) {
        if (isDead) {
            return;
        }

        switch (key) {
            case Consts.UP:
                this.sprite.changeSheet("bomberman_up.png");
                if (t.isValidPosition(this.getPosition().offset(0, -1))) {
                    if (this.allowMovement) {
                        this.moveUp();
                        this.restrictMovement();
                        this.spriteCycle();
                    }
                }
                break;
            case Consts.DOWN:
                this.sprite.changeSheet("bomberman_down.png");
                if (t.isValidPosition(this.getPosition().offset(0, 1))) {
                    if (this.allowMovement) {
                        this.moveDown();
                        this.restrictMovement();
                        this.spriteCycle();
                    }
                }
                break;
            case Consts.LEFT:
                this.sprite.changeSheet("bomberman_left.png");
                if (t.isValidPosition(this.getPosition().offset(-1, 0))) {
                    if (this.allowMovement) {
                        this.moveLeft();
                        this.restrictMovement();
                        this.spriteCycle();
                    }
                }
                break;
            case Consts.RIGHT:
                this.sprite.changeSheet("bomberman_right.png");
                if (t.isValidPosition(this.getPosition().offset(1, 0))) {
                    if (this.allowMovement) {
                        this.moveRight();
                        this.restrictMovement();
                        this.spriteCycle();
                    }
                }
                break;
            case Consts.BOMB:
                if (this.numSettedBombs == this.numAllowedBombs) {
                    break;
                } else {
                    this.createBomb(c, t);
                    break;
                }
        }
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Bomberman.instance = this;
    }

}
