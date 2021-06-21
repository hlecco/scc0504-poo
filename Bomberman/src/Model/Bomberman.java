package Model;

import java.io.Serializable;

import Auxiliar.Consts;
import Auxiliar.Draw;
import Controler.GameControl;
import Controler.Screen;


public class Bomberman extends Element implements Serializable {

    private int numAllowedBombs;
    private int numSettedBombs;
    private int bombermanPotency;
    private int delay;
    private int numFase;
    private int numLife;
    private boolean allowMoviment;
    private boolean isDead;
    

    public Bomberman(int pNumFase, int pNumLife, int bomberUp, int fireUp,
            int speedUp) {
        super("bomberman_down.png", 1, 2, 8, 0, -1);
        this.numSettedBombs = 0;
        this.numAllowedBombs = bomberUp;
        this.bombermanPotency = fireUp;
        this.priority = 2;
        this.delay = speedUp;
        this.numFase = pNumFase;
        this.numLife = pNumLife;
        this.allowMoviment = true;
        this.addClock(1, 3, this::update_location, null, true);
    }

    /*
    Método usado para que os inimigos saibam a posição do Bomberman na tela.
    */
    private void update_location() {
        Draw.getScreen().setBombermanPosition(this.position);
    }

    private void restrictMovement() {
        this.allowMoviment = false;
        this.addClock(this.delay, 1, null, this::restoreMovement, false);
    }

    private void restoreMovement() {
        this.allowMoviment = true;
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
    }

    private void recharge() {
        numSettedBombs--;
    }

    public void die() {
        if (!isDead) {
            numLife--;
            isDead = true;
            this.sprite.changeSheet("bomberman_dead.png");
            if (numLife > 0) {
                this.addClock(30, 2, this.sprite::cycle, this::resetFase, false);
            } else {
                this.addClock(30, 2, this.sprite::cycle, this::resetGame, false);
            }
        }
    }

    /*
    Os próximos dois métodos fazem o papel de Runnable e são chamados quando o
    Bomberman morre. Um reinicia a tela e o outro reinicia o jogo.
    */
    private void resetFase() {
        Draw.getScreen().nextFase(numFase, numLife, numAllowedBombs,
                bombermanPotency, delay);
    }

    private void resetGame() {
        Draw.getScreen().resetGame();
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
        this.addClock(1, 1, null, this::nextFase, false);
    }
    
    public void nextFase() {
        this.setPosition(1, 1);
        Draw.getScreen().nextFase(++numFase, numLife, numAllowedBombs,
                bombermanPotency, delay);
    }
    
    @Override
    public void Event(int key, GameControl c, Screen t) {
        if (isDead) {
            return;
        }

        switch (key) {
            case Consts.UP:
                this.sprite.changeSheet("bomberman_up.png");
                if (t.isValidPosition(this.getPosicao().offset(0, -1))) {
                    if (this.allowMoviment) {
                        this.moveUp();
                        this.restrictMovement();
                        this.sprite.cycle();
                    }
                }
                break;
            case Consts.DOWN:
                this.sprite.changeSheet("bomberman_down.png");
                if (t.isValidPosition(this.getPosicao().offset(0, 1))) {
                    if (this.allowMoviment) {
                        this.moveDown();
                        this.restrictMovement();
                        this.sprite.cycle();
                    }
                }
                break;
            case Consts.LEFT:
                this.sprite.changeSheet("bomberman_left.png");
                if (t.isValidPosition(this.getPosicao().offset(-1, 0))) {
                    if (this.allowMoviment) {
                        this.moveLeft();
                        this.restrictMovement();
                        this.sprite.cycle();
                    }
                }
                break;
            case Consts.RIGHT:
                this.sprite.changeSheet("bomberman_right.png");
                if (t.isValidPosition(this.getPosicao().offset(1, 0))) {
                    if (this.allowMoviment) {
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
                // pode tirar, né?
            case Consts.DOOR:
                break;
        }
    }

}