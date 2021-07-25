package Model;

import java.io.Serializable;

import Auxiliar.Consts;
import Auxiliar.Draw;
import Clocks.Cycle;
import Clocks.Recharge;
import Clocks.ResetGame;
import Clocks.NextStage;
import Clocks.ResetStage;
import Clocks.RestoreMovement;
import Clocks.UpdateLocation;
import Controller.GameControl;
import Controller.Screen;

public class Bomberman extends Element implements Serializable {

    private int numAllowedBombs;
    private int numSettedBombs;
    private int bombermanPotency;
    private int delay;
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
        this.delay = 4;
        this.numLife = pNumLife;
        this.allowMovement = true;
        UpdateLocation ul = new UpdateLocation();
        this.addClock(1, 3, ul::run, null, true);
    }

    private Bomberman() {
        this(3);
    }

    public int getNumLife() {
        return this.numLife;
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
        RestoreMovement rm = new RestoreMovement();
        this.addClock(this.delay, 1, null, rm::run, false);
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

        Recharge r = new Recharge();
        this.addClock(Consts.BOMB_TIMER, 8, null, r::run, false);
    }

    public void recharge() {
        numSettedBombs--;
    }
    
    public static void load(Bomberman b) {
        Bomberman.instance = b;
    }

    public void die() {
        if (!isDead) {
            numLife--;
            isDead = true;
            this.sprite.changeSheet("bomberman_dead.png");
            Cycle c = new Cycle(this.sprite);
            if (numLife > 0) {
                this.restore();
                ResetStage rs = new ResetStage();
                this.addClock(30, 2, c::run, rs::run, false);
            } else {
                this.restore();
                ResetGame rg = new ResetGame();
                this.addClock(30, 2, c::run, rg::run, false);
            }
        }
    }

    private void restore() {
        this.cleanClocks();
        if (numLife > 0) {
            Bomberman.instance = new Bomberman(numLife);
        } else {
            Bomberman.instance = new Bomberman();
        }
    }

    @Override
    public void touchEnemy() {
        this.die();
    }

    public void bomberUp() {
        if (numAllowedBombs < Consts.MAX_BOMBS) {
            numAllowedBombs++;
        }
    }

    public void fireUp() {
        if (bombermanPotency < Consts.MAX_POWER) {
            bombermanPotency++;
        }
    }

    public void speedUp() {
        this.delay--;
    }

    @Override
    public void touchAnother(Element e) {
        e.touchBomberman(this);
    }

    @Override
    public void touchDoor() {
        NextStage ns = new NextStage();
        this.addClock(1, 1, null, ns::run, false);
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
                        this.sprite.cycle();
                    }
                }
                break;
            case Consts.DOWN:
                this.sprite.changeSheet("bomberman_down.png");
                if (t.isValidPosition(this.getPosition().offset(0, 1))) {
                    if (this.allowMovement) {
                        this.moveDown();
                        this.restrictMovement();
                        this.sprite.cycle();
                    }
                }
                break;
            case Consts.LEFT:
                this.sprite.changeSheet("bomberman_left.png");
                if (t.isValidPosition(this.getPosition().offset(-1, 0))) {
                    if (this.allowMovement) {
                        this.moveLeft();
                        this.restrictMovement();
                        this.sprite.cycle();
                    }
                }
                break;
            case Consts.RIGHT:
                this.sprite.changeSheet("bomberman_right.png");
                if (t.isValidPosition(this.getPosition().offset(1, 0))) {
                    if (this.allowMovement) {
                        this.moveRight();
                        this.restrictMovement();
                        this.sprite.cycle();
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

}
