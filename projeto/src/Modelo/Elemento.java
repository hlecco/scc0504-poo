package Modelo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Controler.Tela;
import Auxiliar.Posicao;
import Controler.ControleDeJogo;
import java.util.ArrayList;
import java.util.concurrent.Callable;


public abstract class Elemento implements Serializable {
    protected Tela pTela;
    protected Sprite sprite;
    protected Posicao pPosicao;
    protected boolean bTransponivel; // Pode passar por cima?
    protected boolean bMortal; // Se encostar, morre?
    protected boolean bDestrutivel; // Destrutível com fogo
    protected int hidden; // 0: nada escondido, 1: bomberup, 2: fireup, 3: fogo, 4: porta
    ArrayList<Clock> clocks; 
    int priority; // higher priority objects will be drawn over lower priority, defaults to 0
       
    protected Elemento(String sNomeImagePNG) {
        this(sNomeImagePNG, 1, 1, 1, 0, 0);
    }
    
    protected Elemento(String sNomeImagePNG, int nFrames) {
        this(sNomeImagePNG, 1, 1, nFrames, 0, 0);
    }
    
    protected Elemento(String sNomeImagePNG, int hSize, int vSize, int nFrames, int hOffset, int vOffset) {
        this.pPosicao = new Posicao(1, 1);
        this.bTransponivel = true;
        this.bMortal = false;
        this.hidden = 0;
        this.clocks = new ArrayList<Clock>();
        
        this.sprite = new Sprite(sNomeImagePNG, hSize, vSize, nFrames, hOffset, vOffset);
        
        this.priority = 0;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    protected void destroiElemento() {
        Desenhador.getTelaDoJogo().removeElemento(this);
    }
    
    public void Event(int key, ControleDeJogo c, Tela t) {}

    public Posicao getPosicao() {
        return pPosicao;
    }

    public boolean isbTransponivel() {
        return bTransponivel;
    }

    public void setbTransponivel(boolean bTransponivel) {
        this.bTransponivel = bTransponivel;
    }
    
    public boolean isbDestrutivel() {
        return bDestrutivel;
    }
    
    public void setbDestrutivel(boolean pDest) {
        this.bDestrutivel = pDest;
    }

    public boolean setPosicao(int coluna, int linha) {
        boolean test = pPosicao.setPosicao(coluna, linha);
        Desenhador.getTelaDoJogo().moveElemento(this);
        for (Elemento e: Desenhador.getTelaDoJogo().buscaElemento(this.getPosicao())) {
            this.touchAnother(e);
        }
        return test;
    }
    
    public int getHiddenItem() {
        return this.hidden;
    }
    
    public void setHidden(int pHidden) {
        this.hidden = pHidden;
    }

    public boolean moveUp() {
        boolean test = this.pPosicao.moveUp();
        Desenhador.getTelaDoJogo().moveElemento(this);
        for (Elemento e: Desenhador.getTelaDoJogo().buscaElemento(this.getPosicao())) {
            this.touchAnother(e);
        }
        return test;
    }

    public boolean moveDown() {
        boolean test = this.pPosicao.moveDown();
        Desenhador.getTelaDoJogo().moveElemento(this);
        for (Elemento e: Desenhador.getTelaDoJogo().buscaElemento(this.getPosicao())) {
            this.touchAnother(e);
        }
        return test;
    }

    public boolean moveRight() {
        boolean test = this.pPosicao.moveRight();
        Desenhador.getTelaDoJogo().moveElemento(this);
        for (Elemento e: Desenhador.getTelaDoJogo().buscaElemento(this.getPosicao())) {
            this.touchAnother(e);
        }
        return test;
    }

    public boolean moveLeft() {
        boolean test = this.pPosicao.moveLeft();
        Desenhador.getTelaDoJogo().moveElemento(this);
        for (Elemento e: Desenhador.getTelaDoJogo().buscaElemento(this.getPosicao())) {
            this.touchAnother(e);
        }
        return test;
    }
    
    public void addClock(int duration, int speed, Runnable onStep, Runnable onEnd, boolean restart) {
        clocks.add(new Clock(duration, speed, onStep, onEnd, restart));
    }
    
   public void autoDesenho() {
        this.sprite.draw(pPosicao.getColuna(), pPosicao.getLinha());        
    }
   
   public void step() {
       for (Clock c: this.clocks) {
           if (c.step()) {
               clocks.remove(c);
           }
       }
   }
   
   public void remove() {
       Desenhador.getTelaDoJogo().removeElemento(this);
   }

   protected void touchFire() {} // o que acontece quando o elemento toca o fogo
   protected void touchHero(Hero h) {} // o que acontece quando o elemento toca o heroi
   protected void touchAnother(Elemento e) {} // o que acontece com o elemento que esse objeto toca
}