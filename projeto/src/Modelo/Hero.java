package Modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import Controler.ControleDeJogo;
import Controler.Tela;


public class Hero extends Elemento implements Serializable {
    
    private int nBombasPermitida;
    private int nBombasColocada;
    private int bombermanPotencia;
    private int delay;
    private boolean allow_movement;
    private boolean isDead;
    
    private void update_location() {
        Desenhador.getTelaDoJogo().setHeroPosition(this.pPosicao);
    }
    
    public Hero() {
        super("bomberman_down.png", 1, 2, 8, 0, -1);
        this.nBombasColocada = 0;
        this.nBombasPermitida = 1;
        this.bombermanPotencia = 1;
        this.priority = 2;
        this.delay = 4;
        this.allow_movement = true;
        this.addClock(1,
                      3,
                      this::update_location,
                      null,
                      true
                      );
    }
    
    private void restrictMovement() {
        this.allow_movement = false;
        this.addClock(this.delay, 1, null, this::restoreMovement, false);
    }
    
    private void restoreMovement() {
        this.allow_movement = true;
    }

    public void voltaAUltimaPosicao() {
        this.pPosicao.volta();
    }
    
    public void createBomb(final ControleDeJogo c, final Tela t) {
        if (!Desenhador.getTelaDoJogo().ehPosicaoValida(pPosicao)) return;
        
        final Bomba bomb = new Bomba();
        nBombasColocada++;
        
        bomb.setPosicao(this.pPosicao.getColuna(), this.pPosicao.getLinha());
        bomb.setPotencia(this.bombermanPotencia);
        bomb.setUp();
        Desenhador.getTelaDoJogo().addElemento(bomb);
        
        this.addClock(Consts.BOMB_TIMER, 8, null, this::recharge, false);
    }
    
    private void recharge() {
        nBombasColocada--;
    }
    
    public void Event(int key, ControleDeJogo c, Tela t) {
        /*Movimento do heroi via teclado*/
        Posicao offset = pPosicao.offset(0, 0);
        int valido;
        
        if (isDead) {
            return;
        }
        switch (key) {
            case Consts.UP:
                this.sprite.changeSheet("bomberman_up.png");
                if (t.ehPosicaoValida(this.getPosicao().offset(0, -1))) {
                    if (this.allow_movement) {
                        this.moveUp();
                        this.restrictMovement();
                        this.sprite.cycle();
                    }
                }
                break;
            case Consts.DOWN:
                this.sprite.changeSheet("bomberman_down.png");
                if (t.ehPosicaoValida(this.getPosicao().offset(0, 1))) {
                    if (this.allow_movement) {
                        this.moveDown();
                        this.restrictMovement();
                        this.sprite.cycle();
                    }
                }
                break;
            case Consts.LEFT:
                this.sprite.changeSheet("bomberman_left.png");
                if (t.ehPosicaoValida(this.getPosicao().offset(-1, 0))) {
                    if (this.allow_movement) {
                        this.moveLeft();
                        this.restrictMovement();
                        this.sprite.cycle();
                    }
                }
                break;
            case Consts.RIGHT:
                this.sprite.changeSheet("bomberman_right.png");
                if (t.ehPosicaoValida(this.getPosicao().offset(1, 0))) {
                    if (this.allow_movement) {
                        this.moveRight();
                        this.restrictMovement();
                        this.sprite.cycle();
                    }
                }
                break;
            case Consts.BOMB:
                if (this.nBombasColocada == this.nBombasPermitida) {
                    break;
                } else {
                    this.createBomb(c, t);
                    break;
                }
            case Consts.DOOR:
                break;             
        }
    }
    
    public void die() {
        if (!isDead) {
            isDead = true;
            this.sprite.changeSheet("bomberman_dead.png");
            this.addClock(30, 2, this.sprite::cycle, this::resetStage, false);
        }
    }
    
    private void resetStage() {
        Desenhador.getTelaDoJogo().stageReset();
    }
    
    public void touchEnemy() {
        this.die();
    }
    
    public void powerUpBomba() {
        if (nBombasPermitida < Consts.MAX_BOMBS) {
            nBombasPermitida++;
        }
    }
    public void powerUpPotencia() {
        if (bombermanPotencia < Consts.MAX_POWER) {
            bombermanPotencia++;
        }
    }
    public void speedUp() {
        this.delay--;
    }
    public void touchAnother(Elemento e) {
        e.touchHero(this);
    }
}