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
    
    int nBombasPermitida = 1;
    int nBombasColocada = 0;
    int bombermanPotencia = 1;
    
    public Hero() {
        super("bomberman.png");
    }

    public void voltaAUltimaPosicao() {
        this.pPosicao.volta();
    }
    
    public void createBomb(final ControleDeJogo c, final Tela t) {
        final Bomba bomb = new Bomba();
        nBombasColocada++;
        
        bomb.setPosicao(this.pPosicao.getLinha(), this.pPosicao.getColuna());
        bomb.setPotencia(this.bombermanPotencia);
        Desenhador.getTelaDoJogo().addElemento(bomb);
        
        TimerTask explode = new TimerTask() {
            public void run() {
                bomb.explode(c, t);
                nBombasColocada--;
                return;
            }
        };
        
        Timer timer = new Timer();
        timer.schedule(explode, 30 * Consts.FRAME_INTERVAL);
    }
    
    public void Event(int key, ControleDeJogo c, Tela t) {
        /*Movimento do heroi via teclado*/
        Posicao offset = pPosicao.offset(0, 0);
        int valido;
        
        switch (key) {
            case Consts.UP:
                offset.moveUp();
                valido = c.ehPosicaoValida(t.getElementos(), offset);
                if (valido != 0 && valido != 2) {
                    if (valido == 3) {
                        Elemento e = t.buscaElemento(offset);
                        if (e != null) {
                            e.destroiElemento();
                        }
                        this.nBombasPermitida += 2;
                    }
                    if (valido == 4) {
                        Elemento e = t.buscaElemento(offset);
                        if (e != null) {
                            e.destroiElemento();
                        }
                        this.bombermanPotencia += 2;
                    }
                    if (valido == 5) {
                        // morre
                    }
                    this.moveUp();
                }
                break;
            case Consts.DOWN:
                offset.moveDown();
                valido = c.ehPosicaoValida(t.getElementos(), offset);
                if (valido != 0 && valido != 2) {
                    if (valido == 3) {
                        Elemento e = t.buscaElemento(offset);
                        if (e != null) {
                            e.destroiElemento();
                        }
                        this.nBombasPermitida += 2;
                    }
                    if (valido == 4) {
                        Elemento e = t.buscaElemento(offset);
                        if (e != null) {
                            e.destroiElemento();
                        }
                        this.bombermanPotencia += 2;
                    }
                    if (valido == 5) {
                        // morre
                    }
                    this.moveDown();
                }
                break;
            case Consts.LEFT:
                offset.moveLeft();
                valido = c.ehPosicaoValida(t.getElementos(), offset);
                if (valido != 0 && valido != 2) {
                    if (valido == 3) {
                        Elemento e = t.buscaElemento(offset);
                        if (e != null) {
                            e.destroiElemento();
                        }
                        this.nBombasPermitida += 2;
                    }
                    if (valido == 4) {
                        Elemento e = t.buscaElemento(offset);
                        if (e != null) {
                            e.destroiElemento();
                        }
                        this.bombermanPotencia += 2;
                    }
                    if (valido == 5) {
                        // morre
                    }                    
                    this.moveLeft();
                }
                break;
            case Consts.RIGHT:
                offset.moveRight();
                valido = c.ehPosicaoValida(t.getElementos(), offset);
                if (valido != 0 && valido != 2) {
                    if (valido == 3) {
                        Elemento e = t.buscaElemento(offset);
                        if (e != null) {
                            e.destroiElemento();
                        }
                        this.nBombasPermitida += 2;
                    }
                    if (valido == 4) {
                        Elemento e = t.buscaElemento(offset);
                        if (e != null) {
                            e.destroiElemento();
                        }
                        this.bombermanPotencia += 2;
                    }
                    if (valido == 5) {
                        // morre
                    }
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
                valido = c.ehPosicaoValida(t.getElementos(), offset);
                System.out.println(valido);
                if (valido == 5) {
                    System.out.println("porta.");
                } else {
                    break;
                }                
        }
    }
}