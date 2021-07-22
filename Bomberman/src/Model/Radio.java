package Model;

import java.util.Random;

import Auxiliar.Consts;
import Auxiliar.Draw;
import Auxiliar.Position;
import Controler.Screen;
import java.io.Serializable;

public class Radio extends Enemy implements Serializable {

    private int direction;

    public Radio() {
        super("radio_down.png", 1, 2, 6, 0, -1);
        this.check();
    }

    /*
    Faz com que persiga o Bomberman.
     */
    public void setDirectionTowardsBomberman() {
        Screen t = Draw.getScreen();
        Position bombermanPosition = t.getBombermanPosition();

        if ((bombermanPosition.getCol() < this.position.getCol())
                & (t.isValidPosition(this.position.offset(-1, 0)))) {
            this.direction = Consts.LEFT;
        } else if ((bombermanPosition.getCol() > this.position.getCol())
                & (t.isValidPosition(this.position.offset(1, 0)))) {
            this.direction = Consts.RIGHT;
        } else if ((bombermanPosition.getRow() < this.position.getRow())
                & (t.isValidPosition(this.position.offset(0, -1)))) {
            this.direction = Consts.UP;
        } else if ((bombermanPosition.getRow() > this.position.getRow())
                & (t.isValidPosition(this.position.offset(0, 1)))) {
            this.direction = Consts.DOWN;
        } else if (t.isValidPosition(this.position.offset(-1, 0))) {
            this.direction = Consts.LEFT;
        } else if (t.isValidPosition(this.position.offset(1, 0))) {
            this.direction = Consts.RIGHT;
        } else if (t.isValidPosition(this.position.offset(0, -1))) {
            this.direction = Consts.UP;
        } else if (t.isValidPosition(this.position.offset(0, 1))) {
            this.direction = Consts.DOWN;
        }

        this.setSpriteSheet();
    }

    private void setDirectionRandom() {
        Random ran = new Random();
        int random_int = ran.nextInt(4);

        switch (random_int) {
            case 0:
                this.direction = Consts.UP;
                break;
            case 1:
                this.direction = Consts.DOWN;
                break;
            case 2:
                this.direction = Consts.LEFT;
                break;
            case 3:
                this.direction = Consts.RIGHT;
                break;
        }

        this.setSpriteSheet();
    }

    public void move() {
        this.moveDirectionCheck(this.direction);
        this.sprite.cycle();
    }

    public void setSpriteSheet() {
        switch (this.direction) {
            case Consts.UP:
                this.sprite.changeSheet("radio_up.png");
                break;
            case Consts.DOWN:
                this.sprite.changeSheet("radio_down.png");
                break;
            case Consts.LEFT:
                this.sprite.changeSheet("radio_left.png");
                break;
            case Consts.RIGHT:
                this.sprite.changeSheet("radio_right.png");
                break;
        }
    }

    /*
    Persegue o Bomberman caso a distância até ele seja "pequena".
     */
    private void check() {
        int distance = this.position.distanceTo(Draw.getScreen().getBombermanPosition());

        if (distance < 8) {
            this.addClock(20, 8, this::move, this::move, false);
            this.addClock(80, 2, this::setDirectionTowardsBomberman,
                    this::setDirectionTowardsBomberman, false);
            this.addClock(80, 2, null, this::check, false);
        } else {
            this.addClock(5, 16, this::move, this::move, false);
            this.addClock(2, 40, this::setDirectionRandom,
                    this::setDirectionRandom, false);
            this.addClock(40, 2, null, this::check, false);
        }
    }

}
