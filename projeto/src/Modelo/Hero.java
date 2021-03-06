package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import Controler.ControleDeJogo;
import Controler.Tela;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Hero extends Elemento implements Serializable{
    public Hero(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }

    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }
    
    public void createBomb(final ControleDeJogo c, final Tela t) {
        final Bomba bomb = new Bomba("bomb.png");
        bomb.setPosicao(this.pPosicao.getLinha(), this.pPosicao.getColuna());
        bomb.setPotencia(3);
        Desenhador.getTelaDoJogo().addElemento(bomb);
        TimerTask explode = new TimerTask() {
            public void run() {
                bomb.explode(c, t);
                return;
            }
        };
        Timer timer = new Timer();
        timer.schedule(explode, 30 * Consts.FRAME_INTERVAL);
    }
    
    public void Event(int key, ControleDeJogo c, Tela t) {
        /*Movimento do heroi via teclado*/
        Posicao offset = pPosicao.offset(0,0);
        switch (key) {
            case Consts.UP:
                offset.moveUp();
                if (c.ehPosicaoValida(t.getElementos(), offset)) {
                    this.moveUp();
                }
                break;
            case Consts.DOWN:
                offset.moveDown();
                if (c.ehPosicaoValida(t.getElementos(), offset)) {
                    this.moveDown();
                }
                break;
            case Consts.LEFT:
                offset.moveLeft();
                if (c.ehPosicaoValida(t.getElementos(), offset)) {
                    this.moveLeft();
                }
                break;
            case Consts.RIGHT:
                offset.moveRight();
                if (c.ehPosicaoValida(t.getElementos(), offset)) {
                    this.moveRight();
                }
                break;
            case Consts.BOMB:
                this.createBomb(c, t);
                break;
        }
    }
}
