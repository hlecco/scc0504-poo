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
    
    private int nBombasPermitida = 1;
    private int nBombasColocada = 0;
    private int bombermanPotencia = 2;
    private boolean isDead;
    
    public Hero() {
        super("bomberman_down.png", 1, 2, 8, 0, -1);
        this.priority = 2;
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
        
        TimerTask recharge = new TimerTask() {
            public void run() {
                nBombasColocada--;
            }
        };
        
        Timer timer = new Timer();
        timer.schedule(recharge, 30 * Consts.FRAME_INTERVAL);
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
                this.sprite.cycle();
                if (t.ehPosicaoValida(this.getPosicao().offset(0, -1))) {
                    this.moveUp();
                }
                break;
            case Consts.DOWN:
                this.sprite.changeSheet("bomberman_down.png");
                this.sprite.cycle();
                if (t.ehPosicaoValida(this.getPosicao().offset(0, 1))) {
                    this.moveDown();
                }
                break;
            case Consts.LEFT:
                this.sprite.changeSheet("bomberman_left.png");
                this.sprite.cycle();
                if (t.ehPosicaoValida(this.getPosicao().offset(-1, 0))) {
                    this.moveLeft();
                }
                break;
            case Consts.RIGHT:
                this.sprite.changeSheet("bomberman_right.png");
                this.sprite.cycle();
                if (t.ehPosicaoValida(this.getPosicao().offset(1, 0))) {
                    this.moveRight();
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
        }
        this.sprite.changeSheet("bomberman_dead.png");
        this.addClock(10, 2, this.sprite::cycle, null, true);
    }
    
    public void touchFire() {
        this.die();
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
    public void touchAnother(Elemento e) {
        e.touchHero(this);
    }
}