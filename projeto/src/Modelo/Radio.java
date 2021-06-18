
package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import Controler.Tela;
import java.util.Random;


public class Radio extends Elemento {
    private boolean targeting;
    private int direction;
    
    public Radio() {
        super("radio_down.png", 1, 2, 6, 0, -1);
        this.targeting = false;
        this.priority = 1;
        this.bMortal = true;
        this.bTransponivel = true;
        this.check();
    }
    
    public void print() {
        System.out.println(".");
    }
    public void setDirectionTowardsHero() {
        Tela t = Desenhador.getTelaDoJogo();
        Posicao heroPosition = t.getHeroPosition();
        if ((heroPosition.getColuna() < this.pPosicao.getColuna())
                & (t.ehPosicaoValida(this.pPosicao.offset(-1, 0)))) {
            this.direction = Consts.LEFT;
        }
        else if ((heroPosition.getColuna() > this.pPosicao.getColuna())
                & (t.ehPosicaoValida(this.pPosicao.offset(1, 0)))) {
            this.direction = Consts.RIGHT;
        }
        else if ((heroPosition.getLinha() < this.pPosicao.getLinha())
                & (t.ehPosicaoValida(this.pPosicao.offset(0, -1)))) {
            this.direction = Consts.UP;
        }
        else if ((heroPosition.getLinha() > this.pPosicao.getLinha())
                & (t.ehPosicaoValida(this.pPosicao.offset(0, 1)))) {
            this.direction = Consts.DOWN;
        }
        else if (t.ehPosicaoValida(this.pPosicao.offset(-1, 0))) {
            this.direction = Consts.LEFT;
        }
        else if (t.ehPosicaoValida(this.pPosicao.offset(1, 0))) {
            this.direction = Consts.RIGHT;
        }
        else if (t.ehPosicaoValida(this.pPosicao.offset(0, -1))) {
            this.direction = Consts.UP;
        }
        else if (t.ehPosicaoValida(this.pPosicao.offset(0, 1))) {
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
    
    private void check() {
        int distance = this.pPosicao.distanceTo(Desenhador.getTelaDoJogo().getHeroPosition());
        if (distance < 8) {
            this.addClock(20, 8, this::move, this::move, false);
            this.addClock(80, 2, this::setDirectionTowardsHero, this::setDirectionTowardsHero, false);
            this.addClock(80, 2, null, this::check, false);
        }
        else {
            this.addClock(5, 16, this::move, this::move, false);
            this.addClock(2, 40, this::setDirectionRandom, this::setDirectionRandom, false);
            this.addClock(40, 2, null, this::check, false);
        }
    }
    
    public void touchFire() {
        this.remove();
    }
    
    public void touchAnother(Elemento e) {
        e.touchEnemy();
    }
}
