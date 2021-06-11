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


public class Fogo extends Elemento {

    private int potencia;
    private int direcao;

    public Fogo() {
        super("explosao.png");
        this.potencia = 1;
        this.direcao = Consts.DOWN;
    }
    
    public void setPotencia(int p) {
        if (p > -1) {
            this.potencia = p;
        }
    }
    
    public void setDirecao(int d) {
        this.direcao = d;
    }
    
    public void propaga(ControleDeJogo c, Tela t) {
        Desenhador.getTelaDoJogo().addElemento(this);
        
        if (potencia > 1) {
            Posicao offset = new Posicao(pPosicao.getLinha(), pPosicao.getColuna());

            switch (this.direcao) {
                case Consts.UP:
                    offset.moveUp();
                    if (c.ehPosicaoValida(t.getElementos(), offset)) {
                        Fogo fogo_filho = new Fogo();
                        fogo_filho.setPotencia(this.potencia - 1);
                        fogo_filho.setDirecao(this.direcao);
                        fogo_filho.setPosicao(offset.getLinha(), offset.getColuna());
                        fogo_filho.propaga(c, t);
                    }
                    break;
                case Consts.DOWN:
                    offset.moveDown();
                    if (c.ehPosicaoValida(t.getElementos(), offset)) {
                        Fogo fogo_filho = new Fogo();
                        fogo_filho.setPotencia(this.potencia - 1);
                        fogo_filho.setDirecao(this.direcao);
                        fogo_filho.setPosicao(offset.getLinha(), offset.getColuna());
                        fogo_filho.propaga(c, t);
                    }
                    break;
                case Consts.LEFT:
                    offset.moveLeft();
                    if (c.ehPosicaoValida(t.getElementos(), offset)) {
                        Fogo fogo_filho = new Fogo();
                        fogo_filho.setPotencia(this.potencia - 1);
                        fogo_filho.setDirecao(this.direcao);
                        fogo_filho.setPosicao(offset.getLinha(), offset.getColuna());
                        fogo_filho.propaga(c, t);
                    }
                    break;
                case Consts.RIGHT:
                    offset.moveRight();
                    if (c.ehPosicaoValida(t.getElementos(), offset)) {
                        Fogo fogo_filho = new Fogo();
                        fogo_filho.setPotencia(this.potencia - 1);
                        fogo_filho.setDirecao(this.direcao);
                        fogo_filho.setPosicao(offset.getLinha(), offset.getColuna());
                        fogo_filho.propaga(c, t);
                    }
                    break;
            }
        }
        vanish();
    }
    
    public void teste() {
        Desenhador.getTelaDoJogo().removeElemento(this);
    }
    
    public void vanish() {
        TimerTask undraw;
        undraw = new TimerTask() {
            public void run() {
                teste();
            }
        };
        Timer timer = new Timer();
        timer.schedule(undraw, 20 * Consts.FRAME_INTERVAL);
    }
}
