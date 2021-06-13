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


public class Fogo extends Elemento {

    private int potencia;
    private int direcao;

    public Fogo() {
        super("explosao.png");
        this.potencia = 1;
        this.direcao = 0;
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
        int valido;
        
        if (potencia > 1) {
            Posicao offset = new Posicao(pPosicao.getLinha(), pPosicao.getColuna());

            switch (this.direcao) {
                case Consts.UP:
                    offset.moveUp();
                    valido = c.ehPosicaoValida(t.getElementos(), offset);
                    
                    if (valido == 1) {
                        Fogo fogo_filho = new Fogo();
                        fogo_filho.setPotencia(this.potencia - 1);
                        fogo_filho.setDirecao(this.direcao);
                        fogo_filho.setPosicao(offset.getLinha(), offset.getColuna());
                        fogo_filho.propaga(c, t);
                    }
                    
                    if (valido == 2) {
                        Elemento e = t.buscaElemento(offset);
                        if (e != null) {
                            if (e.powerUp == 1) {
                                BomberUp bUp = new BomberUp();
                                Desenhador.getTelaDoJogo().addElemento(bUp);
                            } else if (e.powerUp == 2) {
                                FireUp fUp = new FireUp();
                                Desenhador.getTelaDoJogo().addElemento(fUp);
                            }
                            e.destroiElemento();
                        }
                    }
                    break;
                case Consts.DOWN:
                    offset.moveDown();
                    valido = c.ehPosicaoValida(t.getElementos(), offset);
                    
                    if (valido == 1) {
                        Fogo fogo_filho = new Fogo();
                        fogo_filho.setPotencia(this.potencia - 1);
                        fogo_filho.setDirecao(this.direcao);
                        fogo_filho.setPosicao(offset.getLinha(), offset.getColuna());
                        fogo_filho.propaga(c, t);
                    }
                    
                    if (valido == 2) {
                        Elemento e = t.buscaElemento(offset);
                        if (e != null) {
                            if (e.powerUp == 1) {
                                BomberUp bUp = new BomberUp();
                                Desenhador.getTelaDoJogo().addElemento(bUp);
                            } else if (e.powerUp == 2) {
                                FireUp fUp = new FireUp();
                                Desenhador.getTelaDoJogo().addElemento(fUp);
                            }
                            e.destroiElemento();
                        }
                    }
                    break;
                case Consts.LEFT:
                    offset.moveLeft();
                    valido = c.ehPosicaoValida(t.getElementos(), offset);
                    
                    if (valido == 1) {
                        Fogo fogo_filho = new Fogo();
                        fogo_filho.setPotencia(this.potencia - 1);
                        fogo_filho.setDirecao(this.direcao);
                        fogo_filho.setPosicao(offset.getLinha(), offset.getColuna());
                        fogo_filho.propaga(c, t);
                    }
                    
                    if (valido == 2) {
                        Elemento e = t.buscaElemento(offset);
                        if (e != null) {
                            if (e.powerUp == 1) {
                                BomberUp bUp = new BomberUp();
                                Desenhador.getTelaDoJogo().addElemento(bUp);
                            } else if (e.powerUp == 2) {
                                FireUp fUp = new FireUp();
                                Desenhador.getTelaDoJogo().addElemento(fUp);
                            }
                            e.destroiElemento();
                        }
                    }
                    break;
                case Consts.RIGHT:
                    offset.moveRight();
                    valido = c.ehPosicaoValida(t.getElementos(), offset);
                    
                    if (valido == 1) {
                        Fogo fogo_filho = new Fogo();
                        fogo_filho.setPotencia(this.potencia - 1);
                        fogo_filho.setDirecao(this.direcao);
                        fogo_filho.setPosicao(offset.getLinha(), offset.getColuna());
                        fogo_filho.propaga(c, t);
                    }
                    
                    if (valido == 2) {
                        Elemento e = t.buscaElemento(offset);
                        if (e != null) {
                            if (e.powerUp == 1) {
                                BomberUp bUp = new BomberUp();
                                Desenhador.getTelaDoJogo().addElemento(bUp);
                            } else if (e.powerUp == 2) {
                                FireUp fUp = new FireUp();
                                Desenhador.getTelaDoJogo().addElemento(fUp);
                            }
                            e.destroiElemento();
                        }
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
