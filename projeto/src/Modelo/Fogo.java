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
                            int hid = e.hidden;
                            e.destroiElemento();
                            if (hid == 1) {
                                BomberUp bUp = new BomberUp();
                                bUp.setPosicao(offset.getLinha(), offset.getColuna());
                                Desenhador.getTelaDoJogo().addElemento(bUp);
                            } else if (hid == 2) {
                                FireUp fUp = new FireUp();
                                fUp.setPosicao(offset.getLinha(), offset.getColuna());
                                Desenhador.getTelaDoJogo().addElemento(fUp);
                            } else if (hid == 3) {
                                Door door = new Door();
                                door.setPosicao(offset.getLinha(), offset.getColuna());
                                Desenhador.getTelaDoJogo().addElemento(door);
                            }
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
                            int hid = e.hidden;
                            e.destroiElemento();
                            if (hid == 1) {
                                BomberUp bUp = new BomberUp();
                                bUp.setPosicao(offset.getLinha(), offset.getColuna());
                                Desenhador.getTelaDoJogo().addElemento(bUp);
                            } else if (hid == 2) {
                                FireUp fUp = new FireUp();
                                fUp.setPosicao(offset.getLinha(), offset.getColuna());
                                Desenhador.getTelaDoJogo().addElemento(fUp);
                            } else if (hid == 3) {
                                Door door = new Door();
                                door.setPosicao(offset.getLinha(), offset.getColuna());
                                Desenhador.getTelaDoJogo().addElemento(door);
                            }
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
                            int hid = e.hidden;
                            e.destroiElemento();
                            if (hid == 1) {
                                BomberUp bUp = new BomberUp();
                                bUp.setPosicao(offset.getLinha(), offset.getColuna());
                                Desenhador.getTelaDoJogo().addElemento(bUp);
                            } else if (hid == 2) {
                                FireUp fUp = new FireUp();
                                fUp.setPosicao(offset.getLinha(), offset.getColuna());
                                Desenhador.getTelaDoJogo().addElemento(fUp);
                            } else if (hid == 3) {
                                Door door = new Door();
                                door.setPosicao(offset.getLinha(), offset.getColuna());
                                Desenhador.getTelaDoJogo().addElemento(door);
                            }
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
                            int hid = e.hidden;
                            e.destroiElemento();
                            if (hid == 1) {
                                BomberUp bUp = new BomberUp();
                                bUp.setPosicao(offset.getLinha(), offset.getColuna());
                                Desenhador.getTelaDoJogo().addElemento(bUp);
                            } else if (hid == 2) {
                                FireUp fUp = new FireUp();
                                fUp.setPosicao(offset.getLinha(), offset.getColuna());
                                Desenhador.getTelaDoJogo().addElemento(fUp);
                            } else if (hid == 3) {
                                Door door = new Door();
                                door.setPosicao(offset.getLinha(), offset.getColuna());
                                Desenhador.getTelaDoJogo().addElemento(door);
                            }
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
